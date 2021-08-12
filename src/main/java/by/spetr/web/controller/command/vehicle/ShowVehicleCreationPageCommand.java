package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.entity.*;
import by.spetr.web.model.entity.type.VehicleDriveType;
import by.spetr.web.model.entity.type.VehiclePowertrainType;
import by.spetr.web.model.entity.type.VehicleTransmissionType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.ADD_VEHICLE_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;


public class ShowVehicleCreationPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("ShowVehicleCreationPageCommand() called");

        try {
            List<VehicleMake> makes = vehicleService.getMakeList();
            List<VehicleModel> models = vehicleService.getModelList();
            List<VehiclePowertrainType> powertrains = vehicleService.getAllPowertrainTypeList();
            List<VehicleTransmissionType> transmissions = vehicleService.getAllTransmissionTypeList();
            List<VehicleDriveType> drives = vehicleService.getAllDriveTypeList();
            List<VehicleColor> colors = vehicleService.getAllColorList();
            List<VehicleOption> options = vehicleService.getOptionList();

            request.setAttribute(VEHICLE_MAKE_LIST, makes);
            request.setAttribute(VEHICLE_MODEL_LIST,models);
            request.setAttribute(VEHICLE_POWERTRAIN_LIST, powertrains);
            request.setAttribute(VEHICLE_TRANSMISSION_LIST, transmissions);
            request.setAttribute(VEHICLE_DRIVE_LIST, drives);
            request.setAttribute(VEHICLE_COLOR_LIST, colors);
            request.setAttribute(VEHICLE_OPTION_LIST, options);

            return new Router(ADD_VEHICLE_PAGE);

        } catch (ServiceException e) {
            logger.error("Error getting makes list from Vehicle.service", e);
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(PagePath.ERROR_PAGE);
        }
    }
}
