package by.spetr.web.controller.command.vehicle;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.PagePath;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.dto.MakeModelListDto;
import by.spetr.web.model.entity.VehicleMake;
import by.spetr.web.model.entity.VehicleModel;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.spetr.web.controller.command.PagePath.VEHICLE_CREATION_PAGE;
import static by.spetr.web.controller.command.RequestParameter.VEHICLE_MAKE_MODEL_LIST;

public class GetMakeModelListCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_GETTING_MODEL_LIST = "Error getting makes or models list from Vehicle.service";
    VehicleService vehicleService = DefaultVehicleService.getInstance();


    @Override
    public Router execute(HttpServletRequest request) {

        try {
            List<VehicleMake> makes = vehicleService.getMakeList();
            List<VehicleModel> models = vehicleService.getModelList();

            MakeModelListDto makeModelListDto = new MakeModelListDto();
            makeModelListDto.setMakes(makes);
            makeModelListDto.setModels(models);

            request.setAttribute(VEHICLE_MAKE_MODEL_LIST, makeModelListDto);

            return new Router(VEHICLE_CREATION_PAGE);

        } catch (ServiceException e) {
            // todo: go to some show_user_error.page
            logger.error(ERROR_GETTING_MODEL_LIST, e);
            return new Router(PagePath.ERROR_PAGE);
        }
    }
}
