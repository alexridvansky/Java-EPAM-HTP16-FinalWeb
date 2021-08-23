package by.spetr.web.util;

import java.util.Random;

public class ConfirmationCodeGenerator {
    private static final Random random = new Random();

    private ConfirmationCodeGenerator() {}

    public static String generateConfirmCode() {
        int random = 100000 + ConfirmationCodeGenerator.random.nextInt(900000);

        return String.valueOf(random);
    }
}
