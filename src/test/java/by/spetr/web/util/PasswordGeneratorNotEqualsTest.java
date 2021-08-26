package by.spetr.web.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PasswordGeneratorNotEqualsTest {

    @Test(dataProvider = "validate_passgen")
    public void testGenerateNotNull(String generated_pass_one, String generated_pass_two) {
        Assert.assertNotEquals(generated_pass_one, generated_pass_two);
    }

    @DataProvider(name = "validate_passgen")
    public Object[][] createData() {
        return new Object[][]{
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()},
                {PasswordGenerator.generate(), PasswordGenerator.generate()}
        };
    }
}