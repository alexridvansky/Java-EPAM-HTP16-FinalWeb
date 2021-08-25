package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorValidatePasswordTest {

    @Test(dataProvider = "validate_password")
    public void testValidateUsername(String password, boolean expectedResult) {
        boolean actualResult = UserValidator.validatePassword(password);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_password")
    public Object[][] createData() {
        return new Object[][]{
                {"pass1", true},
                {"as", false},
                {"Жора", true},
                {"ann", false},
                {"anna", true},
                {"1ann", true},
                {"ann1", true},
                {"0000", true},
                {"01aa", true}
        };
    }
}