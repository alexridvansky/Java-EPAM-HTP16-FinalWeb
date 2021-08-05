package by.spetr.web.controller.command.redirect;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.MAIN_PAGE;

public class GoToMainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("redirecting to Main page");

        return new Router(MAIN_PAGE);
    }
}
