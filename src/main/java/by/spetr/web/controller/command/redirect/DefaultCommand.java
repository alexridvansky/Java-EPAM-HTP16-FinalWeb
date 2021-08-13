package by.spetr.web.controller.command.redirect;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static by.spetr.web.controller.command.PagePath.ERROR_404_PAGE;
import static by.spetr.web.controller.command.Router.RouterType.REDIRECT;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(ERROR_404_PAGE, REDIRECT);
    }
}
