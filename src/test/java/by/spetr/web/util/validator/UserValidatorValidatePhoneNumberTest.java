package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorValidatePhoneNumberTest {

    @Test(dataProvider = "validate_phone_number")
    public void testValidateUsername(String phoneNumber, boolean expectedResult) {
        boolean actualResult = UserValidator.validatePhoneNumber(phoneNumber);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_phone_number")
    public Object[][] createData() {
        return new Object[][]{
                {"+375292772455", true},
                {"+74951514455", false},
                {"+7495151445225", false},
                {"Жора", false},
                {"ann", false},
                {"+380845133546", true},
                {"1ann", false},
                {"+370868646755", true},
                {"", false},
                {" ", false}
        };
    }
}