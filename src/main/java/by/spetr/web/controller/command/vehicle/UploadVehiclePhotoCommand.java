package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.UserDto;
import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.DefaultForm;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.INDEX_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;


public class UploadVehiclePhotoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService service = VehicleService.getInstance();
    private static final String UPLOAD_SUCCESSFUL = "file uploaded";

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("UploadVehiclePhotos called");
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

        try {
            VehicleFullForm form = (VehicleFullForm) doForm(request);

            boolean isUploaded = service.uploadVehiclePhoto(form);

            Optional<VehicleFullDto> optionalVehicleFullDto = service.getFullDtoVehicleById(form.getVehicleId());

            if (isUploaded) {
                request.setAttribute(FEEDBACK_MESSAGE_PARAM, UPLOAD_SUCCESSFUL);
                request.setAttribute(OPERATION_SUCCESS_PARAM, true);
            }

            if (optionalVehicleFullDto.isPresent()) {
                request.setAttribute(VEHICLE_PARAM, optionalVehicleFullDto.get());
            } else {
                return new Router(ERROR_PAGE);
            }

            return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));

        } catch (ServiceException e) {
            logger.error("Error uploading photos", e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        } catch (IllegalArgumentException e) {
            logger.error("Illegal arguments in request, error uploading", e);
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, "Wrong parameters");

            return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));
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

        Set<String> filenames = (Set<String>) request.getAttribute(FILENAME_PARAM);
        if (filenames == null || filenames.isEmpty()) {
            throw new IllegalArgumentException("There's no files to upload");
        }

        form.setExecutor(executor);
        form.setPhotoSet(filenames);

        return form;
    }
}