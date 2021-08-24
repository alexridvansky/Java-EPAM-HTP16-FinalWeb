package by.spetr.web.controller;

import by.spetr.web.controller.command.Command;
import by.spetr.web.controller.command.CommandProvider;
import by.spetr.web.controller.command.RequestParameter;
import by.spetr.web.controller.command.Router;
import by.spetr.web.model.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * class {@code UploadController} is used for processing requests with multipart parameters
 */

@WebServlet(urlPatterns = {"/uploadController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final VehicleService service = VehicleService.getInstance();
    private static final String UPLOAD_DIRECTORY = "temp";

    /**
     * Redirects {@code doPost} requests
     *
     * @param request  - {@code HttpServletRequest} request
     * @param response - {@code HttpServletResponse} response
     * @throws ServletException – if the target resource throws this exception
     * @throws IOException      – if the target resource throws this exception
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes redirected doGet and doPost requests
     *
     * @param request  - {@code HttpServletRequest} request
     * @param response - {@code HttpServletResponse} response
     * @throws ServletException – if the target resource throws this exception
     * @throws IOException      – if the target resource throws this exception
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        logger.debug("UploadController - New request (Url: {})",
                request.getRequestURL() + (request.getParameter(RequestParameter.COMMAND_PARAM) != null
                        ? "?" + request.getParameter(RequestParameter.COMMAND_PARAM)
                        : ""));

        String commandName = request.getParameter(RequestParameter.COMMAND_PARAM);
        logger.debug("commandName: {}", commandName);

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        for (File subFile : Objects.requireNonNull(uploadDir.listFiles())) {
            subFile.delete();
        }

        Set<String> files = new HashSet<>();
        String fileName = null;

        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();

            if (fileName != null && !fileName.isBlank()) {
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
            request.setAttribute(FEEDBACK_MESSAGE_PARAM, "Incorrect router type: {}");
            request.setAttribute(EXCEPTION_MESSAGE_PARAM, "Router select error");
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
