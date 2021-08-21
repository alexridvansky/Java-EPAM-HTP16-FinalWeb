package by.spetr.web.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomGeneratorTest {

    @Test
    public void confirmCodeLengthTest() {
        Assert.assertEquals(RandomGenerator.generateConfirmCode().length(), 6);
    }

    @Test
    public void confirmCodeNotEqualityTest() {
        Assert.assertFalse(RandomGenerator.generateConfirmCode().equals(RandomGenerator.generateConfirmCode()));
    }
}