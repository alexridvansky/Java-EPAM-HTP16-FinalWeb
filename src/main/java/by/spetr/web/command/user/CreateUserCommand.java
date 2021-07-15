package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.RegistrationFormData;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.command.RequestParameter.*;

public class CreateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = DefaultUserService.getInstance();

        RegistrationFormData registrationFormData = new RegistrationFormData(
                request.getParameter(USERNAME_PARAM),
                request.getParameter(PASSWORD_PARAM),
                request.getParameter(PASSWORD_AGAIN_PARAM),
                request.getParameter(EMAIL_PARAM),
                request.getParameter(PHONE_PARAM)
        );

        boolean isUserCreated = false;
        try {
            isUserCreated = userService.registerUser(registrationFormData);
        } catch (ServiceException e) {
            e.printStackTrace(); // todo: exc
        }

        logger.debug("user created: {}", isUserCreated);
        logger.debug("comment: {}", registrationFormData.getComment());

        if (!isUserCreated) {
            request.setAttribute(REGISTRATION_DATA, registrationFormData);
            return new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.FORWARD);
        }

        return new Router(PagePath.SIGN_UP_RESULT_PAGE, Router.RouterType.FORWARD);
    }
}
