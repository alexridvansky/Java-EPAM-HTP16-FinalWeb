package by.spetr.web.model.dao;

import by.spetr.web.model.entity.User;
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
    List<User> getListOfUsers() throws DaoException;

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

    boolean create(User entity);

    boolean delete(User entity);

    boolean delete(long id);

    User update(User entity);
}
