package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorValidateEmailTest {

    @Test(dataProvider = "validate_email")
    public void testValidateUsername(String email, boolean expectedResult) {
        boolean actualResult = UserValidator.validateEmail(email);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_email")
    public Object[][] createData() {
        return new Object[][]{
                {"alexredvunsky@gmail.com", true},
                {"alexredvunsky@gmail.c", false},
                {"sputr@tut.by", true},
                {"ann", false},
                {"fairness@gmail.com", true},
                {"stupidity@mail.ru", true},
                {"curiosity@tut.by", true},
                {"trump@tut.by", true},
                {"barabashka@tut.by", true}
        };
    }
}