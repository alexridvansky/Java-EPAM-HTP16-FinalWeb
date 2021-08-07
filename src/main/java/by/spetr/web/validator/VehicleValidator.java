package by.spetr.web.validator;

import by.spetr.web.util.RegexpPropertyUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleValidator {
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();
    private static final String REGEXP_MAKE = "regexp.vehicle_make";
    private static final String REGEXP_MODEL = "regexp.vehicle_model";

    private VehicleValidator() {}

    public static boolean validateMake(String make) {
        return match(make, regexpPropertyUtil.getProperty(REGEXP_MAKE));
    }

    public static boolean validateModel(String model) {
        return match(model, regexpPropertyUtil.getProperty(REGEXP_MODEL));
    }

    private static boolean match(String data, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }
}
