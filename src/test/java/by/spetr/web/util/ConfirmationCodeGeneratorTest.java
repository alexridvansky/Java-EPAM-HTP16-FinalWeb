package by.spetr.web.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfirmationCodeGeneratorTest {

    @Test
    public void confirmCodeLengthTest() {
        Assert.assertEquals(ConfirmationCodeGenerator.generateConfirmCode().length(), 6);
    }

    @Test
    public void confirmCodeNotEqualityTest() {
        Assert.assertFalse(ConfirmationCodeGenerator.generateConfirmCode().equals(ConfirmationCodeGenerator.generateConfirmCode()));
    }
}