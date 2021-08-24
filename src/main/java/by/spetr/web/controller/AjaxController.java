package by.spetr.web.controller;

import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.VehicleService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;

@WebServlet(name = "AjaxController", urlPatterns = {"/ajaxController}"})
public class AjaxController extends HttpServlet {
    private static final VehicleService vehicleService = VehicleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String json = null;
        String command = req.getParameter("command");

        if (command.equals("colors")) {
            resp.setContentType("application/json");
            try {
                var colors = vehicleService.getAllColorList();
                json = new Gson().toJson(colors);
            } catch (ServiceException e) {
                resp.sendRedirect(ERROR_PAGE);
            }
        }

        resp.getWriter().write(json);
    }
}
