package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.RegistrationFormDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.RequestParameter.*;

public class CreateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("CreateUserCommand called");
        UserService userService = DefaultUserService.getInstance();

        RegistrationFormDto registrationFormDto = new RegistrationFormDto(
                request.getParameter(USER_NAME_PARAM),
                request.getParameter(PASSWORD_PARAM),
                request.getParameter(PASSWORD_AGAIN_PARAM),
                request.getParameter(EMAIL_PARAM),
                request.getParameter(PHONE_PARAM)
        );

        boolean isUserCreated = false;
        try {
            isUserCreated = userService.registerUser(registrationFormDto);
        } catch (ServiceException e) {
            e.printStackTrace(); // todo: exc
        }

        logger.debug("user created: {}", isUserCreated);
        logger.debug("comment: {}", registrationFormDto.getComment());

        if (!isUserCreated) {
            request.setAttribute(REGISTRATION_DATA, registrationFormDto);
            return new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.FORWARD);
        }

        return new Router(PagePath.SIGN_UP_RESULT_PAGE, Router.RouterType.FORWARD);
    }
}
