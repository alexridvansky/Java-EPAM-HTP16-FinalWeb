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
    Optional<User> findById(long id) throws DaoException;

    /**
     * Returns {@code Optional<User>} if such was found by username given.
     *
     * @param login is a username used to Log in into the system
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Returns {@code Optional<User>} if such was found by email given.
     *
     * @param email is user email
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Returns {@code Optional<User>} if such was found by phone number given.
     *
     * @param phone is user phone number
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<User> findByPhone(String phone) throws DaoException;

    /**
     * the method is used to user authentication, matching username and password hash with stored in database.
     *
     * @param login username or login
     * @return {@code Optional<User>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<String> findUserPassword(String login) throws DaoException;

    /**
     * is used to create a new user with user.Role = user and user.State = confirmation.
     *
     * @param user             {@code User} class
     * @param hashedPassword   hashed password to the user account
     * @param confirmationCode generated confirmation code to activate the account
     * @return true if user has been created successfully
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    User createUser(User user, String hashedPassword, String confirmationCode) throws DaoException;

    /**
     * is used for updating status of given user
     *
     * @param userId    userId
     * @param userState new {@code UserStateType}
     * @return true if user status has been changed successfully
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean updateState(long userId, UserStateType userState) throws DaoException;

    /**
     * is used for updating status of given user
     *
     * @param userName  {@code User} name
     * @param userState new {@code UserStateType}
     * @return true if user status has been changed successfully
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean updateState(String userName, UserStateType userState) throws DaoException;

    /**
     * is used for updating role of given user
     *
     * @param userId   userId
     * @param userRole new {@code UserRoleType}
     * @return true if user status has been changed successfully
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean updateRole(long userId, UserRoleType userRole) throws DaoException;

    /**
     * method is used to verify is chatId given already exists in the db
     *
     * @param chatId chatId to be checked
     * @return true if chatId given is already present in the db
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean isChatIdExist(long chatId) throws DaoException;

    /**
     * method is used to register any attempt of the user with chatId specific to pass the confirmation procedure
     *
     * @param chatId chatId of the user
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    public void createConfirmAttempt(long chatId) throws DaoException;

    /**
     * method is used to get number of confirmation attempts of the user
     * expired attempts are automatically deleted
     *
     * @param chatId chatId of the user
     * @param periodInHour   period of time in hours attempts will be included and counted
     * @return number of confirmation attempts
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    int findConfirmAttemptCount(long chatId, int periodInHour) throws DaoException;

    /**
     * is used for updating role of given user
     *
     * @param userName {@code User} name
     * @param userRole new {@code UserRoleType}
     * @return true if user status has been changed successfully
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean updateRole(String userName, UserRoleType userRole) throws DaoException;

    /**
     * method stores a new password to the db
     *
     * @param login          username of the user
     * @param hashedPassword a new password to be set up
     * @return true if password changing went successfully
     * @throws DaoException when error occurred on DAO layer
     */
    boolean updateUserPassword(String login, String hashedPassword) throws DaoException;

    /**
     * Returns confirmation secret key
     *
     * @param userId userId
     * @return confirmation code
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<String> getConfirmCode(long userId) throws DaoException;

    /**
     * is used for registration confirmation by matching secret code with stored in the database
     *
     * @param chatId user's telegram chatId
     * @param code   user's secret confirmation code
     * @return true if confirmation code matches with stored one
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean confirm(long chatId, String code) throws DaoException;

    /**
     * is user for getting userid by telegram chatId
     *
     * @param chatId telegram chatId
     * @return userId or 0 in case when no user found
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    long findChatIdByUserId(long chatId) throws DaoException;
}
