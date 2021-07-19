package by.spetr.web.model.dao;

import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.entity.type.*;
import by.spetr.web.model.exception.ConnectionPoolException;
import by.spetr.web.model.exception.DaoException;
import by.spetr.web.model.pool.Connection;
import by.spetr.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class DefaultVehicleDao extends AbstractDao<Vehicle> implements VehicleDao{
    private static final Logger logger = LogManager.getLogger();

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

    @Override
    public List<Vehicle> findAll() throws DaoException {
        logger.info("findAll() method been called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_VEHICLES)) {

            ResultSet resultSet = statement.executeQuery();
            List<Vehicle> vehicles = new ArrayList<>();

            while (resultSet.next()) {
                Vehicle vehicle = extractVehicleFromResultSet(resultSet);
                logger.debug(vehicle);
                vehicles.add(vehicle);
            }

            return vehicles;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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
                resultSet.getLong("vehicle_id"),
                VehicleStateType.valueOf(resultSet.getString("state")),
                resultSet.getString("login"),
                resultSet.getString("make"),
                resultSet.getString("model"),
                Year.of(resultSet.getDate("modelyear").toLocalDate().getYear()),
                resultSet.getBigDecimal("price"),
                VehiclePowertrainType.valueOf(resultSet.getString("powertrain")),
                VehicleTransmissionType.valueOf(resultSet.getString("transmission")),
                VehicleDriveType.valueOf(resultSet.getString("drive")),
                resultSet.getInt("displacement"),
                resultSet.getInt("power"),
                resultSet.getDate("creation_date").toLocalDate()
        );
    }

    // .toLocalDate()

}
