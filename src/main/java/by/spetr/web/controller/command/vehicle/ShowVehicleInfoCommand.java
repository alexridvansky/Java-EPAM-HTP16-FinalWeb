package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;

import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.spetr.web.controller.command.PagePath.SHOW_VEHICLE_INFO;
import static by.spetr.web.controller.command.PagePath.SHOW_VEHICLE_NOT_FOUND;
import static by.spetr.web.controller.command.RequestParameter.*;

public class ShowVehicleInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String vehicleId = request.getParameter(VEHICLE_ID_PARAM);

        try {
            Optional<VehicleFullDto> optionalVehicleFullDto = vehicleService.getFullVehicleInfo(Long.parseLong(vehicleId));
            if (optionalVehicleFullDto.isPresent()) {
                request.setAttribute(VEHICLE_PARAM, optionalVehicleFullDto.get());
//                request.getSession().setAttribute(LAST_PAGE_PARAM, SHOW_VEHICLE_INFO);

                return new Router(SHOW_VEHICLE_INFO);
            } else {
                return new Router(SHOW_VEHICLE_NOT_FOUND);
            }
        } catch (ServiceException e) {
            // todo: go to some show_user_error.page
            return new Router(PagePath.ERROR_PAGE);
        }
    }
}
