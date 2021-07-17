package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.command.RequestParameter.*;

public class ChangeUserRole implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Change user role method been called");

        String userName = request.getParameter(USER_NAME_PARAM);
        String userRole = request.getParameter(USER_ROLE_PARAM);

        try {
            userService.updateUserRole(userName, UserRoleType.valueOf(userRole));
            logger.debug("'{}' - '{}'", userName, userRole);
        } catch (ServiceException e) {
            logger.error("role for the '{}' not changed", userName);
            // todo: goto error page? show message ?
        }

        Command command = new PrintAllUsersCommand();
        command.execute(request);

        return new Router(PagePath.PRINT_ALL_USERS, Router.RouterType.FORWARD);
    }
}
