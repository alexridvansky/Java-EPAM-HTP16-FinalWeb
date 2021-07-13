package by.spetr.web.model.dao;

import by.spetr.web.model.pool.ConnectionPool;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.exception.ConnectionPoolException;
import by.spetr.web.model.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultUserDao extends AbstractDAO<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_SELECT_ALL_USERS
            = "SELECT user_id, role, state, login, email, phone, registration_date " +
            "FROM user " +
            "INNER JOIN user_role ON role_id = user_role_id " +
            "INNER JOIN user_state ON state_id = user_state_id;";
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
    private static final String SQL_LOGIN_PASSWORD_MATCHER
            = "SELECT user_id, role, state, login, pass, email, phone, registration_date " +
            "FROM user " +
            "INNER JOIN user_role ON role_id = user_role_id " +
            "INNER JOIN user_state ON state_id = user_state_id " +
            "WHERE login = ? AND pass = ?;";
    private static final String SQL_ADD_NEW_USER
            = "INSERT INTO user (role_id, state_id, login, pass, email, phone, registration_date) " +
            "values (3, 1, 'spetr', 'spetr1', 'spetr@tut.by', '+375296290069', '2019-01-01');";

    @Override
    public List<User> getListOfUsers() throws DaoException {
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
            logger.error("database access error occurs or this method is called on a closed connection", e);
            throw new DaoException("database access error occurs or this method is called on a closed connection", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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
                logger.info("No data matching the request. Null-User, wrapped in Optional, is to be sent");
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error("database access error occurs or this method is called on a closed connection", e);
            throw new DaoException("database access error occurs or this method is called on a closed connection", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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
                logger.info("No data matching the request. Null-User, wrapped in Optional, is to be sent");
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error("database access error occurs or this method is called on a closed connection", e);
            throw new DaoException("database access error occurs or this method is called on a closed connection", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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
                logger.info("No data matching the request. Null-User, wrapped in Optional, is to be sent");
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error("database access error occurs or this method is called on a closed connection", e);
            throw new DaoException("database access error occurs or this method is called on a closed connection", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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
                logger.info("No data matching the request. Null-User, wrapped in Optional, is to be sent");
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error("database access error occurs or this method is called on a closed connection", e);
            throw new DaoException("database access error occurs or this method is called on a closed connection", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public Optional<User> logIn(String login, String passHash) throws DaoException {
        logger.info("logIn() method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_LOGIN_PASSWORD_MATCHER)){

            statement.setString(1, login);
            statement.setString(2, passHash);

            ResultSet resultSet = statement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            } else {
                logger.info("No data matching the request, null-User, wrapped in Optional, is to be sent");
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error("database access error occurs or this method is called on a closed connection", e);
            throw new DaoException("database access error occurs or this method is called on a closed connection", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    /**
     * Method creates {@code User} with {@code ResultSet} given.
     *
     * @param resultSet {@code ResultSet.class}
     * @return {@code User}
     * @throws {@code SQLException} in case of impossibility to extract all fields
     */
    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {

        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("login"),
                UserRoleType.valueOf(resultSet.getString("role")),
                UserStateType.valueOf(resultSet.getString("state")),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getDate("registration_date").toLocalDate()
        );
    }

    @Override
    public boolean create(User entity) {

        return false; // todo:
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
