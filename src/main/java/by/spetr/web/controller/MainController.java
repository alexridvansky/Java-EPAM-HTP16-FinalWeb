package by.spetr.web.controller;

import by.spetr.web.controller.command.*;
import by.spetr.web.model.exception.ConnectionPoolException;
import by.spetr.web.model.pool.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;
import static by.spetr.web.controller.command.RequestParameter.EXCEPTION_MESSAGE_PARAM;
import static by.spetr.web.controller.command.RequestParameter.FEEDBACK_MESSAGE_PARAM;


/**
 * class {@code MainController} is used for processing requests matching '*.do' pattern
 */

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class MainController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Redirects {@code doGet} requests toward {@code MainController Servlet}
     *
     * @param request  - {@code HttpServletRequest} request
     * @param response - {@code HttpServletResponse} response
     * @throws ServletException – if the target resource throws this exception
     * @throws IOException      – if the target resource throws this exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("MainController: doGet is being called, redirecting...");
        processRequest(request, response);
    }

    /**
     * Redirects {@code doPost} requests toward {@code MainController Servlet}
     *
     * @param request  - {@code HttpServletRequest} request
     * @param response - {@code HttpServletResponse} response
     * @throws ServletException – if the target resource throws this exception
     * @throws IOException      – if the target resource throws this exception
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("MainController: doPost is being called, redirecting...");
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
        logger.debug("New request (Url: {})",
                request.getRequestURL() + (request.getQueryString() != null
                        ? "?" + request.getQueryString()
                        : ""));

        String commandName = request.getParameter(RequestParameter.COMMAND_PARAM);
        logger.debug("commandName: {}", commandName);
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
