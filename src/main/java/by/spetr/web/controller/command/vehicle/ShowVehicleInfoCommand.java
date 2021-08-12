package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;

public class ShowVehicleInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String vehicleId = request.getParameter(VEHICLE_ID_PARAM);

        try {
            Optional<VehicleFullDto> optionalVehicleFullDto = vehicleService.getFullDtoVehicleById(Long.parseLong(vehicleId));
            if (optionalVehicleFullDto.isPresent()) {
                request.setAttribute(VEHICLE_PARAM, optionalVehicleFullDto.get());
                return new Router(SHOW_VEHICLE_INFO);
            } else {
                return new Router(SHOW_VEHICLE_NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error getting vehicle info from Vehicle.service", e);
            request.setAttribute(EXCEPTION_MESSAGE, "Error getting vehicle info from Vehicle.service");

            return new Router(ERROR_PAGE);
        }
    }
}
