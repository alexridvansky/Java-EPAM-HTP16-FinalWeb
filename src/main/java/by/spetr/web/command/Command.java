package by.spetr.web.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    /*
     * @param {@code HttpServletRequest} request
     * @return {@code String} forwarding or redirecting path
     */
    String execute(HttpServletRequest request);
}
