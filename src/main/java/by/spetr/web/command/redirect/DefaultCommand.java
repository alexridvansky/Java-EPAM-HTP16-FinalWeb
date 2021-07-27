package by.spetr.web.command.redirect;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;
import by.spetr.web.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ERROR_404_PAGE, Router.RouterType.REDIRECT);
    }
}
