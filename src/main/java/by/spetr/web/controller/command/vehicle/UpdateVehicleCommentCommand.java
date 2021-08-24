package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static by.spetr.web.controller.command.PagePath.*;
import static by.spetr.web.controller.command.RequestParameter.*;

import javax.servlet.http.HttpServletRequest;

public class UpdateVehicleCommentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = VehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);
            VehicleFullForm form = (VehicleFullForm) doForm(request);

            vehicleService.updateVehicleComment(form);

            request.setAttribute(FEEDBACK_MESSAGE_PARAM, form.getFeedbackMsg());
            request.setAttribute(OPERATION_SUCCESS_PARAM, form.isSuccess());

            Optional<VehicleFullDto> optionalVehicleFullDto = vehicleService.getFullDtoVehicleById(form.getVehicleId());
            if (optionalVehicleFullDto.isPresent()) {
                request.setAttribute(VEHICLE_PARAM, optionalVehicleFullDto.get());

                return new Router(VEHICLE_INFO_PERSONAL);

            } else {
                request.setAttribute(FEEDBACK_MESSAGE_PARAM, "Vehicle not found");

                return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));
            }
        } catch (ServiceException | IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }

    @Override
    public DefaultForm doForm(HttpServletRequest request) {
        VehicleFullForm form = new VehicleFullForm();

        UserDto executor = (UserDto) request.getSession().getAttribute(USER_PARAM);
        if (executor == null) {
            throw new IllegalArgumentException("No user in the session");
        }

        String vehicleId = request.getParameter(VEHICLE_ID_PARAM);
        if (vehicleId == null || vehicleId.isBlank()) {
            throw new IllegalArgumentException("Wrong parameters");
        }

        try {
            form.setVehicleId(Long.parseLong(vehicleId));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing argument from String to Long");
        }

        String newComment = request.getParameter(VEHICLE_NEW_DESCRIPTION_PARAM);
        if (newComment == null) {
            throw new IllegalArgumentException("Wrong parameters");
        }

        form.setExecutor(executor);
        form.setComment(newComment);

        return form;
    }
}
