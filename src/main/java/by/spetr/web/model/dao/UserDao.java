package by.spetr.web.model.dao;

import by.spetr.web.model.entity.User;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * UserDao Interface defines service methods for {@code User} type objects.
 */
public interface UserDao {
    /**
     * Returns list of all users.
     *
     * @return {@code List<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<User> findAll() throws DaoException;

    /**
     * Returns {@code Optional<User>} if such was found by userId given.
     *
     * @param id is userId
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<User> findUserById(long id) throws DaoException;

    /**
     * Returns {@code Optional<User>} if such was found by username given.
     *
     * @param login is a username used to Log in into the system
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Returns {@code Optional<User>} if such was found by email given.
     *
     * @param email is user email
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Returns {@code Optional<User>} if such was found by phone number given.
     *
     * @param phone is user phone number
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<User> findUserByPhone(String phone) throws DaoException;

    /**
     * the method is used to user authentication, matching username and password hash with stored in database.
     *
     * @param login username or login
     * @param passHash hashed passcode
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<User> logIn(String login, String passHash) throws DaoException;

    /**
     * is used to create a new user with user.Role = user and user.State = confirmation.
     *
     * @param entity {@code User} class
     * @return true if user has been created successfully
     */
    boolean create(User entity, String pass) throws DaoException;

    /**
     * is used for changing status of given user
     *
     * @param userId userId
     * @param userState new {@code UserState.Type}
     * @return true if user status has been changed successfully
     * @throws DaoException if error occurred on DAO layer
     */
    boolean changeState(long userId, UserStateType userState) throws DaoException;

    /**
     * is used for changing status of given user
     *
     * @param userName {@code User} name
     * @param userState new {@code UserState.Type}
     * @return true if user status has been changed successfully
     * @throws DaoException if error occurred on DAO layer
     */
    boolean changeState(String userName, UserStateType userState) throws DaoException;

    /**
     * is used for changing status of given user
     *
     * @param userId userId
     * @param userRole new {@code UserState.Type}
     * @return true if user status has been changed successfully
     * @throws DaoException if error occurred on DAO layer
     */
    boolean changeRole(long userId, UserRoleType userRole) throws DaoException;

    /**
     * is used for changing status of given user
     *
     * @param userName {@code User} name
     * @param userRole new {@code UserState.Type}
     * @return true if user status has been changed successfully
     * @throws DaoException if error occurred on DAO layer
     */
    boolean changeRole(String userName, UserRoleType userRole) throws DaoException;

    boolean delete(User entity);

    boolean delete(long id);

    User update(User entity);
}
