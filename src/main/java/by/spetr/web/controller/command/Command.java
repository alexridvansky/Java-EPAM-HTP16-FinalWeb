package by.spetr.web.controller.command;

import by.spetr.web.model.form.DefaultForm;
import jakarta.servlet.http.HttpServletRequest;

import static by.spetr.web.controller.command.RequestParameter.PAGE_PARAM;

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

    default int getPage(HttpServletRequest request) {
        int page = 1;
        String stringPage = request.getParameter(PAGE_PARAM);

        if (stringPage != null) {
            try {
                page = Integer.parseInt(stringPage);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error parsing page number, argument illegal.");
            }
        }
        return page;
    }
}