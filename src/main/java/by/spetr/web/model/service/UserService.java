package by.spetr.web.model.service;

import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.LoginForm;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.form.UserRegForm;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * declare service methods for {@code User.class}
 */
public interface UserService {

    /**
     * is used to interact with UsedDao layer and create new {@code User}
     *
     * @param form - UserRegForm containing user registration parameters
     * @return - {@code true} if registration parameters were valid and new user been created
     * @throws ServiceException if no data been received from DAO layer or in case when data can't be validated
     */
    Optional<UserDto> registerUser(UserRegForm form) throws ServiceException;

    /**
     * is used to check whether username is already taken
     *
     * @param login - username to check
     * @return {@code true} if username given wasn't found in users database
     * @throws ServiceException if no data been received from DAO layer
     */
    boolean isUsernameFree(String login) throws ServiceException;

    /**
     * is used to check whether email is already present in database of users
     *
     * @param email - email to check
     * @return {@code true} if email given hasn't been found in users database
     * @throws ServiceException if no data been received from DAO layer
     */
    boolean isEmailFree(String email) throws ServiceException;

    /**
     * is used to check whether phone number is already present in database of users
     *
     * @param phone - phone number to check
     * @return {@code true} if phone number given hasn't been found in users database
     * @throws ServiceException if no data been received from DAO layer
     */
    boolean isPhoneFree(String phone) throws ServiceException;

    /**
     * Is used for Authentication user through matching login with password hash
     *
     * @param form - LoginForm containing login and password values
     * @return {@code User} if so been found matching login and password hash
     * @throws ServiceException if no data been received from DAO layer
     */
    Optional<UserDto> logIn(LoginForm form) throws ServiceException;

    /**
     * Returns list of all users or empty List<User> if there's no users in the database
     *
     * @return {@code List<User>}
     * @throws ServiceException if no data been received from DAO layer
     */
    List<User> getUserList() throws ServiceException;

    /**
     * Returns User by UserId given
     *
     * @param userId user Id
     * @return Optional<User> or Optional.Empty if such user hasn't been found
     * @throws ServiceException if no data been received from DAO layer
     */
    Optional<User> getUserById(long userId) throws ServiceException;

    /**
     * converts User to UserDto
     *
     * @param user of User.class
     * @return UserDto instance
     */
    UserDto convertToDto(@NonNull User user);

    /**
     * is used for updating user status by username
     *
     * @param form UserForm contains User and new userStatus to be changed to
     * @return true if status been changed successfully
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean updateUserState(UserForm form) throws ServiceException;

    /**
     * is used for updating user status by username
     *
     * @param form UserForm contains User and new userRole to be changed to
     * @return true if status been changed successfully
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean updateUserRole(UserForm form) throws ServiceException;

    /**
     * method is used to verify is chatId given already exists in the db
     *
     * @param chatId chatId to be checked
     * @return true if chatId given is already present in the db
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean isChatIdExist(long chatId) throws ServiceException;

    /**
     * method is used to get number of confirmation attempts of the user
     *
     * @param chatId chatId of the user
     * @param hourPeriod period of time in hours attempts will be counted and expired will be deleted
     * @return number of confirmation attempts
     * @throws ServiceException when error occurred on DAO layer
     */
    int getConfirmAttemptCount(long chatId, int hourPeriod) throws ServiceException;

    /**
     * Returns confirmation secret key
     *
     * @param userId userId
     * @return confirmation code
     * @throws ServiceException when error occurred on DAO layer
     */
    Optional<String> getConfirmCode(long userId) throws ServiceException;

    /**
     * Method is used for user registration confirmation
     *
     * @param chatId telegram chatId
     * @param code   confirmation code to activate the account
     * @return boolean if confirmation went successful and user status changed
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean confirm(Long chatId, String code) throws ServiceException;

    /**
     * this method is used to generate a new password and sent it to the user via telegram bot
     *
     * @param form UserForm contains username (login)
     * @return true if password restoring and storing went successfully
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean recoverUserPassword(UserForm form) throws ServiceException;

    /**
     * method sets up a new password for user by username
     *
     * @param login username of the user
     * @param password a new password to be set up
     * @return true if password changing went successfully
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean updateUserPassword(String login, String password) throws ServiceException;

    static UserService getInstance() {
        return DefaultUserService.getInstance();
    }
}
