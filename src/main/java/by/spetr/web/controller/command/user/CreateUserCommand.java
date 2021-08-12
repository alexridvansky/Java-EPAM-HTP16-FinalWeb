package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.form.UserRegistrationForm;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

public class CreateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("CreateUserCommand called");
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

        UserRegistrationForm userForm = new UserRegistrationForm(
                request.getParameter(USER_NAME_PARAM),
                request.getParameter(PASSWORD_PARAM),
                request.getParameter(PASSWORD_AGAIN_PARAM),
                request.getParameter(EMAIL_PARAM),
                request.getParameter(PHONE_PARAM)
        );

        try {
            Optional<UserDto> optionalUserDto = userService.registerUser(userForm);
            if (optionalUserDto.isEmpty()) {
                request.setAttribute(REGISTRATION_DATA, userForm);

                return new Router(PagePath.SIGN_UP_PAGE);
            } else {
                request.getSession().setAttribute(USER_PARAM, optionalUserDto.get());

                return new Router(Objects.requireNonNullElse(lastPage, PagePath.INDEX_PAGE));
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }
}
