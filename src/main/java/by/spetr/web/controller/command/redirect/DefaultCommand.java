package by.spetr.web.controller.command.redirect;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.controller.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ERROR_404_PAGE, Router.RouterType.REDIRECT);
    }
}
