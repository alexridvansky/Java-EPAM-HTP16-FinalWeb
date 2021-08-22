package by.spetr.web.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordGeneratorTest {
    private static final int EXPECTED_PASS_LENGTH = 8;

    @Test
    public void testGenerateNotNull() {
        String password = PasswordGenerator.generate();

        Assert.assertNotNull(password);
    }

    @Test
    public void testGenerateNotEquals() {
        String password1 = PasswordGenerator.generate();
        String password2 = PasswordGenerator.generate();

        Assert.assertNotEquals(password1, password2);
    }

    @Test
    public void testPasswordLength() {
        String password = PasswordGenerator.generate();
        int passLength = password.length();

        Assert.assertEquals(passLength, EXPECTED_PASS_LENGTH);
    }
}