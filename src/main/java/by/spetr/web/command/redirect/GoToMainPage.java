package by.spetr.web.command.redirect;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.command.PagePath.MAIN_PAGE;
import static by.spetr.web.command.RequestParameter.LAST_PAGE_PARAM;

public class GoToMainPage implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);
        logger.debug("redirecting from '{}' to '{}'", lastPage, MAIN_PAGE);

        request.getSession().setAttribute(LAST_PAGE_PARAM,MAIN_PAGE);

        return new Router(MAIN_PAGE, Router.RouterType.REDIRECT);
    }
}
