package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;

import static by.spetr.web.controller.command.PagePath.INDEX_PAGE;
import static by.spetr.web.controller.command.Router.RouterType.REDIRECT;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();

        return new Router(INDEX_PAGE, REDIRECT);
    }
}
