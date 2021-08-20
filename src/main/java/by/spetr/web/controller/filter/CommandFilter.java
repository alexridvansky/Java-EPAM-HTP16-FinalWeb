package by.spetr.web.controller.filter;

import by.spetr.web.controller.command.CommandType;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.AccessControlService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

import static by.spetr.web.controller.command.RequestParameter.COMMAND_PARAM;
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
    private static final UserService userService = UserService.getInstance();
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

        if (user.getUserId() != 0) {
            Optional<User> optionalUser;

            try {
                optionalUser = userService.getUserById(user.getUserId());
                if (optionalUser.isEmpty()) {
                    logger.error("Provided user not found in the db, access denied");
                    httpServletResponse.sendRedirect(indexPath);
                    return;
                }
            } catch (ServiceException e) {
                logger.error("User actual state and role can't be checked at the moment");
                httpServletResponse.sendRedirect(indexPath);
                return;
            }

            // performing double check to ensure user's role or state haven't been changed recently
            UserDto userCheck = userService.convertToDto(optionalUser.get());
            session.setAttribute(USER_PARAM, userCheck);
            user = userCheck;
        }

        try {
            String command = servletRequest.getParameter(COMMAND_PARAM);
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
