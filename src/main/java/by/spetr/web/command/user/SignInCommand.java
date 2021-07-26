package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static by.spetr.web.command.PagePath.UNSUPPORTED_COMMAND;
import static by.spetr.web.command.RequestParameter.*;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();
    private static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR";

    @Override
    public Router execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_PARAM);
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

        if (user != null) {
            logger.error("Attempt to authenticate from already authenticated user");
            return new Router(UNSUPPORTED_COMMAND, Router.RouterType.REDIRECT);
        }

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        try {
            Optional<User> optionalUser = userService.logIn(login, pass);

            if (optionalUser.isPresent()) {
                request.getSession().setAttribute(USER_PARAM, optionalUser.get());
            } else {
                request.setAttribute(AUTHENTICATION_ERROR, LOGIN_ERROR_MESSAGE);
            }

            return new Router(Objects.requireNonNullElse(lastPage, PagePath.INDEX_PAGE));

        } catch (ServiceException e) {
            e.printStackTrace(); // todo: exc
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD); // todo: exc redirect
        }
    }
}