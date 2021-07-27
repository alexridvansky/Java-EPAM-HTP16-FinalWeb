package by.spetr.web.command.vehicle;

import by.spetr.web.command.Command;
import by.spetr.web.command.PagePath;
import by.spetr.web.command.Router;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;


import static by.spetr.web.command.PagePath.INDEX_PAGE;
import static by.spetr.web.command.RequestParameter.*;


public class UploadVehiclePhotoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService service = DefaultVehicleService.getInstance();
    private static final String UPLOAD_SUCCESSFUL = "file uploaded";

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("UploadVehiclePhotos called");

        String vehicleId = request.getParameter(VEHICLE_ID_PARAM);
        logger.debug("vehicle_id: {}", vehicleId);
        String filename = (String) request.getAttribute(FILENAME_PARAM);
        logger.debug("filename: {}", filename);

        if (vehicleId == null || filename == null) {
            return new Router(PagePath.ERROR_PAGE);
        } else {
            try {
                logger.debug("last page: {}", request.getParameter(LAST_PAGE_PARAM));

                boolean isUploaded = service.uploadVehiclePhoto(Integer.parseInt(vehicleId), filename);

                String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);
                Optional<Vehicle> optionalVehicle = service.getVehicleById(Long.parseLong(vehicleId));

                if (isUploaded) {
                    request.setAttribute(MESSAGE_PARAM, UPLOAD_SUCCESSFUL);
                }

                if (optionalVehicle.isPresent()) {
                    request.setAttribute(VEHICLE_PARAM, optionalVehicle.get());
                } else {
                    return new Router(PagePath.ERROR_PAGE);
                }

                return new Router(Objects.requireNonNullElse(lastPage, INDEX_PAGE));
            } catch (ServiceException e) {
                e.printStackTrace(); // todo: custom_error_page
                return new Router(PagePath.ERROR_PAGE);
            }
        }
    }
}
