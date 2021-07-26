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

import static by.spetr.web.command.RequestParameter.*;

public class ChangeUserStateCommand implements Command {
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
            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

            return new Router(lastPage, Router.RouterType.FORWARD);

        } catch (ServiceException e) {
            logger.error("state for the '{}' not changed", userName);
            // todo: goto error page? show message ?

            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
