package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleMakeForm;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;

public class AddNewMakeCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        VehicleMakeForm form;

        try {
            form = (VehicleMakeForm) doForm(request);
            vehicleService.addMake(form);
            request.setAttribute(FEEDBACK_MESSAGE, form.getFeedbackMsg());

            return new Router(MAKE_CREATION_PAGE);

        } catch (ServiceException e) {
            logger.error("Error adding new entry of Vehicle.model", e);
            request.setAttribute(FEEDBACK_MESSAGE, "Error adding new entry of Vehicle.model");
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(ERROR_PAGE);

        } catch (IllegalArgumentException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        VehicleMakeForm form = new VehicleMakeForm();

        String make = request.getParameter(VEHICLE_MAKE);
        if (make == null || make.isBlank()) {
            throw new IllegalArgumentException("Parameter 'make' is null or empty");
        }
        form.setMake(make);

        return form;
    }
}
