package by.spetr.web.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PasswordGeneratorNotNullTest {

    @Test(dataProvider = "validate_passgen")
    public void testGenerateNotNull(String generated_pass, boolean expectedResult) {
        Assert.assertNotNull(generated_pass);
    }

    @DataProvider(name = "validate_passgen")
    public Object[][] createData() {
        return new Object[][]{
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true},
                {PasswordGenerator.generate(), true}
        };
    }
}