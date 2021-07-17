package by.spetr.web.model.service;

import by.spetr.web.model.dao.DefaultUserDao;
import by.spetr.web.model.dao.UserDao;
import by.spetr.web.model.entity.RegistrationFormData;
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

import static by.spetr.web.model.service.UserServiceMessage.*;

public class DefaultUserService implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final DefaultUserService instance = new DefaultUserService();
    private static final UserDao userDao = new DefaultUserDao();

    private DefaultUserService() {
    }

    public static DefaultUserService getInstance() {
        return instance;
    }

    @Override
    public boolean registerUser(RegistrationFormData registrationFormData) throws ServiceException {
        boolean dataValidity = true;

        if (!UserValidator.validateUsername(registrationFormData.getLogin())) {
            registrationFormData.setLogin("");
            registrationFormData.appendComment(USERNAME_INVALID);
            dataValidity = false;
        } else if (!isUsernameFree(registrationFormData.getLogin())) {
            registrationFormData.setLogin("");
            registrationFormData.appendComment(USERNAME_TAKEN);
            dataValidity = false;
        }
        if (!registrationFormData.getPassword().equals(registrationFormData.getPasswordRepeat())) {
            registrationFormData.setPassword("");
            registrationFormData.setPasswordRepeat("");
            registrationFormData.appendComment(PASSWORDS_DIFFERENT);
            dataValidity = false;
        } else if (!UserValidator.validatePassword(registrationFormData.getPassword())) {
            registrationFormData.setPassword("");
            registrationFormData.setPasswordRepeat("");
            registrationFormData.appendComment(PASSWORD_INVALID);
            dataValidity = false;
        }
        if (!UserValidator.validateEmail(registrationFormData.getEmail())) {
            registrationFormData.setEmail("");
            registrationFormData.appendComment(EMAIL_INVALID);
            dataValidity = false;
        } else if (!isEmailFree(registrationFormData.getEmail())) {
            registrationFormData.setEmail("");
            registrationFormData.appendComment(EMAIL_TAKEN);
            dataValidity = false;
        }
        if (!UserValidator.validatePhoneNumber(registrationFormData.getPhone())) {
            registrationFormData.setPhone("");
            registrationFormData.appendComment(PHONE_INVALID);
            dataValidity = false;
        } else if (!isPhoneFree(registrationFormData.getPhone())) {
            registrationFormData.setPhone("");
            registrationFormData.appendComment(PHONE_TAKEN);
            dataValidity = false;
        }
        if (dataValidity) {
            User user = new User(
                    registrationFormData.getLogin(),
                    UserRoleType.USER,
                    UserStateType.CONFIRM,
                    registrationFormData.getEmail(),
                    registrationFormData.getPhone(),
                    LocalDate.now()
            );

            // generating hash
            String passHash = BCrypt.hashpw(registrationFormData.getPassword(), BCrypt.gensalt());
            // todo: clear passwords in regDataForm

            try {
                return userDao.create(user, registrationFormData.getPassword());
            } catch (DaoException e) {
                logger.error(DAO_ERROR, e);
                throw new ServiceException(DAO_ERROR, e);
            } finally {
                // either afterward creating new user or in case when user is not going to be created, passwords fields
                // are to be cleared unconditionally in order to prevent sending them back to the registration form
                registrationFormData.setPassword("");
                registrationFormData.setPasswordRepeat("");
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean isUsernameFree(String login) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findUserByLogin(login);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error(DAO_ERROR, e);
            throw new ServiceException(DAO_ERROR, e);
        }
    }

    @Override
    public boolean isEmailFree(String email) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error(DAO_ERROR, e);
            throw new ServiceException(DAO_ERROR, e);
        }
    }

    @Override
    public boolean isPhoneFree(String phone) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findUserByPhone(phone);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error(DAO_ERROR, e);
            throw new ServiceException(DAO_ERROR, e);
        }
    }

    @Override
    public Optional<User> logIn(String login, String passwordHash) throws ServiceException {
        try {
            return userDao.logIn(login, passwordHash);
        } catch (DaoException e) {
            logger.error(DAO_ERROR, e);
            throw new ServiceException(DAO_ERROR, e);
        }
    }

    @Override
    public List<User> getUserList() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error(DAO_ERROR, e);
            throw new ServiceException(DAO_ERROR, e);
        }
    }

    @Override
    public boolean updateUserState(String userName, UserStateType userState) throws ServiceException {
        try {
            if (isUsernameFree(userName)) {
                FormattedMessage errorMsg = new FormattedMessage("user '{}' not found", userName);
                logger.error(errorMsg);
                throw new ServiceException(errorMsg.toString());
            }
            return userDao.changeState(userName, userState);
        } catch (DaoException e) {
            logger.error(DAO_ERROR, e);
            throw new ServiceException(DAO_ERROR, e);
        }
    }

    @Override
    public boolean updateUserRole(String userName, UserRoleType userRole) throws ServiceException {
        try {
            if (isUsernameFree(userName)) {
                FormattedMessage errorMsg = new FormattedMessage("user '{}' not found", userName);
                logger.error(errorMsg);
                throw new ServiceException(errorMsg.toString());
            }
            return userDao.changeRole(userName, userRole);
        } catch (DaoException e) {
            logger.error(DAO_ERROR, e);
            throw new ServiceException(DAO_ERROR, e);
        }    }
}
