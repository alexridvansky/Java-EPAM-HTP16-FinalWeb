package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.command.RequestParameter.USER_STATE_PARAM;
import static by.spetr.web.command.RequestParameter.USER_NAME_PARAM;

public class ChangeUserState implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Change user state method been called");

        String userName = request.getParameter(USER_NAME_PARAM);
        String userState = request.getParameter(USER_STATE_PARAM);

        try {
            userService.updateUserState(userName, UserStateType.valueOf(userState));
            logger.debug("'{}' - '{}'", userName, userState);
        } catch (ServiceException e) {
            logger.error("state for the '{}' not changed", userName);
            // todo: goto error page? show message ?
        }

        Command command = new PrintAllUsersCommand();
        command.execute(request);

        return new Router(PagePath.PRINT_ALL_USERS, Router.RouterType.FORWARD);
    }
}
