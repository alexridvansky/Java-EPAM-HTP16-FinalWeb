package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.CommandType;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.UserForm;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.SHOW_VEHICLE_LIST_ADM;
import static by.spetr.web.controller.command.RequestParameter.*;

public class ShowVehicleListAdmCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            DefaultForm form = doForm(request);
            List<VehicleFullDto> vehicles = vehicleService.getFullDtoVehicleList(form);
            request.setAttribute(VEHICLE_LIST_PARAM,vehicles);

            return new Router(SHOW_VEHICLE_LIST_ADM);

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());
            return new Router(ERROR_PAGE);
        } catch (IllegalArgumentException e) {
            logger.error("Parsing parameters error", e);
            request.setAttribute(EXCEPTION_MESSAGE, "Parsing parameters error");
            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        UserForm form = new UserForm();
        UserDto executor = (UserDto) request.getSession().getAttribute(USER_PARAM);
        if (executor == null) {
            throw new IllegalArgumentException();
        }

        String command = (String) request.getAttribute(COMMAND);
        CommandType requestedCommand = CommandType.valueOf(command.toUpperCase());

        form.setExecutor(executor);
        form.setRequestedCommand(requestedCommand);

        return form;
    }
}
