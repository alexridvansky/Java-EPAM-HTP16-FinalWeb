package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Year;
import java.util.Set;

import static by.spetr.web.controller.command.PagePath.ADD_PHOTO_PAGE;
import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

public class AddNewVehicleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Create new vehicle command");

        UserDto user = (UserDto) request.getSession().getAttribute(USER_PARAM);
        if (user.getRole() != UserRoleType.USER) {
            logger.error("Unauthorised access attempt");
            request.setAttribute(EXCEPTION_MESSAGE, "Unauthorised access attempt");

            return new Router(ERROR_PAGE);
        }

        try {
            if (request.getMethod().equals("GET")) {
                logger.error("Wrong request.method. Probably invasion attempt :) Alarm!");
                request.setAttribute(EXCEPTION_MESSAGE, "Wrong Request.method(). Probably invasion attempt :) Alarm!");

                return new Router(ERROR_PAGE);
            }

            VehicleFullForm form = (VehicleFullForm) doForm(request);

            Vehicle vehicle = vehicleService.addVehicle(form);

            request.setAttribute(VEHICLE_PARAM, vehicle);

            return new Router(ADD_PHOTO_PAGE);

        } catch (IllegalArgumentException | ServiceException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE, e.getMessage());

            return new Router(ERROR_PAGE);
        }

    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        try {
            VehicleFullForm form = new VehicleFullForm();

            UserDto user = (UserDto) request.getSession().getAttribute(USER_PARAM);
            form.setOwnerId(user.getUserId());
            int makeId = Integer.parseInt(request.getParameter(VEHICLE_MAKE_ID_PARAM));
            form.setMakeId(makeId);
            int modelId = Integer.parseInt(request.getParameter(VEHICLE_MODEL_ID_PARAM));
            form.setModelId(modelId);
            int powertrain = Integer.parseInt(request.getParameter(VEHICLE_POWERTRAIN_ID_PARAM));
            form.setPowertrainId(powertrain);
            int transmissionId = Integer.parseInt(request.getParameter(VEHICLE_TRANSMISSION_ID_PARAM));
            form.setTransmissionId(transmissionId);
            int driveId = Integer.parseInt(request.getParameter(VEHICLE_DRIVE_ID_PARAM));
            form.setDriveId(driveId);
            int colorId = Integer.parseInt(request.getParameter(VEHICLE_COLOR_ID_PARAM));
            form.setColorId(colorId);
            int modelYear = Integer.parseInt(request.getParameter(VEHICLE_MODEL_YEAR_PARAM));
            form.setModelYear(Year.of(modelYear));
            int displacement = Integer.parseInt(request.getParameter(VEHICLE_DISPLACEMENT_PARAM));
            form.setDisplacement(displacement);
            int mileage = Integer.parseInt(request.getParameter(VEHICLE_MILEAGE_PARAM));
            form.setMileage(mileage);
            int power = Integer.parseInt(request.getParameter(VEHICLE_POWER_PARAM));
            form.setPower(power);
            int price = Integer.parseInt(request.getParameter(VEHICLE_PRICE_PARAM));
            form.setPrice(price);

            String description = request.getParameter(VEHICLE_DESCRIPTION_PARAM);
            if (description != null && description.length() > 300) {
                description = description.substring(0, 300);
            }
            form.setComment(description);

            String[] optionSet = request.getParameterValues(VEHICLE_OPTIONS_SET);
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

        } catch (NumberFormatException | NullPointerException e) {
            logger.error("Weird thing... Wrong parameters' types, parsing error");
            throw new IllegalArgumentException("Weird thing... Wrong parameters' types, parsing error");
        }
    }
}
