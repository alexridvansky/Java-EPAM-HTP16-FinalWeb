package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.RequestParameter;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.SHOW_USER_LIST_ADM;
import static by.spetr.web.controller.command.RequestParameter.EXCEPTION_MESSAGE_PARAM;
import static by.spetr.web.controller.command.RequestParameter.USER_PARAM;

import javax.servlet.http.HttpServletRequest;

public class ShowUserListAdminCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<User> users = userService.getUserList();
            request.setAttribute(RequestParameter.USER_LIST_PARAM, users);

            return new Router(SHOW_USER_LIST_ADM);

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

        if (executor == null) {
            logger.error("Wrong parameters' types, parsing error");
            throw new IllegalArgumentException("Wrong parameters' types, parsing error");
        }

        form.setExecutor(executor);

        return form;
    }
}
