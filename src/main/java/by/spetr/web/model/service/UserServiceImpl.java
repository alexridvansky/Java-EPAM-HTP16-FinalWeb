package by.spetr.web.model.service;

import by.spetr.web.model.dao.DefaultUserDao;
import by.spetr.web.model.dao.UserDao;
import by.spetr.web.model.entity.SignUpData;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.DaoException;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    private static final Logger logger = LogManager.getLogger();
    private static final UserServiceImpl instance = new UserServiceImpl();
    private static final UserDao userDao = new DefaultUserDao();

    private UserServiceImpl(){
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean registerUser(SignUpData signUpData) {
        String comment = "";
        StringBuilder builder = new StringBuilder(comment);

        boolean isUserNameValid = UserValidator.validateUsername(signUpData.getLogin());
        if (!isUserNameValid) {
            signUpData.setLogin("");
            builder.append("login isn't valid");
        }

        return false; // todo:
    }

    @Override
    public boolean isUsernameFree(String login) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findUserByLogin(login);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error("Data can't be received from DAO layer", e);
            throw new ServiceException("Data can't be received from DAO layer", e);
        }
    }

    @Override
    public boolean isEmailFree(String email) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error("Data can't be received from DAO layer", e);
            throw new ServiceException("Data can't be received from DAO layer", e);
        }
    }

    @Override
    public boolean isPhoneFree(String phone) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findUserByPhone(phone);
            return optionalUser.isEmpty();
        } catch (DaoException e) {
            logger.error("Data can't be received from DAO layer", e);
            throw new ServiceException("Data can't be received from DAO layer", e);
        }
    }

    @Override
    public Optional<User> logIn(String login, String passwordHash) throws ServiceException {
        try {
            return userDao.logIn(login, passwordHash);
        } catch (DaoException e) {
            logger.error("Data can't be received from DAO layer", e);
            throw new ServiceException("Data can't be received from DAO layer", e);
        }
    }

    @Override
    public List<User> getUserList() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("Data can't be received from DAO layer", e);
            throw new ServiceException("Data can't be received from DAO layer", e);
        }
    }
}