package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.UserRegForm;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;

public class PasswordChangeCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("PasswordChangeCommand called");
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

        UserRegForm form = (UserRegForm) doForm(request);

        try {
            Optional<UserDto> optionalUserDto = userService.registerUser(form);
            if (optionalUserDto.isEmpty()) {
                request.setAttribute(USER_REGISTRATION_DATA_PARAM, form);
                request.setAttribute(FEEDBACK_MESSAGE_PARAM, form.getFeedbackMsg());

                return new Router(SIGN_UP_PAGE);
            } else {
                request.getSession().setAttribute(USER_PARAM, optionalUserDto.get());

                return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        UserRegForm form = new UserRegForm();
        form.setLogin(request.getParameter(REG_USER_NAME_PARAM));
        form.setPassword(request.getParameter(USER_PASSWORD_PARAM));
        form.setPasswordRepeat(request.getParameter(USER_PASSWORD_AGAIN_PARAM));
        form.setEmail(request.getParameter(USER_EMAIL_PARAM));
        form.setPhone(request.getParameter(USER_PHONE_PARAM));

        return form;
    }
}
