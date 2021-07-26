package by.spetr.web.command.vehicle;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;
import by.spetr.web.model.dto.VehiclePreviewDto;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.spetr.web.command.PagePath.*;
import static by.spetr.web.command.RequestParameter.LAST_PAGE_PARAM;
import static by.spetr.web.command.RequestParameter.VEHICLE_LIST_PARAM;

public class ShowVehicleAdsCommand implements Command {
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<VehiclePreviewDto> vehicles = vehicleService.getPublicVehicleList();

            request.getSession().setAttribute(LAST_PAGE_PARAM, SHOW_VEHICLE_ADS);
            request.setAttribute(VEHICLE_LIST_PARAM, vehicles);

            return new Router(SHOW_VEHICLE_ADS);

        } catch (ServiceException e) {
            return new Router(ERROR_PAGE);
        }
    }
}
