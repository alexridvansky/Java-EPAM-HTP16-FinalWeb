package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

import javax.servlet.http.HttpServletRequest;

public class UpdateVehicleStateCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("UpdateVehicleStateCommand() method been called");

        VehicleFullForm form = (VehicleFullForm) doForm(request);

        try {
            vehicleService.updateVehicleState(form);
            logger.debug("vehicleId '{}' - '{}'", form.getVehicleId(), form.getState());

            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, form.getFeedbackMsg());
            request.setAttribute(OPERATION_SUCCESS_PARAM, form.isSuccess());

            return new Router(Objects.requireNonNullElse(lastPage, PagePath.INDEX_PAGE));

        } catch (ServiceException e) {
            logger.error("state for the 'vehicleId {}' not changed", form.getVehicleId());
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, "state for the '" + form.getVehicleId() + "' not changed");
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

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
