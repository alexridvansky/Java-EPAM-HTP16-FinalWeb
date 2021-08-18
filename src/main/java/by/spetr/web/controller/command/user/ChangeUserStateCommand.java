package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

public class ChangeUserStateCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Change user state method been called");

        try {
            UserForm form = (UserForm) doForm(request);
            logger.debug("'{}' -> '{}'", form.getUserName(), form.getState());

            userService.updateUserState(form);
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, form.getFeedbackMsg());
            request.setAttribute(OPERATION_SUCCESS_PARAM, form.isSuccess());

            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);
            return new Router(Objects.requireNonNullElse(lastPage, PagePath.INDEX_PAGE));

        } catch (ServiceException | IllegalArgumentException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());
            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        UserForm form = new UserForm();

        UserDto executor = (UserDto) request.getSession().getAttribute(USER_PARAM);
        String userName = request.getParameter(USER_NAME_PARAM);
        String userState = request.getParameter(USER_STATE_PARAM);

        if (userState == null || userName == null || executor == null) {
            logger.error("Wrong parameters' types, parsing error");
            throw new IllegalArgumentException("Wrong parameters' types, parsing error");
        }

        form.setExecutor(executor);
        form.setUserName(userName);
        form.setState(UserStateType.valueOf(userState));

        return form;
    }
}
