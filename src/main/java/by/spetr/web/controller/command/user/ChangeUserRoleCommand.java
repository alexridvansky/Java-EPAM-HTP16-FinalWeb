package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("ChangeUserRoleCommand called");

        try {
            UserForm form = (UserForm) doForm(request);
            logger.debug("'{}' -> '{}'", form.getUserName(), form.getRole());

            userService.updateUserRole(form);
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

        String userName = request.getParameter(USER_NAME_PARAM);
        String userRole = request.getParameter(USER_ROLE_PARAM);
        UserDto executor = (UserDto) request.getSession().getAttribute(USER_PARAM);

        if (userRole == null || userName == null || executor == null) {
            logger.error("Wrong parameters' types, parsing error");
            throw new IllegalArgumentException("Wrong parameters' types, parsing error");
        }

        form.setUserName(userName);
        form.setRole(UserRoleType.valueOf(userRole));
        form.setExecutor(executor);

        return form;
    }
}
