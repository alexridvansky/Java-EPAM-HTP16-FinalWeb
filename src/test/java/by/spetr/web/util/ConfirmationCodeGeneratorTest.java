package by.spetr.web.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfirmationCodeGeneratorTest {
    private static final int EXPECTED_CODE_LENGTH = 6;

    @Test
    public void confirmCodeNotNullTest() {
        String code = ConfirmationCodeGenerator.generateConfirmCode();

        Assert.assertNotNull(code);
    }

    @Test
    public void confirmCodeLengthTest() {
        String code = ConfirmationCodeGenerator.generateConfirmCode();

        Assert.assertEquals(code.length(), EXPECTED_CODE_LENGTH);
    }

    @Test
    public void confirmCodeNotEqualityTest() {
        String code1 = ConfirmationCodeGenerator.generateConfirmCode();
        String code2 = ConfirmationCodeGenerator.generateConfirmCode();

        Assert.assertNotEquals(code1, code2);
    }
}