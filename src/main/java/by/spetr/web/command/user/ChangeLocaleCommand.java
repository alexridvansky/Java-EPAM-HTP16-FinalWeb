package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static by.spetr.web.command.PagePath.INDEX_PAGE;
import static by.spetr.web.command.RequestParameter.*;

public class ChangeLocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Locale locale = request.getLocale();
        String newLocale = request.getParameter(NEW_LOCALE_PARAM);
        request.getSession().setAttribute(LOCALE_PARAM, newLocale);
        logger.debug("locale: {}", locale);
        logger.debug("new requestet locale: {}", newLocale);

        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);
        if (lastPage == null) {
            lastPage = INDEX_PAGE;
        }

        return new Router(lastPage);
    }
}
