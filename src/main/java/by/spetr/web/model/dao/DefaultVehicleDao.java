package by.spetr.web.model.dao;

import by.spetr.web.model.entity.*;
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

    private static final String SQL_SELECT_ALL_VEHICLES
            = "SELECT vehicle_id, state, owner_id, vehicle_model_id, model, vehicle_make_id, make, modelyear, " +
            "mileage, vehicle_color_id, color, price, powertrain, transmission, drive, displacement, power, comment, " +
            "creation_date " +
            "FROM vehicle " +
            "INNER JOIN vehicle_state ON state_id = vehicle_state_id " +
            "INNER JOIN vehicle_model ON model_id = vehicle_model_id " +
            "INNER JOIN vehicle_make ON vehicle_model.make_id = vehicle_make_id " +
            "INNER JOIN vehicle_color ON color_id = vehicle_color_id " +
            "INNER JOIN vehicle_powertrain ON powertrain_id = vehicle_powertrain_id " +
            "INNER JOIN vehicle_transmission ON transmission_id = vehicle_transmission_id " +
            "INNER JOIN vehicle_drive ON drive_id = vehicle_drive_id " +
            "ORDER BY vehicle_id;";
    private static final String SQL_SELECT_ALL_PUBLIC_VEHICLES
            = "SELECT vehicle_id, state, owner_id, vehicle_model_id, model, vehicle_make_id, make, modelyear, " +
            "mileage, vehicle_color_id, color, price, powertrain, transmission, drive, displacement, power, comment, " +
            "creation_date " +
            "FROM vehicle " +
            "INNER JOIN vehicle_state ON state_id = vehicle_state_id " +
            "INNER JOIN vehicle_model ON model_id = vehicle_model_id " +
            "INNER JOIN vehicle_make ON vehicle_model.make_id = vehicle_make_id " +
            "INNER JOIN vehicle_color ON color_id = vehicle_color_id " +
            "INNER JOIN vehicle_powertrain ON powertrain_id = vehicle_powertrain_id " +
            "INNER JOIN vehicle_transmission ON transmission_id = vehicle_transmission_id " +
            "INNER JOIN vehicle_drive ON drive_id = vehicle_drive_id " +
            "INNER JOIN user ON vehicle.owner_id = user.user_id " +
            "WHERE state = 'ENABLED' " +
            "AND user.state_id = 2 " +
            "ORDER BY vehicle_id;";
    private static final String SQL_SELECT_VEHICLES_BY_USER_ID
            = "SELECT vehicle_id, state, owner_id, vehicle_model_id, model, vehicle_make_id, make, modelyear, " +
            "mileage, vehicle_color_id, color, price, powertrain, transmission, drive, displacement, power, comment, " +
            "creation_date " +
            "FROM vehicle " +
            "INNER JOIN vehicle_state ON state_id = vehicle_state_id " +
            "INNER JOIN vehicle_model ON model_id = vehicle_model_id " +
            "INNER JOIN vehicle_make ON make_id = vehicle_make_id " +
            "INNER JOIN vehicle_color ON color_id = vehicle_color_id " +
            "INNER JOIN vehicle_powertrain ON powertrain_id = vehicle_powertrain_id " +
            "INNER JOIN vehicle_transmission ON transmission_id = vehicle_transmission_id " +
            "INNER JOIN vehicle_drive ON drive_id = vehicle_drive_id " +
            "WHERE owner_id = ? " +
            "AND state IN ('ENABLED', 'DISABLED', 'MODERATION') " +
            "ORDER BY vehicle_id;";
    private static final String SQL_FIND_VEHICLE_COUNT_BY_USER_ID
            = "SELECT COUNT(*) " +
            "FROM vehicle " +
            "WHERE owner_id = ?;";
    private static final String SQL_SELECT_VEHICLE_BY_ID
            = "SELECT vehicle_id, state, owner_id, vehicle_model_id, model, vehicle_make_id, make, modelyear, " +
            "mileage, vehicle_color_id, color, price, powertrain, transmission, drive, displacement, power, comment, " +
            "creation_date " +
            "FROM vehicle " +
            "INNER JOIN vehicle_state ON state_id = vehicle_state_id " +
            "INNER JOIN vehicle_model ON model_id = vehicle_model_id " +
            "INNER JOIN vehicle_make ON make_id = vehicle_make_id " +
            "INNER JOIN vehicle_color ON color_id = vehicle_color_id " +
            "INNER JOIN vehicle_powertrain ON powertrain_id = vehicle_powertrain_id " +
            "INNER JOIN vehicle_transmission ON transmission_id = vehicle_transmission_id " +
            "INNER JOIN vehicle_drive ON drive_id = vehicle_drive_id " +
            "WHERE vehicle_id = ?;";
    private static final String SQL_IS_MAKE_EXIST_BY_NAME
            = "SELECT EXISTS" +
            "(SELECT * " +
            "FROM vehicle_make " +
            "WHERE make = ?);";
    private static final String SQL_IS_MAKE_EXIST_BY_ID
            = "SELECT EXISTS( " +
            "SELECT vehicle_make_id " +
            "FROM vehicle_make " +
            "WHERE vehicle_make_id = ?);";
    private static final String SQL_SELECT_ALL_MAKES
            = "SELECT vehicle_make_id, make " +
            "FROM vehicle_make " +
            "ORDER BY make;";
    private static final String SQL_SELECT_MODEL_BY_NAME
            = "SELECT * " +
            "FROM vehicle_model " +
            "WHERE model = ?;";
    private static final String SQL_SELECT_MODEL_BY_ID
            = "SELECT * " +
            "FROM vehicle_model " +
            "WHERE vehicle_model_id = ?;";
    private static final String SQL_SELECT_POWERTRAIN_BY_ID
            = "SELECT * " +
            "FROM vehicle_powertrain " +
            "WHERE vehicle_powertrain_id = ?;";
    private static final String SQL_SELECT_TRANSMISSION_BY_ID
            = "SELECT * " +
            "FROM vehicle_transmission " +
            "WHERE vehicle_transmission_id = ?;";
    private static final String SQL_SELECT_DRIVE_BY_ID
            = "SELECT * " +
            "FROM vehicle_drive " +
            "WHERE vehicle_drive_id = ?;";
    private static final String SQL_SELECT_COLOR_BY_ID
            = "SELECT * " +
            "FROM vehicle_color " +
            "WHERE vehicle_color_id = ?;";
    private static final String SQL_SELECT_COLOR_BY_NAME
            = "SELECT * " +
            "FROM vehicle_color " +
            "WHERE color = ?;";
    private static final String SQL_SELECT_ALL_MODELS
            = "SELECT vehicle_model_id, model, vehicle_make_id, make " +
            "FROM vehicle_model " +
            "INNER JOIN vehicle_make ON vehicle_model.make_id = vehicle_make_id " +
            "ORDER BY model;";
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
    private static final String SQL_FIND_ALL_OPTIONS
            = "SELECT * " +
            "FROM vehicle_option " +
            "ORDER BY vehicle_option_id;";
    private static final String SQL_FIND_ALL_COLORS
            = "SELECT * " +
            "FROM vehicle_color " +
            "ORDER BY color;";
    private static final String SQL_SELECT_OPTIONS_BY_VEHICLE_ID
            = "SELECT option_id, option_description " +
            "FROM vehicle_option_map " +
            "INNER JOIN vehicle_option ON option_id = vehicle_option_id " +
            "WHERE vehicle_id = ?;";
    private static final String SQL_INSERT_VEHICLE_MAKE
            = "INSERT INTO vehicle_make (make)" +
            "VALUES (?);";
    private static final String SQL_INSERT_VEHICLE_MODEL
            = "INSERT INTO vehicle_model (model, make_id) " +
            "VALUES (?, ?);";
    private static final String SQL_INSERT_VEHICLE_COLOR
            = "INSERT INTO vehicle_color (color)" +
            "VALUES (?);";
    private static final String SQL_INSERT_VEHICLE
            = "INSERT INTO vehicle (state_id, owner_id, model_id, modelyear, mileage, color_id, price, powertrain_id, " +
            "transmission_id, drive_id, displacement, power, comment) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_INSERT_OPTION
            = "INSERT INTO vehicle_option_map (vehicle_id, option_id) " +
            "VALUES (?, ?);";

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
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public List<Vehicle> findAllPublic() throws DaoException {
        logger.info("findAll() method called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PUBLIC_VEHICLES)) {

            List<Vehicle> vehicles = new ArrayList<>();

            while (resultSet.next()) {
                Vehicle vehicle = extractVehicleFromResultSet(resultSet);
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

    @Override
    public List<Vehicle> findByUserId(long userId) throws DaoException {
        logger.info("findAllVehiclesByUserId() method called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_VEHICLES_BY_USER_ID)) {

            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            List<Vehicle> vehicles = new ArrayList<>();

            while (resultSet.next()) {
                Vehicle vehicle = extractVehicleFromResultSet(resultSet);
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

    @Override
    public int findAdNumberByUserId(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_VEHICLE_COUNT_BY_USER_ID)) {

            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(COUNT);
            }

            return count;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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
        VehicleBuilder vehicleBuilder = VehicleBuilder.getInstance();

        VehicleMake vehicleMake = new VehicleMake();
        vehicleMake.setMakeId(resultSet.getInt(VEHICLE_MAKE_ID));
        vehicleMake.setValue(resultSet.getString(VEHICLE_MAKE));

        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setModelId(resultSet.getInt(VEHICLE_MODEL_ID));
        vehicleModel.setValue(resultSet.getString(VEHICLE_MODEL));
        vehicleModel.setMake(vehicleMake);

        VehicleColor vehicleColor = new VehicleColor();
        vehicleColor.setColorId(resultSet.getInt(VEHICLE_COLOR_ID));
        vehicleColor.setValue(resultSet.getString(VEHICLE_COLOR));

        return vehicleBuilder
                .id(resultSet.getLong(VEHICLE_ID))
                .state(VehicleStateType.valueOf(resultSet.getString(VEHICLE_STATE)))
                .ownerId(resultSet.getLong(VEHICLE_OWNER_ID))
                .model(vehicleModel)
                .modelYear(Year.of(resultSet.getDate(VEHICLE_MODEL_YEAR).toLocalDate().getYear()))
                .mileage(resultSet.getInt(VEHICLE_MILEAGE))
                .color(vehicleColor)
                .price(resultSet.getBigDecimal(VEHICLE_PRICE))
                .powertrain(VehiclePowertrainType.valueOf(resultSet.getString(VEHICLE_POWERTRAIN)))
                .transmission(VehicleTransmissionType.valueOf(resultSet.getString(VEHICLE_TRANSMISSION)))
                .drive(VehicleDriveType.valueOf(resultSet.getString(VEHICLE_DRIVE)))
                .displacement(resultSet.getInt(VEHICLE_DISPLACEMENT))
                .power(resultSet.getInt(VEHICLE_POWER))
                .comment(resultSet.getString(VEHICLE_COMMENT))
                .dateCreated(resultSet.getDate(VEHICLE_CREATION_DATE).toLocalDate())
                .build();
    }

    @Override
    public boolean isMakeExist(String make) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_MAKE_EXIST_BY_NAME)) {
            statement.setString(1, make);

            ResultSet resultSet = statement.executeQuery();
            int result = 0;
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }

            return result > 0;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean isMakeExist(int makeId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_MAKE_EXIST_BY_ID)) {
            statement.setInt(1, makeId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public List<VehicleMake> findMakeList() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MAKES)) {
            List<VehicleMake> makes = new ArrayList<>();

            while (resultSet.next()) {
                int makeId = resultSet.getInt(VEHICLE_MAKE_ID);
                String makeValue = resultSet.getString(VEHICLE_MAKE);

                VehicleMake make = new VehicleMake();
                make.setMakeId(makeId);
                make.setValue(makeValue);

                makes.add(make);
            }

            return makes;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean insertMake(String make) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_VEHICLE_MAKE)) {

            preparedStatement.setString(1, make.toUpperCase());

            return (preparedStatement.executeUpdate() == 1);

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public List<VehicleModel> findModelList() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MODELS)) {

            List<VehicleModel> models = new ArrayList<>();

            while (resultSet.next()) {
                int modelId = resultSet.getInt(VEHICLE_MODEL_ID);
                String modelValue = resultSet.getString(VEHICLE_MODEL);
                int makeId = resultSet.getInt(VEHICLE_MAKE_ID);
                String makeValue = resultSet.getString(VEHICLE_MAKE);

                VehicleMake make = new VehicleMake();
                make.setMakeId(makeId);
                make.setValue(makeValue);

                VehicleModel model = new VehicleModel();
                model.setModelId(modelId);
                model.setValue(modelValue);
                model.setMake(make);

                models.add(model);
            }

            return models;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean isModelExist(String model) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MODEL_BY_NAME)) {
            statement.setString(1, model);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean isModelExist(int modelId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MODEL_BY_ID)) {
            statement.setInt(1, modelId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean isPowertrainExist(int powertrainId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_POWERTRAIN_BY_ID)) {
            statement.setInt(1, powertrainId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean isTransmissionExist(int transmissionId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TRANSMISSION_BY_ID)) {
            statement.setInt(1, transmissionId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean isDriveExist(int driveId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_DRIVE_BY_ID)) {
            statement.setInt(1, driveId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean isColorExist(int colorId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COLOR_BY_ID)) {
            statement.setInt(1, colorId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean isColorExist(String color) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COLOR_BY_NAME)) {
            statement.setString(1, color);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public Optional<VehicleColor> findColorById(int colorId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COLOR_BY_ID)) {
            statement.setInt(1, colorId);

            ResultSet resultSet = statement.executeQuery();
            VehicleColor vehicleColor = new VehicleColor();
            if (resultSet.next()) {
                int vehicleColorId = resultSet.getInt(VEHICLE_COLOR_ID);
                String vehicleColorValue = resultSet.getString(VEHICLE_COLOR);
                vehicleColor.setColorId(vehicleColorId);
                vehicleColor.setValue(vehicleColorValue);
                return Optional.of(vehicleColor);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public VehicleColor insertColor(String color) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_VEHICLE_COLOR,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, color.toUpperCase());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            int colorId = 0;
            if (resultSet.next()) {
                colorId = resultSet.getInt(1);
            }

            Optional<VehicleColor> optionalVehicleColor = Optional.empty();
            if (colorId > 0) {
                optionalVehicleColor = findColorById(colorId);
            }

            if (optionalVehicleColor.isPresent()) {
                return optionalVehicleColor.get();
            } else {
                logger.error("New color entry can't be created or re-read afterward");
                throw new DaoException("New color entry can't be created or re-read afterward");
            }

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public boolean insertModel(String model, int makeId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_VEHICLE_MODEL)) {

            preparedStatement.setString(1, model.toUpperCase());
            preparedStatement.setInt(2, makeId);

            return (preparedStatement.executeUpdate() == 1);

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public Vehicle insertVehicle(Vehicle vehicle) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement optionsStatement = connection.prepareStatement(SQL_INSERT_OPTION);
             PreparedStatement vehicleStatement = connection.prepareStatement(SQL_INSERT_VEHICLE,
                     Statement.RETURN_GENERATED_KEYS)) {

            vehicleStatement.setInt(1, vehicle.getState().getStateId());
            vehicleStatement.setLong(2, vehicle.getOwnerId());
            vehicleStatement.setInt(3, vehicle.getModel().getModelId());
            vehicleStatement.setInt(4, vehicle.getModelYear().getValue());
            vehicleStatement.setInt(5, vehicle.getMileage());
            vehicleStatement.setInt(6, vehicle.getColor().getColorId());
            vehicleStatement.setBigDecimal(7, vehicle.getPrice());
            vehicleStatement.setInt(8, vehicle.getPowertrain().getPowertrainId());
            vehicleStatement.setInt(9, vehicle.getTransmission().getTransmissionId());
            vehicleStatement.setInt(10, vehicle.getDrive().getDriveId());
            vehicleStatement.setInt(11, vehicle.getDisplacement());
            vehicleStatement.setInt(12, vehicle.getPower());
            vehicleStatement.setString(13, vehicle.getComment());
            vehicleStatement.executeUpdate();

            ResultSet resultSet = vehicleStatement.getGeneratedKeys();
            long vehicleId = 0;
            if (resultSet.next()) {
                vehicleId = resultSet.getLong(1);
            }

            for (VehicleOption option : vehicle.getOptionList()) {
                optionsStatement.setLong(1, vehicleId);
                optionsStatement.setLong(2, option.getOptionId());
                optionsStatement.addBatch();
            }
            optionsStatement.executeBatch();

            Optional<Vehicle> optionalVehicle = findById(vehicleId);
            if (optionalVehicle.isEmpty()) {
                throw new DaoException("Vehicle can't be created or re-read after creating");
            } else {
                return optionalVehicle.get();
            }

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
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
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
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

            while (resultSet.next()) {
                String imagePath = resultSet.getString(IMAGE_PATH);
                imagePaths.add(imagePath);
            }

            return imagePaths;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public List<VehicleOption> findAllOption() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_OPTIONS);
            List<VehicleOption> optionList = new ArrayList<>();

            while (resultSet.next()) {
                long optionId = resultSet.getLong(VEHICLE_OPTION_ID);
                String optionDescription = resultSet.getString(OPTION_DESCRIPTION);
                VehicleOption vehicleOption = new VehicleOption();
                vehicleOption.setOptionId(optionId);
                vehicleOption.setDescription(optionDescription);
                optionList.add(vehicleOption);
            }

            return optionList;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public List<VehicleOption> findOptionByVehicleId(long vehicleId) throws DaoException {
        logger.info("findOptionsById() method called");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_OPTIONS_BY_VEHICLE_ID)) {
            statement.setLong(1, vehicleId);

            ResultSet resultSet = statement.executeQuery();
            List<VehicleOption> optionList = new ArrayList<>();

            while (resultSet.next()) {
                long optionId = resultSet.getLong(OPTION_ID);
                String optionDescription = resultSet.getString(OPTION_DESCRIPTION);
                VehicleOption vehicleOption = new VehicleOption();
                vehicleOption.setOptionId(optionId);
                vehicleOption.setDescription(optionDescription);
                optionList.add(vehicleOption);
            }

            return optionList;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public List<VehicleOption> reSetOptionList(long vehicleId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement optionsCreateStatement = connection.prepareStatement(SQL_INSERT_OPTION)) {

            return null; // todo:

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }

    @Override
    public List<VehicleColor> findAllColors() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_COLORS);
            List<VehicleColor> colorList = new ArrayList<>();

            while (resultSet.next()) {
                int colorId = resultSet.getInt(VEHICLE_COLOR_ID);
                String color = resultSet.getString(VEHICLE_COLOR);

                VehicleColor vehicleColor = new VehicleColor();
                vehicleColor.setColorId(colorId);
                vehicleColor.setValue(color);

                colorList.add(vehicleColor);
            }

            return colorList;

        } catch (SQLException e) {
            logger.error("database access error occurred or error parsing resultSet", e);
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        } catch (ConnectionPoolException e) {
            logger.error("error of getting connection from ConnectionPool", e);
            throw new DaoException("error of getting connection from ConnectionPool", e);
        }
    }
}
