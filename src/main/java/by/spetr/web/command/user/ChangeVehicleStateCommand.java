package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.command.RequestParameter.*;

public class ChangeVehicleStateCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("ChangeVehicleStateCommand() method been called");

        String vehicleId = request.getParameter(VEHICLE_ID_PARAM);
        String vehicleState = request.getParameter(VEHICLE_STATE_PARAM);

        try {
            vehicleService.updateVehicleState(Long.parseLong(vehicleId), VehicleStateType.valueOf(vehicleState));
            logger.debug("vehicleId '{}' - '{}'", vehicleId, vehicleState);
            List<Vehicle> vehicles = vehicleService.getAdminVehicleList();
            request.setAttribute(VEHICLE_LIST_PARAM, vehicles);

            return new Router(PagePath.SHOW_VEHICLE_LIST, Router.RouterType.FORWARD);

        } catch (ServiceException e) {
            logger.error("state for the 'vehicleId {}' not changed", vehicleId);
            e.printStackTrace();

            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
            // todo: goto error page? show message ?
        }
    }
}
