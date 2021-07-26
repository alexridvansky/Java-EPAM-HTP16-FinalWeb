package by.spetr.web.command.user;

import by.spetr.web.command.*;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.spetr.web.command.PagePath.SHOW_USER_LIST;
import static by.spetr.web.command.RequestParameter.LAST_PAGE_PARAM;

public class ShowUserListCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = DefaultUserService.getInstance();

        try {
            List<User> users = userService.getUserList();
            request.setAttribute(RequestParameter.USER_LIST_PARAM, users);
            request.getSession().setAttribute(LAST_PAGE_PARAM, SHOW_USER_LIST);

            return new Router(SHOW_USER_LIST);

        } catch (ServiceException e) {
            // todo: go to some showing_user_error.page

            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
