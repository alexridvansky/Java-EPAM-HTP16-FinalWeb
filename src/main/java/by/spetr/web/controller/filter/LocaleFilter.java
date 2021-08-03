package by.spetr.web.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;

import static by.spetr.web.command.RequestParameter.LOCALE_PARAM;

/**
 * Filter is used to set up the locale from user system defaults to session
 */
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getSession().getAttribute(LOCALE_PARAM) == null) {
            Locale userSystemDefaultLocale = httpRequest.getLocale();
            httpRequest.getSession().setAttribute(LOCALE_PARAM, userSystemDefaultLocale);
            logger.debug("locale has been set by client's system default: {}", request.getLocale());
        }

        chain.doFilter(request, response);
    }
}