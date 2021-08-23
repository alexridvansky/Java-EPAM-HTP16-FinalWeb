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

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;

public class PasswordChangeCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("PasswordChangeCommand called");

        UserRegForm form = null;

        try {
            form = (UserRegForm) doForm(request);

            boolean result = userService.changeUserPassword(form);

            request.setAttribute(OPERATION_SUCCESS_PARAM, form.isSuccess());
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, form.getFeedbackMsg());

            if (result) {
                return new Router(MAIN_PAGE, Router.RouterType.REDIRECT);
            } else {
                return new Router(PASSWORD_CHANGE_PAGE);
            }

        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());
            logger.error(e);

            return new Router(ERROR_PAGE);

        } catch (IllegalArgumentException e) {
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, e.getMessage());
            logger.error(e);

            return new Router(PASSWORD_CHANGE_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        UserRegForm form = new UserRegForm();

        UserDto user = (UserDto) request.getSession().getAttribute(USER_PARAM);
        String oldPassword = request.getParameter(USER_OLD_PASSWORD);
        String newPassword = request.getParameter(USER_NEW_PASSWORD);
        String newPasswordRepeat = request.getParameter(USER_NEW_PASSWORD_REPEAT);

        if (oldPassword == null || newPassword == null || newPasswordRepeat == null
                || oldPassword.isBlank() || newPassword.isBlank() || newPasswordRepeat.isBlank()
                || user == null || user.getLogin() == null) {

            throw new IllegalArgumentException("Arguments doesn't match requirements");
        }

        form.setLogin(user.getLogin());
        form.setOldPassword(oldPassword);
        form.setPassword(newPassword);
        form.setPasswordRepeat(newPasswordRepeat);

        return form;
    }
}
