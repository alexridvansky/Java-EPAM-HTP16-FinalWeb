package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;
import static by.spetr.web.controller.command.Router.RouterType.REDIRECT;

public class EditVehicleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Edit vehicle command");

        try {
            VehicleFullForm form = (VehicleFullForm) doForm(request);
            Vehicle vehicle = vehicleService.addVehicle(form);
            request.setAttribute(VEHICLE_PARAM, vehicle);

            if (form.isSuccess()) {
                return new Router(VEHICLE_LIST_PERSONAL, REDIRECT);
            } else {
                request.setAttribute(FORM_PARAM, form);
                request.setAttribute(FEEDBACK_MESSAGE_PARAM, form.getFeedbackMsg());
                request.setAttribute(OPERATION_SUCCESS_PARAM, form.isSuccess());

                return new Router(ADD_VEHICLE_PAGE);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);

        } catch (IllegalArgumentException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ADD_VEHICLE_PAGE);
        }

    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        VehicleFullForm form = new VehicleFullForm();
        return form;
    }
}
