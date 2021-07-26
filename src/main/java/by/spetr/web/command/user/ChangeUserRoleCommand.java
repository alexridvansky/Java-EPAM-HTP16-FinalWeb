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

public class ChangeUserRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();


    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("ChangeUserRoleCommand called");

        String userName = request.getParameter(USER_NAME_PARAM);
        String userRole = request.getParameter(USER_ROLE_PARAM);

        try {
            userService.updateUserRole(userName, UserRoleType.valueOf(userRole));
            logger.debug("'{}' -> '{}'", userName, userRole);
            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

            return new Router(lastPage, Router.RouterType.FORWARD);

        } catch (ServiceException e) {
            // todo: go to some showing_user_error.page
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
