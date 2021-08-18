package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.type.*;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.service.AccessControlService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.ADD_VEHICLE_PAGE;
import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;


public class ShowVehicleCreationPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    VehicleService vehicleService = VehicleService.getInstance();
    AccessControlService accessControlService = AccessControlService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("ShowVehicleCreationPageCommand() called");

        try {
            VehicleFullForm form = (VehicleFullForm) doForm(request);

            accessControlService.createVehicle(form);

            List<VehicleMake> makes = vehicleService.getMakeList();
            List<VehicleModel> models = vehicleService.getModelList();
            List<VehiclePowertrainType> powertrains = vehicleService.getAllPowertrainTypeList();
            List<VehicleTransmissionType> transmissions = vehicleService.getAllTransmissionTypeList();
            List<VehicleDriveType> drives = vehicleService.getAllDriveTypeList();
            List<VehicleColor> colors = vehicleService.getAllColorList();
            List<VehicleOption> options = vehicleService.getOptionList();

            request.setAttribute(VEHICLE_MAKE_LIST_PARAM, makes);
            request.setAttribute(VEHICLE_MODEL_LIST_PARAM, models);
            request.setAttribute(VEHICLE_POWERTRAIN_LIST_PARAM, powertrains);
            request.setAttribute(VEHICLE_TRANSMISSION_LIST_PARAM, transmissions);
            request.setAttribute(VEHICLE_DRIVE_LIST_PARAM, drives);
            request.setAttribute(VEHICLE_COLOR_LIST_PARAM, colors);
            request.setAttribute(VEHICLE_OPTION_LIST_PARAM, options);

            return new Router(ADD_VEHICLE_PAGE);

        } catch (ServiceException | IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        VehicleFullForm form = new VehicleFullForm();
        UserDto executor = (UserDto) request.getSession().getAttribute(USER_PARAM);
        if (executor == null) {
            logger.error("No user in sessionScope");
            throw new IllegalArgumentException("No user in sessionScope");
        }
        form.setExecutor(executor);

        return form;
    }
}
