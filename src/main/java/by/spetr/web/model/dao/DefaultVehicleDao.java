package by.spetr.web.model.dao;

import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.entity.type.*;
import by.spetr.web.model.exception.ConnectionPoolException;
import by.spetr.web.model.exception.DaoException;
import by.spetr.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Year;
import java.util.*;

import static by.spetr.web.model.dao.ColumnName.*;

public class DefaultVehicleDao extends AbstractDao<Vehicle> implements VehicleDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String DATABASE_ERROR = "database access error occurred or error parsing resultSet";
    private static final String CONNECTION_GETTING_ERROR = "error of getting connection from ConnectionPool";

    private static final String SQL_SELECT_ALL_VEHICLES
            = "SELECT vehicle_id, state, login, make, model, modelyear, price, powertrain, " +
            "transmission, drive, displacement, power, creation_date " +
            "FROM vehicle " +
            "INNER JOIN vehicle_state ON state_id = vehicle_state_id " +
            "INNER JOIN user ON owner_id = user_id " +
            "INNER JOIN vehicle_make ON make_id = vehicle_make_id " +
            "INNER JOIN vehicle_model ON model_id = vehicle_model_id " +
            "INNER JOIN vehicle_powertrain ON powertrain_id = vehicle_powertrain_id " +
            "INNER JOIN vehicle_transmission ON transmission_id = vehicle_transmission_id " +
            "INNER JOIN vehicle_drive ON drive_id = vehicle_drive_id " +
            "ORDER BY vehicle_id;";
    private static final String SQL_SELECT_VEHICLES_BY_USER_ID
            = "SELECT vehicle_id, state, login, make, model, modelyear, price, powertrain, " +
            "transmission, drive, displacement, power, creation_date " +
            "FROM vehicle " +
            "INNER JOIN vehicle_state ON state_id = vehicle_state_id " +
            "INNER JOIN user ON owner_id = user_id " +
            "INNER JOIN vehicle_make ON make_id = vehicle_make_id " +
            "INNER JOIN vehicle_model ON model_id = vehicle_model_id " +
            "INNER JOIN vehicle_powertrain ON powertrain_id = vehicle_powertrain_id " +
            "INNER JOIN vehicle_transmission ON transmission_id = vehicle_transmission_id " +
            "INNER JOIN vehicle_drive ON drive_id = vehicle_drive_id " +
            "WHERE owner_id = ? " +
            "ORDER BY vehicle_id;";
    private static final String SQL_SELECT_VEHICLE_BY_ID
            = "SELECT vehicle_id, state, login, make, model, modelyear, price, powertrain, " +
            "transmission, drive, displacement, power, creation_date " +
            "FROM vehicle " +
            "INNER JOIN vehicle_state ON state_id = vehicle_state_id " +
            "INNER JOIN user ON owner_id = user_id " +
            "INNER JOIN vehicle_make ON make_id = vehicle_make_id " +
            "INNER JOIN vehicle_model ON model_id = vehicle_model_id " +
            "INNER JOIN vehicle_powertrain ON powertrain_id = vehicle_powertrain_id " +
            "INNER JOIN vehicle_transmission ON transmission_id = vehicle_transmission_id " +
            "INNER JOIN vehicle_drive ON drive_id = vehicle_drive_id " +
            "WHERE vehicle_id = ?;";
    private static final String SQL_UPDATE_VEHICLE_BY_ID
            = "UPDATE vehicle SET state_id = ? WHERE vehicle_id = ?;";
    private static final String SQL_CREATE_NEW_PHOTO_RECORD
            = "INSERT INTO vehicle_gallery (vehicle_id, img_path) " +
            "values (?, ?);";
    private static final String SQL_SELECT_ALL_PHOTO_BY_VEHICLE_ID
            = "SELECT img_path " +
            "FROM vehicle_gallery " +
            "WHERE vehicle_id = ? " +
            "ORDER BY is_preview;";
    private static final String SQL_SELECT_PREVIEW_PHOTO_BY_VEHICLE_ID
            = "SELECT img_path " +
            "FROM vehicle_gallery " +
            "WHERE vehicle_id = ? " +
            "ORDER BY is_preview " +
            "LIMIT 1;";

    @Override
    public List<Vehicle> findAll() throws DaoException {
        logger.info("findAll() method called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_VEHICLES)) {

            List<Vehicle> vehicles = new ArrayList<>();

            while (resultSet.next()) {
                Vehicle vehicle = extractVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);
            }

            return vehicles;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public List<Vehicle> findByUserId(long userId) throws DaoException {
        logger.info("findAllVehiclesByUserId() method called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_VEHICLES_BY_USER_ID)) {

            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            List<Vehicle> vehicles = new ArrayList<>();

            if (resultSet.next()) {
                Vehicle vehicle = extractVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);
            }

            return vehicles;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public Optional<Vehicle> findById(long id) throws DaoException {
        logger.info("findVehicleById() method called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_VEHICLE_BY_ID)) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            Vehicle vehicle = null;

            if (resultSet.next()) {
                vehicle = extractVehicleFromResultSet(resultSet);
            } else {
                logger.info("No data matching the request. Null-User, wrapped in Optional, is to be sent");
            }

            return Optional.ofNullable(vehicle);

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    /**
     * Method creates {@code Vehicle} with {@code ResultSet} given.
     *
     * @param resultSet {@code ResultSet.class}
     * @return {@code Vehicle}
     * @throws SQLException in case of impossibility of extracting all fields
     */
    private Vehicle extractVehicleFromResultSet(ResultSet resultSet) throws SQLException {

        return new Vehicle(
                resultSet.getLong(VEHICLE_ID),
                VehicleStateType.valueOf(resultSet.getString("state")),
                resultSet.getString(VEHICLE_OWNER_LOGIN),
                resultSet.getString(VEHICLE_MAKE),
                resultSet.getString(VEHICLE_MODEL),
                Year.of(resultSet.getDate(VEHICLE_MODEL_YEAR).toLocalDate().getYear()),
                resultSet.getBigDecimal(VEHICLE_PRICE),
                VehiclePowertrainType.valueOf(resultSet.getString(VEHICLE_POWERTRAIN)),
                VehicleTransmissionType.valueOf(resultSet.getString(VEHICLE_TRANSMISSION)),
                VehicleDriveType.valueOf(resultSet.getString(VEHICLE_DRIVE)),
                resultSet.getInt(VEHICLE_DISPLACEMENT),
                resultSet.getInt(VEHICLE_POWER),
                resultSet.getDate(VEHICLE_CREATION_DATE).toLocalDate()
        );
    }

    @Override
    public boolean updateState(long vehicleId, VehicleStateType vehicleState) throws DaoException {
        logger.info("changeRole() method called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_VEHICLE_BY_ID)) {

            statement.setInt(1, vehicleState.getStateId());
            statement.setLong(2, vehicleId);

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
    public boolean createPhoto(long vehicleId, Set<String> cloudinaryPublicIdSet) throws DaoException {
        logger.info("changeRole() method called");
        cloudinaryPublicIdSet.forEach(logger::debug);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_NEW_PHOTO_RECORD)) {

            for (String cloudinaryPublicId : cloudinaryPublicIdSet) {
                statement.setLong(1, vehicleId);
                statement.setString(2, cloudinaryPublicId);
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }

        return true;
    }

    @Override
    public Optional<String> findPreviewById(long vehicleId) throws DaoException {
        logger.info("findPreviewById() method called");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PREVIEW_PHOTO_BY_VEHICLE_ID)) {

            statement.setLong(1, vehicleId);
            ResultSet resultSet = statement.executeQuery();

            String imgPath = null;

            if (resultSet.next()) {
                imgPath = resultSet.getString(IMAGE_PATH);
            }

            return Optional.ofNullable(imgPath);

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }

    @Override
    public List<String> findAllPhotoById(long vehicleId) throws DaoException {
        logger.info("findAllPhotoById() method called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_PHOTO_BY_VEHICLE_ID)) {
            statement.setLong(1, vehicleId);

            ResultSet resultSet = statement.executeQuery();
            List<String> imagePaths = new ArrayList<>();

            if (resultSet.next()) {
                String imagePath = resultSet.getString(IMAGE_PATH);
                imagePaths.add(imagePath);
            }

            return imagePaths;

        } catch (SQLException e) {
            logger.error(DATABASE_ERROR, e);
            throw new DaoException(DATABASE_ERROR, e);
        } catch (ConnectionPoolException e) {
            logger.error(CONNECTION_GETTING_ERROR, e);
            throw new DaoException(CONNECTION_GETTING_ERROR, e);
        }
    }
}
