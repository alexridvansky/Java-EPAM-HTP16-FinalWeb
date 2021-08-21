package by.spetr.web.util;

import java.util.Random;

public class PasswordGenerator {
    public static String generate() {
//        int random = 100000 + random_float() * 900000;
        Random rnd = new Random();
        int random = 100000 + rnd.nextInt(900000);
        return String.valueOf(random);
    }
}
