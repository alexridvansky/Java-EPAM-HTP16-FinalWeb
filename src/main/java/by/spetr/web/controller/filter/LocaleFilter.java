package by.spetr.web.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;

import static by.spetr.web.controller.command.RequestParameter.LOCALE_PARAM;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter is used to set up the locale from user's system defaults to session
 */
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Locale filter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getSession().getAttribute(LOCALE_PARAM) == null) {
            Locale userSystemDefaultLocale = httpRequest.getLocale();
            httpRequest.getSession().setAttribute(LOCALE_PARAM, userSystemDefaultLocale);
            logger.debug("locale has been set by client's system default: {}", request.getLocale());
        }

        chain.doFilter(request, response);
    }
}