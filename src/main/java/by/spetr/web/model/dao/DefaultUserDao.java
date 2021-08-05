package by.spetr.web.model.dao;

import by.spetr.web.model.pool.ConnectionPool;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.exception.ConnectionPoolException;
import by.spetr.web.model.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.spetr.web.model.dao.ColumnName.*;

public class DefaultUserDao extends AbstractDao<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String DATABASE_ERROR = "database access error occurred or error parsing resultSet";
    private static final String CONNECTION_GETTING_ERROR = "error of getting connection from ConnectionPool";
    private static final String NO_DATA_MESSAGE = "No data matching the request. Null-User, wrapped in Optional, is to be sent";

    private static final String SQL_SELECT_ALL_USERS
            = "SELECT user_id, role, state, login, email, phone, registration_date " +
            "FROM user " +
            "INNER JOIN user_role ON role_id = user_role_id " +
            "INNER JOIN user_state ON state_id = user_state_id " +
            "ORDER BY user_id;";
    private static final String SQL_SELECT_USER_BY_ID
            = "SELECT user_id, role, state, login, email, phone, registration_date " +
            "FROM user " +
            "INNER JOIN user_role ON role_id = user_role_id " +
            "INNER JOIN user_state ON state_id = user_state_id " +
            "WHERE user_id = ?;";
    private static final String SQL_SELECT_USER_BY_LOGIN
            = "SELECT user_id, role, state, login, email, phone, registration_date " +
            "FROM user " +
            "INNER JOIN user_role ON role_id = user_role_id " +
            "INNER JOIN user_state ON state_id = user_state_id " +
            "WHERE login = ?;";
    private static final String SQL_SELECT_USER_BY_EMAIL
            = "SELECT user_id, role, state, login, email, phone, registration_date " +
            "FROM user " +
            "INNER JOIN user_role ON role_id = user_role_id " +
            "INNER JOIN user_state ON state_id = user_state_id " +
            "WHERE email = ?;";
    private static final String SQL_SELECT_USER_BY_PHONE
            = "SELECT user_id, role, state, login, email, phone, registration_date " +
            "FROM user " +
            "INNER JOIN user_role ON role_id = user_role_id " +
            "INNER JOIN user_state ON state_id = user_state_id " +
            "WHERE phone = ?;";
    private static final String SQL_PASSWORD_FINDER
            = "SELECT pass " +
            "FROM user " +
            "WHERE login = ?;";
    public static final String SQL_PHONE_FINDER
            = "SELECT phone " +
            "FROM user " +
            " WHERE login = ?;";
    private static final String SQL_CREATE_NEW_USER
            = "INSERT INTO user (login, pass, role_id, state_id, email, phone, registration_date) " +
            "values (?, ?, 3, 1, ?, ?, ?);";
    private static final String SQL_UPDATE_STATE_BY_ID
            = "UPDATE user SET state_id = ? WHERE user_id = ?;";
    private static final String SQL_UPDATE_STATE_BY_LOGIN
            = "UPDATE user SET state_id = ? WHERE login = ?;";
    private static final String SQL_UPDATE_ROLE_BY_ID
            = "UPDATE user SET role_id = ? WHERE user_id = ?;";
    private static final String SQL_UPDATE_ROLE_BY_LOGIN
            = "UPDATE user SET role_id = ? WHERE login = ?;";

    @Override
    public List<User> findAll() throws DaoException {
        logger.info("findAll() method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {

            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public Optional<User> findUserById(long id) throws DaoException {
        logger.info("findById() method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {

            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            } else {
                logger.info(NO_DATA_MESSAGE);
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        logger.info("findUserByLogin() method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {

            statement.setString(1,login);

            ResultSet resultSet = statement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            } else {
                logger.info(NO_DATA_MESSAGE);
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        logger.info("findUserByEmail() method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL)) {

            statement.setString(1,email);

            ResultSet resultSet = statement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            } else {
                logger.info(NO_DATA_MESSAGE);
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public Optional<User> findUserByPhone(String phone) throws DaoException {
        logger.info("findUserByPhone() method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_PHONE)) {

            statement.setString(1,phone);

            ResultSet resultSet = statement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            } else {
                logger.info(NO_DATA_MESSAGE);
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    /**
     * Method creates {@code User} with {@code ResultSet} given.
     *
     * @param resultSet ResultSet.class
     * @return {@code User}
     * @throws SQLException in case of impossibility of extracting all fields
     */
    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {

        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString(USER_LOGIN),
                UserRoleType.valueOf(resultSet.getString("role")),
                UserStateType.valueOf(resultSet.getString("state")),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getDate("registration_date").toLocalDate()
        );
    }

    @Override
    public Optional<String> findUserPassword(String login) throws DaoException {
        logger.info("findUserPassword() method been called with {}", login);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_PASSWORD_FINDER)){

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            String password = null;

            if (resultSet.next()) {
                password = resultSet.getString(USER_PASSHASH);
            } else {
                logger.info("no such user in the database");
            }

            return Optional.ofNullable(password);

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public Optional<String> findUserPhoneByName(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_PHONE_FINDER)) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            String phone = null;

            if (resultSet.next()) {
                phone = resultSet.getString(USER_PHONE);
            }

            return Optional.ofNullable(phone);

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public boolean create(User entity, String pass) throws DaoException {
        logger.info("create() method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_NEW_USER)) {

            statement.setString(1, entity.getLogin());
            statement.setString(2, pass);
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPhone());
            statement.setDate(5, Date.valueOf(LocalDate.now()));

            int result = statement.executeUpdate();

            logger.debug("fields updated: {}", result);

            return result > 0;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public boolean updateState(long userId, UserStateType userState) throws DaoException {
        logger.info("Update user_state by user_id method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATE_BY_ID)) {

            // check if such user exists, if not - exit
            Optional<User> optionalUser = findUserById(userId);
            if (optionalUser.isEmpty()) {
                StringFormattedMessage str = new StringFormattedMessage("user '{}' not found", userId);
                logger.error(str);
                throw new DaoException(str.toString());
            }

            statement.setInt(1, userState.getStateId());
            statement.setLong(2, userId);

            int result = statement.executeUpdate();

            logger.debug("fields updated: {}", result);

            return result > 0;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public boolean updateState(String userName, UserStateType userState) throws DaoException {
        logger.info("Update user_state by user_id method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATE_BY_LOGIN)) {

            statement.setInt(1, userState.getStateId());
            statement.setString(2, userName);

            int result = statement.executeUpdate();

            logger.debug("fields updated: {}", result);

            return result > 0;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public boolean updateRole(long userId, UserRoleType userRole) throws DaoException {
        logger.info("Update user_role by user_id method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ROLE_BY_ID)) {

            statement.setInt(1, userRole.getRoleId());
            statement.setLong(2, userId);

            int result = statement.executeUpdate();

            logger.debug("fields updated: {}", result);

            return result > 0;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public boolean updateRole(String userName, UserRoleType userRole) throws DaoException {
        logger.info("Update user_role by user_name method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ROLE_BY_LOGIN)) {

            statement.setInt(1, userRole.getRoleId());
            statement.setString(2, userName);

            int result = statement.executeUpdate();

            logger.debug("fields updated: {}", result);

            return result > 0;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public User update(User entity) {


        return null;  // todo:
    }

    @Override
    public boolean delete(User entity) {


        return false;  // todo:
    }

    @Override
    public boolean delete(long id) {

        return false;  // todo:
    }
}
