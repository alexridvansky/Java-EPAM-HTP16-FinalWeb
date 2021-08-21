package by.spetr.web.util;

import java.util.Random;

public class RandomGenerator {
    public static String generateConfirmCode() {
        Random rnd = new Random();
        int random = 100000 + rnd.nextInt(900000);
        return String.valueOf(random);
    }
}
