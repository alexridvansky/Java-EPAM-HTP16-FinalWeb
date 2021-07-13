package by.spetr.web.command.list;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePathConstant;
import by.spetr.web.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToLoginErrorPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePathConstant.LOGIN_ERROR, Router.RouterType.REDIRECT);
    }
}
