package by.spetr.web.controller;

import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.VehicleService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.spetr.web.controller.command.PagePath.ERROR_PAGE;

@WebServlet(name = "AjaxController", urlPatterns = {"/ajaxController}"})
public class AjaxController extends HttpServlet {
    VehicleService vehicleService = VehicleService.getInstance();

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
