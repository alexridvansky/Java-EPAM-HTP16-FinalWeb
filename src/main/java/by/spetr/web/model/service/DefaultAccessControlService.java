package by.spetr.web.model.service;

import by.spetr.web.controller.command.CommandType;
import by.spetr.web.model.dao.DefaultUserDao;
import by.spetr.web.model.dao.UserDao;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.form.VehicleFullForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.List;

public class DefaultAccessControlService implements AccessControlService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDao userDao = new DefaultUserDao();
    private static final VehicleService vehicleService = VehicleService.getInstance();
    private static DefaultAccessControlService instance;

    private DefaultAccessControlService() {

    }

    public static DefaultAccessControlService getInstance() {
        if (instance == null) {
            instance = new DefaultAccessControlService();
        }
        return instance;
    }

    @Override
    public boolean commandPermission(UserRoleType role, CommandType command) {
        return true;
    }

    @Override
    public boolean updateUserRole(UserForm form) throws ServiceException {
        return true;
    }

    @Override
    public boolean updateUserState(UserForm form) throws ServiceException {
        return true;
    }

    public boolean updateVehicleState(VehicleFullForm form) throws ServiceException {

        return true;
    }

    @Override
    public boolean createVehicle(VehicleFullForm form) throws ServiceException {
        return true;
    }
}
