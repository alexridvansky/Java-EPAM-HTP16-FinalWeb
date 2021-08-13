package by.spetr.web.controller.filter;

import by.spetr.web.controller.command.CommandType;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.service.AccessControlService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.spetr.web.controller.command.RequestParameter.COMMAND;
import static by.spetr.web.controller.command.RequestParameter.USER_PARAM;

/**
 * Security filter controls client's access to commands through AccessControlService
 */
@WebFilter(filterName = "CommandFilter", urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "INDEX_PATH", value = "/index.jsp"),
})
public class CommandFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final AccessControlService accessControlService = AccessControlService.getInstance();
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)

            throws IOException, ServletException {
        logger.debug("CommandFilter...");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        UserDto user = (UserDto) session.getAttribute(USER_PARAM);


        try {
            String command = servletRequest.getParameter(COMMAND);
            CommandType requestedCommand = null;
            if (command != null) {
                requestedCommand = CommandType.valueOf(command.toUpperCase());

                if (!accessControlService.commandPermission(user.getRole(), requestedCommand)) {
                    logger.error("Access forbidden. Redirect to index");
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + indexPath);
                    return;
                }

            }
        } catch (IllegalArgumentException e) {
            logger.error("Unknown command type. Redirect to index", e);
            httpServletResponse.sendRedirect(indexPath);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
