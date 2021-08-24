package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.entity.type.VehicleColor;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.COLOR_CREATION_PAGE;
import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.EXCEPTION_MESSAGE_PARAM;
import static by.spetr.web.controller.command.RequestParameter.VEHICLE_COLOR_LIST_PARAM;

import javax.servlet.http.HttpServletRequest;

public class ShowColorCreationPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("ShowColorCreationPageCommand() called");

        try {
            List<VehicleColor> colors = vehicleService.getAllColorList();
            request.setAttribute(VEHICLE_COLOR_LIST_PARAM, colors);

            return new Router(COLOR_CREATION_PAGE);

        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }
}
