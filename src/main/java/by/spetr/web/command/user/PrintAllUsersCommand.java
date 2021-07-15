package by.spetr.web.command.user;

import by.spetr.web.command.*;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class PrintAllUsersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = DefaultUserService.getInstance();
        try {
            List<User> users = userService.getUserList();
            request.setAttribute(RequestParameter.USER_LIST_PARAM, users);
            return new Router(PagePath.PRINT_ALL_USERS, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            // todo: go to some showing_user_error.page
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
