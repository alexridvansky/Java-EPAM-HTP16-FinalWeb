package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();

        return new Router(PagePath.INDEX_PAGE);
    }
}
