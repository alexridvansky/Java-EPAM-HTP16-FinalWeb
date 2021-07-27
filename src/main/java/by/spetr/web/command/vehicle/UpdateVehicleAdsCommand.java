package by.spetr.web.command.vehicle;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateVehicleAdsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("UpdateVehicleAdsCommand called");



        return null;
    }
}
