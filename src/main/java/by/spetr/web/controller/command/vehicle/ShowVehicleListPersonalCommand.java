package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.dto.VehiclePreviewDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.VEHICLE_LIST_PERSONAL;
import static by.spetr.web.controller.command.RequestParameter.USER_PARAM;
import static by.spetr.web.controller.command.RequestParameter.VEHICLE_LIST_PARAM;
import static by.spetr.web.controller.command.Router.RouterType.REDIRECT;

public class ShowVehicleListPersonalCommand implements Command {
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            DefaultForm form = doForm(request);
            List<VehiclePreviewDto> vehicles = vehicleService.getPersonalVehicleList(form.getExecutor().getUserId());

            request.setAttribute(VEHICLE_LIST_PARAM, vehicles);

            return new Router(VEHICLE_LIST_PERSONAL);

        } catch (ServiceException e) {
            return new Router(ERROR_PAGE, REDIRECT);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        DefaultForm form = new DefaultForm();
        form.setExecutor((UserDto) request.getSession().getAttribute(USER_PARAM));

        return form;
    }
}
