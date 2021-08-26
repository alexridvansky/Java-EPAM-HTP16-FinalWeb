package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.Page;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.VehiclePreviewDto;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.PagePath.VEHICLE_LIST_PUBLIC;
import static by.spetr.web.controller.command.RequestParameter.*;

public class ShowVehicleListPublicCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService vehicleService = DefaultVehicleService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_PARAM);

        try {
            int adCount = vehicleService.getPublicVehicleListSize();
            List<VehiclePreviewDto> vehicles = new ArrayList<>(adCount);
            int pageToDisplay = getPage(request);
            if (adCount > 0) {
                vehicles = vehicleService.getPublicVehicleList(Page.PAGE_SIZE, pageToDisplay);
            }

            request.setAttribute(PAGEABLE, new Page(adCount, pageToDisplay, Page.PAGE_SIZE));
            request.setAttribute(VEHICLE_LIST_PARAM, vehicles);

            return new Router(VEHICLE_LIST_PUBLIC);

        } catch (ServiceException | IllegalArgumentException e) {
            logger.error(e);
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, e.getMessage());

            return new Router(ERROR_PAGE);
        }
    }
}
