package by.spetr.web.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PasswordGeneratorPassLengthTest {

    @Test(dataProvider = "validate_passgen")
    public void testGenerateNotNull(String generated_pass, int expectedLength) {
        Assert.assertEquals(generated_pass.length(), expectedLength);
    }

    @DataProvider(name = "validate_passgen")
    public Object[][] createData() {
        return new Object[][]{
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8},
                {PasswordGenerator.generate(), 8}
        };
    }
}