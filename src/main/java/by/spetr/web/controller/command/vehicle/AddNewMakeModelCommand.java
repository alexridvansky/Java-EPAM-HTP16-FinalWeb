package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.entity.VehicleMake;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessage;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.MAKE_MODEL_CREATION_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

public class AddNewMakeModelCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String operation = request.getParameter(OPERATION);

        if (operation == null) {
            request.setAttribute(FEEDBACK_MESSAGE, "Operation type is null");

            return new Router(ERROR_PAGE);
        }

        switch (operation) {
            case "make" -> {
                String make = request.getParameter(VEHICLE_MAKE);

                if (make == null || make.isBlank()) {
                    request.setAttribute(FEEDBACK_MESSAGE, "Empty parameter 'make'");

                    return new Router(ERROR_PAGE);
                } else {
                    try {
                        vehicleService.addMake(make);
                        FormattedMessage message = new FormattedMessage("Model '<b>{}</b>' added", make);
                        request.setAttribute(FEEDBACK_MESSAGE, message);

                    } catch (ServiceException e) {
                        logger.error("Error adding new entry of Vehicle.make", e);
                        request.setAttribute(FEEDBACK_MESSAGE, "Error adding new entry of Vehicle.make");
                        request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

                        return new Router(ERROR_PAGE);
                    }
                }
            }
            case "model" -> {
                String model = request.getParameter(VEHICLE_MODEL);
                String modelMakeIdStr = request.getParameter(VEHICLE_MAKE_ID);

                if (model == null || model.isBlank() || modelMakeIdStr == null || modelMakeIdStr.isBlank()) {
                    request.setAttribute(FEEDBACK_MESSAGE, "Empty parameter 'model', 'make_id' or either");

                    return new Router(ERROR_PAGE);
                } else {
                    try {
                        int modelMakeId = Integer.parseInt(modelMakeIdStr);
                        vehicleService.addModel(model, modelMakeId);
                        FormattedMessage message = new FormattedMessage("Model '<b>{}</b>' added", model);
                        request.setAttribute(FEEDBACK_MESSAGE, message);

                    } catch (ServiceException e) {
                        logger.error("Error adding new entry of Vehicle.model", e);
                        request.setAttribute(FEEDBACK_MESSAGE, "Error adding new entry of Vehicle.model");
                        request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

                        return new Router(ERROR_PAGE);

                    } catch (NumberFormatException e) {
                        logger.error("Error parsing of value of Vehicle.make_id", e);
                        request.setAttribute(FEEDBACK_MESSAGE, "Error parsing of value of Vehicle.make_id");
                        request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

                        return new Router(ERROR_PAGE);
                    }
                }
            }
            default -> {
                FormattedMessage errorMsg = new FormattedMessage("Unknown or wrong operation type");
                request.setAttribute(FEEDBACK_MESSAGE, errorMsg);

                return new Router(ERROR_PAGE);
            }
        }

        try {
            List<VehicleMake> makes = vehicleService.getMakeList();
            request.setAttribute(VEHICLE_MAKE_LIST, makes);

            return new Router(MAKE_MODEL_CREATION_PAGE);

        } catch (ServiceException e) {
            logger.error("Error getting makes list from Vehicle.service", e);
            request.setAttribute(FEEDBACK_MESSAGE, "Error getting makes list from Vehicle.service");
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(PagePath.ERROR_PAGE);
        }
    }
}
