package by.spetr.web.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static by.spetr.web.command.RequestParameter.LAST_PAGE_PARAM;

/**
 * Filter is used to store in the session last page visited
 * to make it possible get back to it
 */
@WebFilter(urlPatterns = {"*.jsp"}, dispatcherTypes = {DispatcherType.FORWARD}
        , initParams = {@WebInitParam(name = "PAGES_ROOT_DIRECTORY", value = "/jsp", description = "Pages Param")
        , @WebInitParam(name = "INDEX_PAGE", value = "/index.jsp", description = "Pages Param")})
public class LastPageFilter implements Filter {
    private String root;
    private String indexPage;

    @Override
    public void init(FilterConfig filterConfig) {
        root = filterConfig.getInitParameter("PAGES_ROOT_DIRECTORY");
        indexPage = filterConfig.getInitParameter("INDEX_PAGE");
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
        httpRequest.getSession().setAttribute(LAST_PAGE_PARAM, lastPage);
        chain.doFilter(request, response);
    }
}