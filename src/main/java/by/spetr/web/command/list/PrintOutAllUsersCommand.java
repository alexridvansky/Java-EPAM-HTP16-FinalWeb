package by.spetr.web.command.list;

import by.spetr.web.command.Command;
import by.spetr.web.command.ParamConstant;
import by.spetr.web.command.Router;
import by.spetr.web.command.PagePathConstant;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class PrintOutAllUsersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = DefaultUserService.getInstance();
        try {
            List<User> users = userService.getUserList();
            request.setAttribute(ParamConstant.USER_LIST_PARAM, users);
            return new Router(PagePathConstant.PRINT_OUT_ALL_USERS, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            // todo: go to some showing_user_error.page
            return new Router(PagePathConstant.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
