package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.form.DefaultForm;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.MAKE_CREATION_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;
import static by.spetr.web.model.entity.type.UserRoleType.MODERATOR;
import static by.spetr.web.model.entity.type.UserRoleType.ROOT;

public class ShowMakeCreationPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("ShowMakeCreationPageCommand() called");

        try {
            doForm(request);

            return new Router(MAKE_CREATION_PAGE);

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(ERROR_PAGE);
        }

    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        DefaultForm form = new DefaultForm();
        UserDto executor = (UserDto) request.getSession().getAttribute(USER_PARAM);
        if (executor == null || (!(executor.getRole() == ROOT || executor.getRole() == MODERATOR))) {
            logger.error("Wrong parameters' types, parsing error");
            throw new IllegalArgumentException("Wrong parameters' types, parsing error");
        }

        return form;
    }
}
