package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.UserRegForm;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;
import static by.spetr.web.model.entity.type.UserStateType.CONFIRM;

import javax.servlet.http.HttpServletRequest;

public class CreateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("CreateUserCommand called");
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

        UserRegForm form = (UserRegForm) doForm(request);

        try {
            Optional<UserDto> optionalUserDto = userService.registerUser(form);
            if (optionalUserDto.isEmpty()) {
                request.setAttribute(USER_REGISTRATION_DATA_PARAM, form);
                request.setAttribute(FEEDBACK_MESSAGE_PARAM, form.getFeedbackMsg());

                return new Router(SIGN_UP_PAGE);
            } else {
                UserDto userDto = optionalUserDto.get();
                request.getSession().setAttribute(USER_PARAM, userDto);

                if (userDto.getState() == CONFIRM) {
                    Optional<String> optionalCode = userService.getConfirmCode(userDto.getUserId());
                    if (optionalCode.isPresent()) {
                        request.setAttribute(USER_CONFIRMATION_CODE, optionalCode.get());

                        return new Router(CONFIRMATION_PAGE);
                    } else {
                        request.setAttribute(FEEDBACK_MESSAGE_PARAM, "There's no confirmation code for this user");

                        return new Router(ERROR_PAGE);
                    }
                }

                return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);

        } catch (IllegalArgumentException e) {
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, e.getMessage());
            logger.error(e);

            return new Router(SIGN_UP_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        UserRegForm form = new UserRegForm();

        String login = request.getParameter(REG_USER_NAME_PARAM);
        String password = request.getParameter(USER_PASSWORD_PARAM);
        String passwordRepeat = request.getParameter(USER_PASSWORD_AGAIN_PARAM);
        String email = request.getParameter(USER_EMAIL_PARAM);
        String phone = request.getParameter(USER_PHONE_PARAM);

        if (login == null || password == null || passwordRepeat == null || email == null || phone == null
                || login.isBlank() || password.isBlank() || passwordRepeat.isBlank() || email.isBlank() || phone.isBlank()) {

            throw new IllegalArgumentException("Arguments doesn't match requirements");
        }

        form.setLogin(login);
        form.setPassword(password);
        form.setPasswordRepeat(passwordRepeat);
        form.setEmail(email);
        form.setPhone(phone);

        return form;
    }
}
