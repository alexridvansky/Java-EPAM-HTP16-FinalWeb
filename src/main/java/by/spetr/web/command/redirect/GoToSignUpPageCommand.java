package by.spetr.web.command.redirect;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;
import by.spetr.web.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToSignUpPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("redirect to SignUp page");

        return new Router(PagePath.SIGN_UP_PAGE);
    }
}
