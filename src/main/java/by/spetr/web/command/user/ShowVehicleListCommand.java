package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.spetr.web.command.PagePath.SHOW_VEHICLE_LIST;
import static by.spetr.web.command.RequestParameter.LAST_PAGE_PARAM;
import static by.spetr.web.command.RequestParameter.VEHICLE_LIST_PARAM;

public class ShowVehicleListCommand implements Command {
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Vehicle> vehicles = vehicleService.getAdminVehicleList();
            request.setAttribute(VEHICLE_LIST_PARAM,vehicles);
            request.getSession().setAttribute(LAST_PAGE_PARAM, SHOW_VEHICLE_LIST);

            return new Router(SHOW_VEHICLE_LIST);

        } catch (ServiceException e) {
            // todo: go to some show_user_error.page
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
