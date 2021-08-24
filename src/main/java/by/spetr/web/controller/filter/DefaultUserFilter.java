package by.spetr.web.controller.filter;

import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.spetr.web.controller.command.RequestParameter.USER_PARAM;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filter is used for checking if session contains information about user
 * and sets it up if instance of user not been found in sessionScope
 */
@WebFilter(filterName = "DefaultUserFilter", urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "GUEST_USERNAME", value = "GUEST")
})
public class DefaultUserFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private String guestUsername;

    @Override
    public void init(FilterConfig filterConfig) {
        guestUsername = filterConfig.getInitParameter("GUEST_USERNAME");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Set default user filter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        UserDto user = (UserDto) session.getAttribute(USER_PARAM);
        if (user == null) {
            user = new UserDto();
            user.setUserId(0);
            user.setLogin(guestUsername);
            user.setRole(UserRoleType.GUEST);
            user.setState(UserStateType.ENABLED);
            session.setAttribute(USER_PARAM, user);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
