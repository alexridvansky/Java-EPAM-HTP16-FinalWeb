package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;

import javax.servlet.http.HttpServletRequest;

public class ShowVehicleInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

            String strVehicleId = request.getParameter(VEHICLE_ID_PARAM);
            if (strVehicleId == null) {
                throw new IllegalArgumentException("VehicleId parameter is null");
            }
            long vehicleId = Long.parseLong(strVehicleId);

            Optional<VehicleFullDto> optionalVehicleFullDto = vehicleService.getFullDtoVehicleById(vehicleId);
            if (optionalVehicleFullDto.isPresent()) {
                request.setAttribute(VEHICLE_PARAM, optionalVehicleFullDto.get());

                return new Router(VEHICLE_INFO);

            } else {
                request.setAttribute(FEEDBACK_MESSAGE_PARAM, "Vehicle not found");

                return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));
            }
        } catch (ServiceException e) {
            logger.error("Error getting vehicle info from Vehicle.service", e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);

        } catch (IllegalArgumentException e) {
            logger.error("Error parsing command: {}", e.getMessage(), e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, "Error parsing command: " + e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }
}
