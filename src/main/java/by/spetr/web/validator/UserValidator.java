package by.spetr.web.validator;

import by.spetr.web.util.RegexpPropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_USERNAME = "regexp.username";
    private static final String REGEXP_PASSWORD = "regexp.password";
    private static final String REGEXP_EMAIL = "regexp.email";
    private static final String REGEXP_PHONE = "regexp.phone";

    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    private UserValidator() {}

    public static boolean validateUsername(String login) {
        logger.debug("login given: {}", login);

        return match(login, regexpPropertyUtil.getProperty(REGEXP_USERNAME));
    }

    public static boolean validatePassword(String password) {

        return match(password, regexpPropertyUtil.getProperty(REGEXP_PASSWORD));
    }

    public static boolean validateEmail(String email) {
        logger.debug("email given: {}", email);

        return match(email, regexpPropertyUtil.getProperty(REGEXP_EMAIL));
    }

    public static boolean validatePhoneNumber(String phone) {
        logger.debug("phone given: {}", phone);

        return match(phone, regexpPropertyUtil.getProperty(REGEXP_PHONE));
    }

    private static boolean match(String data, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }
}
