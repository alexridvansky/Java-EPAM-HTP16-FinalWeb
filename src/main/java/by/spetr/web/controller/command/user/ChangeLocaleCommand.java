package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Objects;

import static by.spetr.web.controller.command.RequestParameter.*;

public class ChangeLocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Locale locale = request.getLocale();
        String newLocale = request.getParameter(NEW_LOCALE_PARAM);
        request.getSession().setAttribute(LOCALE_PARAM, newLocale);
        logger.debug("locale changed: {} -> {}", locale, newLocale);

        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

        return new Router(Objects.requireNonNullElse(lastPage, PagePath.INDEX_PAGE));
    }
}
