package by.spetr.web.command.user;

import by.spetr.web.command.Command;
import by.spetr.web.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Locale oldLocale = request.getLocale();

        return null; // todo:
    }
}
