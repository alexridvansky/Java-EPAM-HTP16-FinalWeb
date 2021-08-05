package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.INDEX_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

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

            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

            if (lastPage == null) {
                lastPage = INDEX_PAGE;
            }
            return new Router(lastPage, Router.RouterType.FORWARD);

        } catch (ServiceException e) {
            logger.error("state for the 'vehicleId {}' not changed", vehicleId);
            e.printStackTrace();

            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
            // todo: goto error page? show message ?
        }
    }
}
