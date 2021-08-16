package by.spetr.web.validator;

import by.spetr.web.util.RegexpPropertyUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleValidator {
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();
    private static final String REGEXP_MAKE = "regexp.vehicle_make";
    private static final String REGEXP_MODEL = "regexp.vehicle_model";
    private static final String REGEXP_MODEL_YEAR = "regexp.vehicle_model_year";
    private static final String REGEXP_COLOR = "regexp.vehicle_color";

    private VehicleValidator() {}

    public static boolean validateMake(String make) {
        return match(make, regexpPropertyUtil.getProperty(REGEXP_MAKE));
    }

    public static boolean validateModel(String model) {
        return match(model, regexpPropertyUtil.getProperty(REGEXP_MODEL));
    }

    public static boolean validateModelYear(String modelYear) {
        return match(modelYear, regexpPropertyUtil.getProperty(REGEXP_MODEL_YEAR));
    }

    public static boolean validateColor(String color) {
        return match(color, regexpPropertyUtil.getProperty(REGEXP_COLOR));
    }

    public static boolean validateDisplacement(int displacement) {
        return (displacement > 9 && displacement <= 16000);
    }

    public static boolean validateMileage(int mileage) {
        return (mileage >= 0 && mileage < 10_000_000);
    }

    public static boolean validatePower(int power) {
        return (power > 0 && power <= 9_000);
    }

    public static boolean validatePrice(int price) {
        return (price >= 0 && price < 10_000_000);
    }

    private static boolean match(String data, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }
}
