package by.spetr.web.command.list;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePathConstant;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.SignUpData;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultUserService;
import by.spetr.web.model.service.UserService;
import by.spetr.web.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.command.ParamConstant.*;

public class GoToSignUpResultPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = DefaultUserService.getInstance();

        SignUpData signUpData = new SignUpData(
                request.getParameter(USERNAME_PARAM),
                request.getParameter(PASSWORD_PARAM),
                request.getParameter(PASSWORD_AGAIN_PARAM),
                request.getParameter(EMAIL_PARAM),
                request.getParameter(PHONE_PARAM)
        );

        boolean isRegDataValid = false;
        try {
            isRegDataValid = userService.registerUser(signUpData);
        } catch (ServiceException e) {
            e.printStackTrace(); // todo: exc
        }

        // Username validation

        //logger.debug("username: {}", username);
        //boolean isUsernameValid = UserValidator.validateUsername(username);
        //logger.debug("isUsernameValid? {}", isUsernameValid);
        //request.setAttribute("isUsernameValid", isUsernameValid);

        // Username availability check
//        boolean isUsernameFree = false;
//        try {
//            isUsernameFree = userService.isUsernameFree(username);
//        } catch (ServiceException e) {
//            e.printStackTrace(); //todo: exc
//            // todo: go to some error_page
//        }
//        logger.debug("isUsernameFree? {}", isUsernameFree);
//        request.setAttribute("isUsernameFree", isUsernameFree);
//
//        // Is username OK?
//        boolean isUsernameOK = isUsernameValid && isUsernameFree;
//
//
//        // Password validation section
//        String password = request.getParameter(PASSWORD_PARAM);
//        String passwordAgain = request.getParameter(PASSWORD_AGAIN_PARAM);
//
//        // Passwords equality check
//        boolean isPasswordsEqual = password.equals(passwordAgain);
//        logger.debug("isPasswordsEqual? {}", isPasswordsEqual);
//        request.setAttribute("isPasswordsEqual", isPasswordsEqual);
//
//        // Password validation
//        boolean isPasswordValid = UserValidator.validatePassword(password);
//        logger.debug("isPasswordValid? {}", isPasswordValid);
//        request.setAttribute("isPasswordValid", isPasswordValid);
//
//        // is password OK?
//        boolean isPasswordOK = isPasswordsEqual && isPasswordValid;
//
//
//        // Email validation section
//        String email = request.getParameter(EMAIL_PARAM);
//        logger.debug("email: {}", email);
//        boolean isEmailValid = UserValidator.validateEmail(email);
//        logger.debug("isEmailValid? {}", isEmailValid);
//        request.setAttribute("isEmailValid", isEmailValid);
//
//        // Email availability check
//        boolean isEmailFree = false;
//        try {
//            isEmailFree = userService.isEmailFree(email);
//        } catch (ServiceException e) {
//            e.printStackTrace(); // todo: exc
//            // todo: go to some error_page
//
//        }
//        logger.debug("isEmailFree? {}", isEmailFree);
//        request.setAttribute("isEmailFree", isEmailFree);
//
//        // Is Email OK?
//        boolean isEmailOK = isEmailValid && isEmailFree;
//
//        // Phone number check section
//        String phone = request.getParameter(PHONE_PARAM);
//        logger.debug("phone: {}", phone);
//        boolean isPhoneValid = UserValidator.validatePhoneNumber(phone);
//        logger.debug("isPhoneValid? {}", isPhoneValid);
//        request.setAttribute("isPhoneValid", isPhoneValid);
//
//        // Phone availability check
//        boolean isPhoneFree = false;
//        try {
//            isPhoneFree = userService.isPhoneFree(phone);
//        } catch (ServiceException e) {
//            e.printStackTrace(); // todo: exc
//            // todo: go to some error_page
//
//        }
//        request.setAttribute("isPhoneFree", isPhoneFree);
//
//        // Is phone OK?
//        boolean isPhoneOK = isPhoneValid && isPhoneFree;
//
//        // checking all the fields together
//        boolean isAllFieldsCorrect = isUsernameOK && isPasswordOK && isEmailOK && isPhoneOK;
//
//        // Creating new {@code User}
//
//        request.setAttribute("isUserCreated", boolean); //todo: return

        return new Router(PagePathConstant.SIGN_UP_RESULT_PAGE, Router.RouterType.FORWARD);
    }
}
