package by.spetr.web.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyUtil {
    private static final PropertyUtil instance = new PropertyUtil();
    private static final ResourceBundle REGEXP = ResourceBundle.getBundle("regexp", Locale.getDefault());
    private static final ResourceBundle BOT = ResourceBundle.getBundle("bot", Locale.getDefault());
    public static final ResourceBundle SITE = ResourceBundle.getBundle("site", Locale.getDefault());

    private PropertyUtil() {}

    public static PropertyUtil getInstance() {
        return instance;
    }

    public String getRegexpProperty(String propertyName) {
        return REGEXP.getString(propertyName);
    }

    public String getBotProperty(String propertyName) {
        return BOT.getString(propertyName);
    }

    public String getSiteProperty(String propertyName) {
        return SITE.getString(propertyName);
    }
}
