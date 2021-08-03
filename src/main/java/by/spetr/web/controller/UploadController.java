package by.spetr.web.controller;

import by.spetr.web.command.*;
import by.spetr.web.model.service.DefaultVehicleService;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static by.spetr.web.command.RequestParameter.FILENAME_PARAM;

/**
 * class {@code UploadController} is used for processing requests with multipart parameters
 */

@WebServlet(urlPatterns = {"/uploadController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService service = DefaultVehicleService.getInstance();
    private static final String UPLOAD_DIRECTORY = "temp";

    /**
     * Redirects {@code doPost} requests
     *
     * @param request - {@code HttpServletRequest} request
     * @param response - {@code HttpServletResponse} response
     * @throws ServletException – if the target resource throws this exception
     * @throws IOException – if the target resource throws this exception
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("UploadController: doPost is being called, redirecting...");
        processRequest(request, response);
    }

    /**
     * Processes redirected doGet and doPost requests
     *
     * @param request - {@code HttpServletRequest} request
     * @param response - {@code HttpServletResponse} response
     * @throws ServletException – if the target resource throws this exception
     * @throws IOException – if the target resource throws this exception
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        logger.debug("UploadController - New request (Url: {})",
                request.getRequestURL() + (request.getParameter(RequestParameter.COMMAND) != null
                        ? "?" + request.getParameter(RequestParameter.COMMAND)
                        : ""));

        String commandName = request.getParameter(RequestParameter.COMMAND);
        logger.debug("commandName: {}", commandName);

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        Set<String> files = new HashSet<>();
        String fileName = null;

        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();

            if (fileName != null) {
                part.write(uploadPath + File.separator + fileName);
                files.add(uploadPath + File.separator + fileName);
            }
        }

        request.setAttribute(FILENAME_PARAM, files);

        Command command = CommandProvider.getInstance().getCommand(commandName);
        Router router = command.execute(request);

        if (router.getRouterType() == Router.RouterType.REDIRECT) {
            response.sendRedirect(router.getPagePath());
        } else if (router.getRouterType() == Router.RouterType.FORWARD) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
            dispatcher.forward(request, response);
        } else {
            logger.error("Incorrect router type: {}", router.getRouterType());
            response.sendRedirect(PagePath.ERROR_PAGE);
            // todo: error_page?
        }
    }
}
