package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorValidateUsernameTest {

    @Test(dataProvider = "validate_username")
    public void testValidateUsername(String username, boolean expectedResult) {
        boolean actualResult = UserValidator.validateUsername(username);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_username")
    public Object[][] createData() {
        return new Object[][]{
                {"Sasha", true},
                {"as", false},
                {"Жора", false},
                {"ann", false},
                {"anna", true},
                {"1ann", false},
                {"ann1", true}
        };
    }
}