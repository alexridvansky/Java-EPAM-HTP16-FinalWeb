package by.spetr.web.controller.command.user;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.LoginForm;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.INDEX_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;
import static by.spetr.web.controller.command.Router.RouterType.REDIRECT;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = DefaultUserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            LoginForm form = (LoginForm) doForm(request);
            Optional<UserDto> optionalUser = userService.logIn(form);

            if (optionalUser.isPresent()) {
                request.getSession().setAttribute(USER_PARAM, optionalUser.get());
                form.setSuccess(true);
            }

            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, form.getFeedbackMsg());
            request.setAttribute(OPERATION_SUCCESS_PARAM, form.isSuccess());

            return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));

        } catch (ServiceException | IllegalArgumentException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        LoginForm form = new LoginForm();
        String login = request.getParameter(USER_NAME_PARAM);
        String pass = request.getParameter(USER_PASSWORD_PARAM);

        if (login == null || pass == null) {
            throw new IllegalArgumentException("Illegal parameters error");
        }

        form.setLogin(login);
        form.setPass(pass);

        return form;
    }
}