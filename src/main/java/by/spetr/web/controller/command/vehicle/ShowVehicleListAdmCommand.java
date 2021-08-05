package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.SHOW_VEHICLE_LIST_ADM;
import static by.spetr.web.controller.command.RequestParameter.VEHICLE_LIST_PARAM;

public class ShowVehicleListAdmCommand implements Command {
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Vehicle> vehicles = vehicleService.getAdminVehicleList();

            request.setAttribute(VEHICLE_LIST_PARAM,vehicles);
//            request.getSession().setAttribute(LAST_PAGE_PARAM, SHOW_VEHICLE_LIST_ADM);

            return new Router(SHOW_VEHICLE_LIST_ADM);

        } catch (ServiceException e) {
            // todo: go to some show_user_error.page
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
