package by.spetr.web.model.service;

import by.spetr.web.model.dao.DefaultUserDao;
import by.spetr.web.model.dao.UserDao;
import by.spetr.web.model.dto.RegistrationFormDto;
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
    private static final DefaultUserService instance = new DefaultUserService();
    private static final UserDao userDao = new DefaultUserDao();

    private DefaultUserService() {
    }

    public static DefaultUserService getInstance() {
        return instance;
    }

    @Override
    public boolean registerUser(RegistrationFormDto registrationFormDto) throws ServiceException {
        boolean dataValidity = true;

        if (!UserValidator.validateUsername(registrationFormDto.getLogin())) {
            registrationFormDto.setLogin("");
            registrationFormDto.appendComment(USERNAME_INVALID);
            dataValidity = false;
        } else if (!isUsernameFree(registrationFormDto.getLogin())) {
            registrationFormDto.setLogin("");
            registrationFormDto.appendComment(USERNAME_TAKEN);
            dataValidity = false;
        }

        if (!registrationFormDto.getPassword().equals(registrationFormDto.getPasswordRepeat())) {
            registrationFormDto.setPassword("");
            registrationFormDto.setPasswordRepeat("");
            registrationFormDto.appendComment(PASSWORDS_DIFFERENT);
            dataValidity = false;
        } else if (!UserValidator.validatePassword(registrationFormDto.getPassword())) {
            registrationFormDto.setPassword("");
            registrationFormDto.setPasswordRepeat("");
            registrationFormDto.appendComment(PASSWORD_INVALID);
            dataValidity = false;
        }

        if (!UserValidator.validateEmail(registrationFormDto.getEmail())) {
            registrationFormDto.setEmail("");
            registrationFormDto.appendComment(EMAIL_INVALID);
            dataValidity = false;
        } else if (!isEmailFree(registrationFormDto.getEmail())) {
            registrationFormDto.setEmail("");
            registrationFormDto.appendComment(EMAIL_TAKEN);
            dataValidity = false;
        }

        if (!UserValidator.validatePhoneNumber(registrationFormDto.getPhone())) {
            registrationFormDto.setPhone("");
            registrationFormDto.appendComment(PHONE_INVALID);
            dataValidity = false;
        } else if (!isPhoneFree(registrationFormDto.getPhone())) {
            registrationFormDto.setPhone("");
            registrationFormDto.appendComment(PHONE_TAKEN);
            dataValidity = false;
        }

        if (dataValidity) {
            User user = new User(
                    registrationFormDto.getLogin(),
                    UserRoleType.USER,
                    UserStateType.CONFIRM,
                    registrationFormDto.getEmail(),
                    registrationFormDto.getPhone(),
                    LocalDate.now()
            );

            String passHash = BCrypt.hashpw(registrationFormDto.getPassword(), BCrypt.gensalt());
            // todo: plain passwords in regDataForm

            try {
                return userDao.create(user, passHash);
            } catch (DaoException e) {
                logger.error(DAO_ERROR, e);
                throw new ServiceException(DAO_ERROR, e);
            } finally {
                // either afterward creating new user or in case when user is not going to be created, passwords fields
                // are to be cleared unconditionally in order to prevent sending them back to the registration form
                registrationFormDto.setPassword("");
                registrationFormDto.setPasswordRepeat("");
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
    public Optional<User> logIn(String login, String password) throws ServiceException {
        try {
            Optional<String> optionalPassword = userDao.findUserPassword(login);
            logger.debug("optionalPassword {}", optionalPassword);
            Optional<User> optionalUser = Optional.empty();

            logger.debug("check: {}", BCrypt.checkpw(password,optionalPassword.get()));

            if (optionalPassword.isPresent() && BCrypt.checkpw(password,optionalPassword.get())) {
                logger.debug("user {} logged in", login);
                optionalUser = userDao.findUserByLogin(login);
            }

            return optionalUser;
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
            return userDao.updateState(userName, userState);
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
            return userDao.updateRole(userName, userRole);
        } catch (DaoException e) {
            logger.error(DAO_ERROR, e);
            throw new ServiceException(DAO_ERROR, e);
        }    }
}
