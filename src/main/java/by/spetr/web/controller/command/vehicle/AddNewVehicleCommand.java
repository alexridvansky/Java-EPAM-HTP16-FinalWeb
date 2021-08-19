package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Year;
import java.util.Set;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;
import static by.spetr.web.controller.command.Router.RouterType.REDIRECT;

public class AddNewVehicleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Create new vehicle command");

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


        } catch (IllegalArgumentException | ServiceException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        }

    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        try {
            VehicleFullForm form = new VehicleFullForm();

            UserDto user = (UserDto) request.getSession().getAttribute(USER_PARAM);
            assert user != null : "No user in sessionScope";
            int makeId = Integer.parseInt(request.getParameter(VEHICLE_MAKE_ID_PARAM));
            int modelId = Integer.parseInt(request.getParameter(VEHICLE_MODEL_ID_PARAM));
            int powertrain = Integer.parseInt(request.getParameter(VEHICLE_POWERTRAIN_ID_PARAM));
            int transmissionId = Integer.parseInt(request.getParameter(VEHICLE_TRANSMISSION_ID_PARAM));
            int driveId = Integer.parseInt(request.getParameter(VEHICLE_DRIVE_ID_PARAM));
            int colorId = Integer.parseInt(request.getParameter(VEHICLE_COLOR_ID_PARAM));
            int modelYear = Integer.parseInt(request.getParameter(VEHICLE_MODEL_YEAR_PARAM));
            int displacement = Integer.parseInt(request.getParameter(VEHICLE_DISPLACEMENT_PARAM));
            int mileage = Integer.parseInt(request.getParameter(VEHICLE_MILEAGE_PARAM));
            int power = Integer.parseInt(request.getParameter(VEHICLE_POWER_PARAM));
            int price = Integer.parseInt(request.getParameter(VEHICLE_PRICE_PARAM));
            form.setOwnerId(user.getUserId());
            form.setMakeId(makeId);
            form.setModelId(modelId);
            form.setPowertrainId(powertrain);
            form.setTransmissionId(transmissionId);
            form.setDriveId(driveId);
            form.setColorId(colorId);
            form.setModelYear(Year.of(modelYear));
            form.setDisplacement(displacement);
            form.setMileage(mileage);
            form.setPower(power);
            form.setPrice(price);

            String description = request.getParameter(VEHICLE_DESCRIPTION_PARAM);
            if (description != null && description.length() > 300) {
                description = description.substring(0, 300);
            }
            form.setComment(description);

            String[] optionSet = request.getParameterValues(VEHICLE_OPTIONS_SET_PARAM);
            if (optionSet != null) {
                for (String option : optionSet) {
                    long optionId = Long.parseLong(option);
                    form.getOptionSet().add(optionId);
                }
            }

            Set<String> filenames = (Set<String>) request.getAttribute(FILENAME_PARAM);
            filenames.forEach(logger::debug);
            form.setPhotoSet(filenames);

            return form;

        } catch (NumberFormatException | NullPointerException | AssertionError e) {
            logger.error("Wrong parameters' types, parsing error");
            throw new IllegalArgumentException("Wrong parameters' types, parsing error");
        }
    }
}
