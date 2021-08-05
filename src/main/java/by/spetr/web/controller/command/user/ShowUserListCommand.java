package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.*;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.SHOW_USER_LIST_ADM;
import static by.spetr.web.controller.command.RequestParameter.LAST_PAGE_PARAM;

public class ShowUserListCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = DefaultUserService.getInstance();

        try {
            List<User> users = userService.getUserList();
            request.setAttribute(RequestParameter.USER_LIST_PARAM, users);
            request.getSession().setAttribute(LAST_PAGE_PARAM, SHOW_USER_LIST_ADM);

            return new Router(SHOW_USER_LIST_ADM);

        } catch (ServiceException e) {
            // todo: go to some showing_user_error.page

            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
