package by.spetr.web.controller.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    /**
     * @param request {@code HttpServletRequest} request
     * @return {@code String} forwarding or redirecting path
     */
    Router execute(HttpServletRequest request);
}
