package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.PASSWORD_RECOVERY_PAGE;
import static by.spetr.web.controller.command.Router.RouterType.REDIRECT;

public class GoToRecoveryPasswordPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("redirecting to password recovery page");

        return new Router(PASSWORD_RECOVERY_PAGE, REDIRECT);
    }
}
