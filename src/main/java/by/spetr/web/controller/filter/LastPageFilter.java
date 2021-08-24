package by.spetr.web.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.spetr.web.controller.command.RequestParameter.LAST_PAGE_PARAM;

/**
 * Filter is used to store the last page visited into session
 * There's an ignore-list below
 */
@WebFilter(filterName = "LastPageFilter", urlPatterns = {"*.jsp"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST},
        initParams = {@WebInitParam(name = "PAGES_ROOT_DIRECTORY", value = "/jsp", description = "Pages Param"),
                @WebInitParam(name = "INDEX_PAGE", value = "/index.jsp", description = "Pages Param")})
public class LastPageFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final List<String> ignoreList = new ArrayList<>();
    private String root;
    private String indexPage;

    @Override
    public void init(FilterConfig filterConfig) {
        root = filterConfig.getInitParameter("PAGES_ROOT_DIRECTORY");
        indexPage = filterConfig.getInitParameter("INDEX_PAGE");

        // pages, listed below, won't be stored as a last visited page
        ignoreList.add("/jsp/vehicle_info.jsp");
        ignoreList.add("/jsp/sign_up.jsp");
        ignoreList.add("/jsp/password_change.jsp");
        ignoreList.add("/jsp/password_recovery.jsp");
        ignoreList.add("/jsp/user_blocked_page.jsp");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String lastPage = indexPage;
        int rootFirstIndex = requestURI.indexOf(root);
        if (rootFirstIndex != -1) {
            lastPage = requestURI.substring(rootFirstIndex);
        }
        if (!ignoreList.contains(lastPage)) {
            httpRequest.getSession().setAttribute(LAST_PAGE_PARAM, lastPage);
        }
        logger.debug("Last page: {}", lastPage);
        chain.doFilter(request, response);
    }
}