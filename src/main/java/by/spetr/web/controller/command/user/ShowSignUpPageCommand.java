package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.SIGN_UP_PAGE;

public class ShowSignUpPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("redirect to SignUp page");

        return new Router(SIGN_UP_PAGE);
    }
}
