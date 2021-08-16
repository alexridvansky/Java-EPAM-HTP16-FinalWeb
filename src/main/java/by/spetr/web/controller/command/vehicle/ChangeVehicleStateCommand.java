package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.form.VehicleShortForm;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;
import static by.spetr.web.controller.command.Router.RouterType.REDIRECT;

public class ChangeVehicleStateCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("ChangeVehicleStateCommand() method been called");

        VehicleFullForm form = (VehicleFullForm) doForm(request);

        try {
            vehicleService.updateVehicleState(form);
            logger.debug("vehicleId '{}' - '{}'", form.getVehicleId(), form.getState());

            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

            return new Router(Objects.requireNonNullElse(lastPage, PagePath.INDEX_PAGE));

        } catch (ServiceException e) {
            logger.error("state for the 'vehicleId {}' not changed", form.getVehicleId());
            request.setAttribute(EXCEPTION_MESSAGE, "state not changed");

            return new Router(ERROR_PAGE, REDIRECT);
        } catch (IllegalArgumentException e) {
            logger.error("Wrong parameters' types, parsing error", e);
            request.setAttribute(EXCEPTION_MESSAGE, "Wrong parameters' types, parsing error");

            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        VehicleFullForm form = new VehicleFullForm();

        UserDto executor = (UserDto) request.getSession().getAttribute(USER_PARAM);
        String vehicleId = request.getParameter(VEHICLE_ID_PARAM);
        String vehicleState = request.getParameter(VEHICLE_STATE_PARAM);

        if (vehicleId == null || vehicleState == null || executor == null) {
            logger.error("Wrong parameters' types, parsing error");
            throw new IllegalArgumentException("Wrong parameters' types, parsing error");
        }

        form.setExecutor(executor);
        form.setVehicleId(Long.parseLong(vehicleId));
        form.setState(VehicleStateType.valueOf(vehicleState));

        return form;
    }
}
