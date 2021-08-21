package by.spetr.web.model.service;

import by.spetr.web.controller.command.CommandType;
import by.spetr.web.model.dao.DefaultUserDao;
import by.spetr.web.model.dao.UserDao;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.exception.DaoException;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.form.VehicleFullForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import static by.spetr.web.controller.command.CommandType.*;
import static by.spetr.web.model.entity.type.UserRoleType.*;

public class DefaultAccessControlService implements AccessControlService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDao userDao = new DefaultUserDao();
    private static final EnumMap<CommandType, List<UserRoleType>> commandPermission = new EnumMap<>(CommandType.class);
    private static final VehicleService vehicleService = VehicleService.getInstance();
    private static DefaultAccessControlService instance;

    private DefaultAccessControlService() {
        commandPermission.put(DEFAULT, new ArrayList<>(List.of(ROOT, MODERATOR, USER, GUEST)));
        commandPermission.put(ADD_NEW_COLOR, new ArrayList<>(List.of(ROOT)));
        commandPermission.put(ADD_NEW_MAKE, new ArrayList<>(List.of(ROOT)));
        commandPermission.put(ADD_NEW_MODEL, new ArrayList<>(List.of(ROOT)));
        commandPermission.put(ADD_NEW_VEHICLE, new ArrayList<>(List.of(USER)));
        commandPermission.put(SHOW_USER_LIST_ADMIN, new ArrayList<>(List.of(ROOT)));
        commandPermission.put(SHOW_VEHICLE_LIST_ADMIN, new ArrayList<>(List.of(ROOT)));
        commandPermission.put(SHOW_VEHICLE_LIST_MODER, new ArrayList<>(List.of(MODERATOR)));
        commandPermission.put(SHOW_VEHICLE_LIST_PERSONAL, new ArrayList<>(List.of(USER)));
        commandPermission.put(SHOW_VEHICLE_LIST_PUBLIC, new ArrayList<>(List.of(ROOT, USER, GUEST)));
        commandPermission.put(SHOW_VEHICLE_INFO, new ArrayList<>(List.of(ROOT, MODERATOR, USER, GUEST)));
        commandPermission.put(SHOW_VEHICLE_CREATION_PAGE, new ArrayList<>(List.of(USER)));
        commandPermission.put(SHOW_MAKE_CREATION_PAGE, new ArrayList<>(List.of(ROOT)));
        commandPermission.put(SHOW_MODEL_CREATION_PAGE, new ArrayList<>(List.of(ROOT)));
        commandPermission.put(SHOW_COLOR_CREATION_PAGE, new ArrayList<>(List.of(ROOT)));
        commandPermission.put(UPDATE_VEHICLE_ADS, new ArrayList<>(List.of(USER)));
        commandPermission.put(UPLOAD_VEHICLE_PHOTO, new ArrayList<>(List.of(USER)));
        commandPermission.put(GO_TO_MAIN_PAGE, new ArrayList<>(List.of(ROOT, MODERATOR, USER, GUEST)));
        commandPermission.put(GO_TO_SIGN_UP_PAGE, new ArrayList<>(List.of(GUEST)));
        commandPermission.put(GO_TO_RECOVERY_PASSWORD_PAGE, new ArrayList<>(List.of(GUEST)));
        commandPermission.put(CREATE_USER, new ArrayList<>(List.of(GUEST)));
        commandPermission.put(SIGN_IN, new ArrayList<>(List.of(GUEST)));
        commandPermission.put(LOG_OUT, new ArrayList<>(List.of(ROOT, MODERATOR, USER)));
        commandPermission.put(CHANGE_LOCALE, new ArrayList<>(List.of(ROOT, MODERATOR, USER, GUEST)));
        commandPermission.put(CHANGE_USER_STATE, new ArrayList<>(List.of(ROOT, MODERATOR)));
        commandPermission.put(CHANGE_USER_ROLE, new ArrayList<>(List.of(ROOT, MODERATOR)));
        commandPermission.put(CHANGE_VEHICLE_STATE, new ArrayList<>(List.of(ROOT, MODERATOR, USER)));
    }

    public static DefaultAccessControlService getInstance() {
        if (instance == null) {
            instance = new DefaultAccessControlService();
        }
        return instance;
    }

    @Override
    public boolean commandPermission(UserRoleType role, CommandType command) {
        List<UserRoleType> list = commandPermission.get(command);
        if (list == null) {
            throw new IllegalArgumentException("Unknown command type");
        } else {
            return list.contains(role);
        }
    }

    @Override
    public boolean updateUserRole(UserForm form) throws ServiceException {
        try {
            UserDto executor = form.getExecutor();
            if (!(executor.getRole() == ROOT || executor.getRole() == MODERATOR)) {
                logger.error("Unauthorised access attempt");
                throw new ServiceException("Unauthorised access attempt");
            }

            Optional<User> optionalAffectedUser = userDao.findByLogin(form.getUserName());
            User affectedUser = null;
            if (optionalAffectedUser.isEmpty()) {
                logger.error("Affected user not found, state changing can't be performed");
                throw new ServiceException("Affected user not found, state changing can't be performed");
            } else {
                affectedUser = optionalAffectedUser.get();
            }

            if (affectedUser.getRole() == ROOT) {
                logger.error("Root account can't be modified");
                throw new ServiceException("Root account can't be modified");
            }

            if (executor.getRole() == MODERATOR) {
                logger.error("Moderators can't perform role changing");
                throw new ServiceException("Moderators can't perform role changing");
            }

            return true;
        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public boolean updateUserState(UserForm form) throws ServiceException {
        try {
            UserDto executor = form.getExecutor();

            if (!(executor.getRole() == ROOT || executor.getRole() == MODERATOR)) {
                logger.error("Unauthorised access attempt");
                throw new ServiceException("Unauthorised access attempt");
            }

            Optional<User> optionalAffectedUser = userDao.findByLogin(form.getUserName());
            User affectedUser = null;
            if (optionalAffectedUser.isEmpty()) {
                logger.error("Affected user not found, state changing can't be performed");
                throw new ServiceException("Affected user not found, state changing can't be performed");
            } else {
                affectedUser = optionalAffectedUser.get();
            }

            if (affectedUser.getRole() == ROOT) {
                logger.error("Root account can't be modified");
                throw new ServiceException("Root account can't be modified");
            }

            if (executor.getRole() == MODERATOR ) {
                if (affectedUser.getRole() == MODERATOR) {
                    logger.error("Moderators can't change roles for themselves");
                    throw new ServiceException("Moderators can't change roles for themselves");
                }

                if (!((affectedUser.getState() == UserStateType.ENABLED && form.getState() == UserStateType.BANNED)
                   || (affectedUser.getState() == UserStateType.BANNED && form.getState() == UserStateType.ENABLED))) {
                    logger.error("Moderators can either ban enabled users, or enable banned");
                    throw new ServiceException("Moderators can either ban enabled users, or enable banned");
                }
            }
            return true;

        } catch (DaoException e) {
            logger.error("Error occurred on DAO layer", e);
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    public boolean updateVehicleState(VehicleFullForm form) throws ServiceException {
        UserDto executor = form.getExecutor();
        Optional<Vehicle> optionalVehicle = vehicleService.getVehicleById(form.getVehicleId());

        if (optionalVehicle.isEmpty()) {
            logger.error("Affected vehicle not found");
            throw new ServiceException("Affected vehicle not found");
        }


        Vehicle vehicle = optionalVehicle.get();

        if (executor.getRole() == USER) {
            if (executor.getUserId() != vehicle.getOwnerId()) {
                logger.error("Users can change state only for their own ads");
                throw new ServiceException("Users can change state only for their own ads");
            }

            if (!((vehicle.getState() == VehicleStateType.ENABLED || vehicle.getState() == VehicleStateType.DISABLED)
                    && (form.getState() == VehicleStateType.ENABLED || form.getState() == VehicleStateType.DISABLED
                    || form.getState() == VehicleStateType.ARCHIVED))) {
                logger.error("Users can change state only within ENABLED-DISABLED, and once to ARCHIVED");
                throw new ServiceException("Users can change state only within ENABLED-DISABLED, and once to ARCHIVED");
            }
        }

        return true;
    }

    @Override
    public boolean createVehicle(VehicleFullForm form) throws ServiceException {
        if (form.getExecutor().getState() != UserStateType.ENABLED) {
            logger.error("Only active (ENABLED) users can create adverts");
            throw new ServiceException("Only active (ENABLED) users can create adverts");
        }

        return true;
    }
}
