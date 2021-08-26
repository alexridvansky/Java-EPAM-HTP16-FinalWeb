package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleValidatorValidateColorTest {

    @Test(dataProvider = "validate_color")
    public void testValidateUsername(String color, boolean expectedResult) {
        boolean actualResult = VehicleValidator.validateColor(color);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_color")
    public Object[][] createData() {
        return new Object[][]{
                {"green", true},
                {"1874", false},
                {"110", false},
                {"blue", true},
                {"20!9", false},
                {"white", true},
                {"silver", true},
                {"1", false},
                {"bronze", true},
                {"_", false},
                {"1", false},
                {"", false},
                {" ", false},
                {"красный", false}
        };
    }
}