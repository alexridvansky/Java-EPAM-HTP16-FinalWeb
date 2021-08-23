package by.spetr.web.validator;

import by.spetr.web.util.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_USERNAME = "regexp.user.username";
    private static final String REGEXP_PASSWORD = "regexp.user.password";
    private static final String REGEXP_EMAIL = "regexp.user.email";
    private static final String REGEXP_PHONE = "regexp.user.phone";

    private static final PropertyReader PROPERTY_UTIL = PropertyReader.getInstance();

    private UserValidator() {}

    public static boolean validateUsername(String login) {
        logger.debug("login given: {}", login);

        return match(login, PROPERTY_UTIL.getRegexpProperty(REGEXP_USERNAME));
    }

    public static boolean validatePassword(String password) {

        return match(password, PROPERTY_UTIL.getRegexpProperty(REGEXP_PASSWORD));
    }

    public static boolean validateEmail(String email) {
        logger.debug("email given: {}", email);

        return match(email, PROPERTY_UTIL.getRegexpProperty(REGEXP_EMAIL));
    }

    public static boolean validatePhoneNumber(String phone) {
        logger.debug("phone given: {}", phone);

        return match(phone, PROPERTY_UTIL.getRegexpProperty(REGEXP_PHONE));
    }

    private static boolean match(String data, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }
}
