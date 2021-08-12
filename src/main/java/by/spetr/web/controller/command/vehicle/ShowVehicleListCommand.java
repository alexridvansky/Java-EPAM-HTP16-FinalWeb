package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.VehiclePreviewDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.VEHICLE_LIST_PARAM;

public class ShowVehicleListCommand implements Command {
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<VehiclePreviewDto> vehicles = vehicleService.getPublicVehicleList();

            request.setAttribute(VEHICLE_LIST_PARAM, vehicles);

            return new Router(SHOW_VEHICLE_LIST);

        } catch (ServiceException e) {
            return new Router(ERROR_PAGE);
        }
    }
}
