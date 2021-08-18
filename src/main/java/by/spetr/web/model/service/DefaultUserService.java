package by.spetr.web.model.service;

import by.spetr.web.model.dao.DefaultUserDao;
import by.spetr.web.model.dao.UserDao;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.form.LoginForm;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.form.UserRegForm;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.exception.DaoException;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.util.BCrypt;
import by.spetr.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.spetr.web.model.service.ServiceMessageList.*;

public class DefaultUserService implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final AccessControlService accessControlService = AccessControlService.getInstance();
    private static final UserDao userDao = new DefaultUserDao();
    private static DefaultUserService instance;

    private DefaultUserService() {
    }

    public static DefaultUserService getInstance() {
        if (instance == null) {
            instance = new DefaultUserService();
        }
        return instance;
    }

    @Override
    public Optional<UserDto> registerUser(UserRegForm form) throws ServiceException {
        if (!UserValidator.validateUsername(form.getLogin())) {
            form.setLogin("");
            form.setFeedbackMsg(USERNAME_INVALID);
        } else if (!isUsernameFree(form.getLogin())) {
            form.setLogin("");
            form.setFeedbackMsg(USERNAME_TAKEN);
        } else if (!form.getPassword().equals(form.getPasswordRepeat())) {
            form.setPassword("");
            form.setPasswordRepeat("");
            form.setFeedbackMsg(PASSWORDS_DIFFERENT);
        } else if (!UserValidator.validatePassword(form.getPassword())) {
            form.setPassword("");
            form.setPasswordRepeat("");
            form.setFeedbackMsg(PASSWORD_INVALID);
        } else if (!UserValidator.validateEmail(form.getEmail())) {
            form.setEmail("");
            form.setFeedbackMsg(EMAIL_INVALID);
        } else if (!isEmailFree(form.getEmail())) {
            form.setEmail("");
            form.setFeedbackMsg(EMAIL_TAKEN);
        } else if (!UserValidator.validatePhoneNumber(form.getPhone())) {
            form.setPhone("");
            form.setFeedbackMsg(PHONE_INVALID);
        } else if (!isPhoneFree(form.getPhone())) {
            form.setPhone("");
            form.setFeedbackMsg(PHONE_TAKEN);
        } else {
            User user = new User(
                    form.getLogin(),
                    UserRoleType.USER,
                    UserStateType.CONFIRM,
                    form.getEmail(),
                    form.getPhone(),
                    LocalDate.now()
            );

            String hashedPassword = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt());

            try {
                user = userDao.createUser(user, hashedPassword);
                UserDto userDto = new UserDto();
                userDto.setUserId(user.getUserId());
                userDto.setLogin(user.getLogin());
                userDto.setRole(user.getRole());
                userDto.setState(user.getState());

                return Optional.of(userDto);

            } catch (DaoException e) {
                logger.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage());
            } finally {
                // either afterward creating new user or in case when user is not going to be created, passwords fields
                // are to be cleared unconditionally in order to prevent sending them back to the registration form
                form.setPassword("");
                form.setPasswordRepeat("");
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean isUsernameFree(String login) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findByLogin(login);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public boolean isEmailFree(String email) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findByEmail(email);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public boolean isPhoneFree(String phone) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findByPhone(phone);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public Optional<UserDto> logIn(LoginForm form) throws ServiceException {
        try {
            Optional<String> optionalPassword = userDao.findUserPassword(form.getLogin());

            if (optionalPassword.isEmpty()) {
                form.setFeedbackMsg("Login and password doesn't match any user");
                return Optional.empty();
            }

            Optional<User> optionalUser = Optional.empty();
            UserDto userDto = null;

            boolean passCheckResult = BCrypt.checkpw(form.getPass(), optionalPassword.get());
            logger.debug("password check: {}", passCheckResult);

            if (passCheckResult) {
                logger.debug("userName {} matches the password", form.getLogin());
                optionalUser = userDao.findByLogin(form.getLogin());
            }

            if (optionalUser.isEmpty()) {
                form.setFeedbackMsg("Login error");
                return Optional.empty();
            }

                userDto = new UserDto();
                userDto.setLogin(optionalUser.get().getLogin());
                userDto.setUserId(optionalUser.get().getUserId());
                userDto.setRole(optionalUser.get().getRole());
                userDto.setState(optionalUser.get().getState());

                form.setFeedbackMsg("User '" + form.getLogin() + "' has entered successfully");
                form.setSuccess(true);

                return Optional.of(userDto);

        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public List<User> getUserList() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public Optional<User> getUserById(long userId) throws ServiceException {
        try {
            return userDao.findById(userId);
        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public boolean updateUserState(UserForm form) throws ServiceException {
        if (!accessControlService.updateUserState(form)) {
            logger.error("Unauthorised access attempt");

            throw new ServiceException("Unauthorised access attempt");
        }

        try {
            if (isUsernameFree(form.getUserName())) {
                FormattedMessage errorMsg = new FormattedMessage("user '{}' not found", form.getUserName());
                logger.error(errorMsg);
                throw new ServiceException(errorMsg.toString());
            }
            if (userDao.updateState(form.getUserName(), form.getState())) {
                form.setFeedbackMsg("User state changed");
                form.setSuccess(true);
                return true;
            } else {
                form.setFeedbackMsg("User state not changed");
                return false;
            }
        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public boolean updateUserRole(UserForm form) throws ServiceException {
        try {
            if (accessControlService.updateUserRole(form)) {
                logger.debug("access allowed");
            }
            if (userDao.updateRole(form.getUserName(), form.getRole())) {
                form.setFeedbackMsg("Role changed");
                form.setSuccess(true);
                return true;
            } else {
                form.setFeedbackMsg("Role changing error");
                return false;
            }
        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }
}
