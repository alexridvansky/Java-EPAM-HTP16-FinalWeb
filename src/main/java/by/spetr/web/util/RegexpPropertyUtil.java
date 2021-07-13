package by.spetr.web.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegexpPropertyUtil {
    private static final RegexpPropertyUtil instance = new RegexpPropertyUtil();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("regexp", Locale.getDefault());

    private RegexpPropertyUtil() {
    }

    public static RegexpPropertyUtil getInstance() {
        return instance;
    }

    public String getProperty(String propertyName) {
        return resourceBundle.getString(propertyName);
    }
}
