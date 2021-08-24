package by.spetr.web.model.service;

import by.spetr.web.controller.command.CommandType;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.form.VehicleUploadForm;

public interface AccessControlService {

    /**
     * is user to authorise access within commands
     *
     * @param role    UserRoleType stands for User.role
     * @param command CommandType stands for Command name
     * @return true if operation is allowed for user group given
     */
    boolean commandPermission(UserRoleType role, CommandType command);

    boolean updateUserRole(UserForm form) throws ServiceException;

    boolean updateUserState(UserForm form) throws ServiceException;

    boolean updateVehicleState(VehicleFullForm form) throws ServiceException;

    boolean createVehicle(VehicleFullForm form) throws ServiceException;

    boolean uploadPhoto(VehicleFullForm form) throws ServiceException;

    static DefaultAccessControlService getInstance() {
        return DefaultAccessControlService.getInstance();
    }
}
