package by.spetr.web.command.list;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;
import by.spetr.web.command.PagePathConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToSignUpPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePathConstant.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
    }
}
