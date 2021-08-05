package by.spetr.web.controller.filter;

import by.spetr.web.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.spetr.web.command.RequestParameter.USER_PARAM;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})  // '/*'
public class SecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMAND = "command"; //todo: replace by static import

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("SecurityFilter...");
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        User user = (User) session.getAttribute(USER_PARAM);

//        if (user == null || user.getRole() != UserRoleType.ROOT || user.getRole() != UserRoleType.MODERATOR) {
//            logger.debug("Unauthorised access attempt");
//        }


        chain.doFilter(request, response);
    }
}
