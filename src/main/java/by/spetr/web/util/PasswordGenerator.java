package by.spetr.web.util;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final String PASS_CHARSET_PROPERTY = "site.generate_pass_charset";
    private static final String PASS_LENGTH_PROPERTY = "site.generate_pass_length";
    private static final String CHAR_SET;
    private static final int PASS_LENGTH;

    static {
        CHAR_SET = PropertyUtil.getInstance().getSiteProperty(PASS_CHARSET_PROPERTY);
        PASS_LENGTH = Integer.parseInt(PropertyUtil.getInstance().getSiteProperty(PASS_LENGTH_PROPERTY));
    }

    private PasswordGenerator() {}

    public static String generate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < PASS_LENGTH; i++) {
            int randomIndex = random.nextInt(CHAR_SET.length());
            sb.append(CHAR_SET.charAt(randomIndex));
        }

        return sb.toString();
    }
}
