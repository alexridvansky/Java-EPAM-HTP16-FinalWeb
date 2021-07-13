package by.spetr.web.command.list;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePathConstant;
import by.spetr.web.command.ParamConstant;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class GoToSignInResultPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = DefaultUserService.getInstance();

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        try {
            Optional<User> optionalUser = userService.logIn(login, pass);

            if (optionalUser.isPresent()) {
                request.setAttribute(ParamConstant.USER_PARAM, optionalUser.get());
                return new Router(PagePathConstant.SIGN_IN_RESULT_PAGE, Router.RouterType.FORWARD);
            } else {
                return new Router(PagePathConstant.LOGIN_ERROR, Router.RouterType.FORWARD);
            }

        } catch (ServiceException e) {
            e.printStackTrace(); // todo: exc
            return new Router(PagePathConstant.ERROR_PAGE, Router.RouterType.FORWARD); // todo: exc redirect
        }


    }
}
