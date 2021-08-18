package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.VEHICLE_LIST_ADM;
import static by.spetr.web.controller.command.RequestParameter.EXCEPTION_MESSAGE_PARAM;
import static by.spetr.web.controller.command.RequestParameter.VEHICLE_LIST_PARAM;

public class ShowVehicleListAdmCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<VehicleFullDto> vehicles = vehicleService.getFullDtoVehicleList();
            request.setAttribute(VEHICLE_LIST_PARAM, vehicles);

            return new Router(VEHICLE_LIST_ADM);

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);

        } catch (IllegalArgumentException e) {
            logger.error("Parsing parameters error", e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, "Parsing parameters error");

            return new Router(ERROR_PAGE);
        }
    }
}
