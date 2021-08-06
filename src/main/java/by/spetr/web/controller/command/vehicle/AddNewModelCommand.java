package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.entity.VehicleMake;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleForm;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;

public class AddNewModelCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        VehicleForm form;

        try {
            form = (VehicleForm) doForm(request);
            vehicleService.addModel(form);
            request.setAttribute(FEEDBACK_MESSAGE, form.getFeedbackMsg());

            List<VehicleMake> makes = vehicleService.getMakeList();
            request.setAttribute(VEHICLE_MAKE_LIST, makes);

            return new Router(MODEL_CREATION_PAGE);

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
        VehicleForm form = new VehicleForm();

        String makeIdStr = request.getParameter(VEHICLE_MAKE_ID);
        if (makeIdStr == null || makeIdStr.isBlank()) {
            logger.error("Weird thing... Parameter 'make_id' is null or empty");
            throw new IllegalArgumentException("Parameter 'make_id' is null or empty");
        }
        int makeId;
        try {
            makeId = Integer.parseInt(makeIdStr);
        } catch (NumberFormatException e) {
            logger.error("Weird thing... wrong data in 'make_id' parameter");
            throw new IllegalArgumentException("Wrong data in 'make_id' parameter", e);
        }
        form.setMakeId(makeId);

        String model = request.getParameter(VEHICLE_MODEL);
        if (model == null || model.isBlank()) {
            logger.error("Weird thing... Parameter 'model' is null or empty");
            throw new IllegalArgumentException("Parameter 'model' is null or empty");
        }
        form.setModel(model);

        return form;
    }
}


