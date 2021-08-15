package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleShortForm;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.spetr.web.controller.command.PagePath.COLOR_CREATION_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

public class AddNewColorCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            VehicleShortForm form = (VehicleShortForm) doForm(request);
            vehicleService.addColor(form);
            request.setAttribute(FEEDBACK_MESSAGE, form.getFeedbackMsg());
            var colors = vehicleService.getAllColorList();
            request.setAttribute(VEHICLE_COLOR_LIST, colors);

            return new Router(COLOR_CREATION_PAGE);

        } catch (ServiceException e) {
            logger.error("Error adding new entry of Vehicle.color", e);
            request.setAttribute(FEEDBACK_MESSAGE, "Error adding new entry of Vehicle.color");
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(COLOR_CREATION_PAGE);

        } catch (IllegalArgumentException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(COLOR_CREATION_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        VehicleShortForm form = new VehicleShortForm();

        String color = request.getParameter(VEHICLE_COLOR_PARAM);

        if (color == null || color.isBlank()) {
            logger.error("Wrong parameters' types, parsing error");
            throw new IllegalArgumentException("Wrong parameters' types, parsing error");
        }

        form.setColor(color);

        return form;
    }
}
