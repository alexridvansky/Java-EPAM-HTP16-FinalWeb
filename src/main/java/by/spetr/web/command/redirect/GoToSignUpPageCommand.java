package by.spetr.web.command.redirect;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;
import by.spetr.web.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.command.PagePath.SIGN_UP_PAGE;
import static by.spetr.web.command.RequestParameter.LAST_PAGE_PARAM;

public class GoToSignUpPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);
        logger.debug("redirecting from '{}' to '{}'", lastPage, SIGN_UP_PAGE);

        request.getSession().setAttribute(LAST_PAGE_PARAM,SIGN_UP_PAGE);

        return new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
    }
}
