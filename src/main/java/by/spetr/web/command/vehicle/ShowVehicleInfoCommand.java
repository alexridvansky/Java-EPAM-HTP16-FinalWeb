package by.spetr.web.command.vehicle;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;

import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.spetr.web.command.PagePath.SHOW_VEHICLE_INFO;
import static by.spetr.web.command.PagePath.SHOW_VEHICLE_NOT_FOUND;
import static by.spetr.web.command.RequestParameter.*;

public class ShowVehicleInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String vehicleId = request.getParameter(VEHICLE_ID_PARAM);

        try {
            Optional<Vehicle> optionalVehicle = vehicleService.getVehicleById(Long.parseLong(vehicleId));
            if (optionalVehicle.isPresent()) {

                Optional<String> optionalPreviewImage = vehicleService.getPreviewImageById(Long.parseLong(vehicleId));

                optionalPreviewImage.ifPresent(s -> request.setAttribute(PREVIEW_PARAM, s));

                request.setAttribute(VEHICLE_PARAM, optionalVehicle.get());
                request.getSession().setAttribute(LAST_PAGE_PARAM, SHOW_VEHICLE_INFO);

                return new Router(SHOW_VEHICLE_INFO);
            } else {
                return new Router(SHOW_VEHICLE_NOT_FOUND);
            }
        } catch (ServiceException e) {
            // todo: go to some show_user_error.page
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
