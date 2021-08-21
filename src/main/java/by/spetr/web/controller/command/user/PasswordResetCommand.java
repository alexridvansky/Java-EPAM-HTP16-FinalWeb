package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.MAIN_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

public class PasswordResetCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            UserForm form = (UserForm) doForm(request);
            userService.passwordRecover(form);

            // there is no additional informing but case when error occurred on service or dao layer.
            // it's done on purpose to not to let someone know whether username given present in the system or not.
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, "Request for a new password accepted, wait for a message.");
            request.setAttribute(OPERATION_SUCCESS_PARAM, true);

            return new Router(MAIN_PAGE);

        } catch (IllegalArgumentException | ServiceException e) {
            logger.error(e.getMessage(), e);
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, e.getMessage());
            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        UserForm form = new UserForm();
        String userName = request.getParameter(USER_NAME_PARAM);
        if (userName == null) {
            throw new IllegalArgumentException("Illegal parameter(s)");
        }
        form.setUserName(userName);

        return form;
    }
}
