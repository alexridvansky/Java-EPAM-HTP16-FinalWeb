package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.entity.type.VehicleMake;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.MODEL_CREATION_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

import javax.servlet.http.HttpServletRequest;


public class ShowModelCreationPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("ShowModelCreationPageCommand() called");

        try {
            doForm(request);
            List<VehicleMake> makes = vehicleService.getMakeList();
            request.setAttribute(VEHICLE_MAKE_LIST_PARAM, makes);

            return new Router(MODEL_CREATION_PAGE);

        } catch (ServiceException e) {
            logger.error("Error getting makes list from Vehicle.service", e);
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, "Error getting makes list from Vehicle.service");
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }
}