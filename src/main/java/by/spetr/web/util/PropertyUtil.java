package by.spetr.web.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyUtil {
    private static final PropertyUtil instance = new PropertyUtil();
    private final ResourceBundle regexpResourceBundle = ResourceBundle.getBundle("regexp", Locale.getDefault());
    private final ResourceBundle botResourceBundle = ResourceBundle.getBundle("bot", Locale.getDefault());

    private PropertyUtil() {}

    public static PropertyUtil getInstance() {
        return instance;
    }

    public String getRegexpProperty(String propertyName) {
        return regexpResourceBundle.getString(propertyName);
    }

    public String getBotProperty(String propertyName) {
        return botResourceBundle.getString(propertyName);
    }
}
