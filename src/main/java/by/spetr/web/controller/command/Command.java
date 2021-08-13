package by.spetr.web.controller.command;

import by.spetr.web.model.form.DefaultForm;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    /**
     * @param request {@code HttpServletRequest} request
     * @return {@code String} forwarding or redirecting path
     */
    Router execute(HttpServletRequest request);

    default DefaultForm doForm(HttpServletRequest request) {
        return new DefaultForm();
    }
}