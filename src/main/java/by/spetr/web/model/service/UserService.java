package by.spetr.web.model.service;

import by.spetr.web.model.entity.RegistrationFormData;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * declare service methods for {@code User.class}
 */
public interface UserService {

    /**
     * is used to interact with UsedDao layer and create new {@code User}
     *
     * @param registrationFormData - class containing registration form parameters
     * @return - {@code true} if registration parameters were valid and new user been created
     * @throws ServiceException if no data been received from DAO layer or in case when data can't be validated
     */
    public boolean registerUser(RegistrationFormData registrationFormData) throws ServiceException;

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
     * Authenticates user by matching login with password hash
     *
     * @param login - {@code User} login
     * @param passwordHash - {@code User} password hash
     * @return {@code User} if so been found matching login and password hash
     * @throws ServiceException if no data been received from DAO layer
     */
    Optional<User> logIn(String login, String passwordHash) throws ServiceException;

    /**
     * Returns list of all users or empty List<User> if there's no users in the database
     *
     * @return {@code List<User>}
     * @throws ServiceException if no data been received from DAO layer
     */
    List<User> getUserList() throws ServiceException;

    /**
     * is used for updating user status by username
     *
     * @param userName username or login
     * @param userState new user state to be changed to
     * @return true if status been changed successfully
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean updateUserState(String userName, UserStateType userState) throws ServiceException;

    /**
     * is used for updating user status by username
     *
     * @param userName username or login
     * @param userRole new user role to be changed to
     * @return true if status been changed successfully
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean updateUserRole(String userName, UserRoleType userRole) throws ServiceException;
}
