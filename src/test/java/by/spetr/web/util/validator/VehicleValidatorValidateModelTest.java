package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleValidatorValidateModelTest {

    @Test(dataProvider = "validate_model")
    public void testValidateUsername(String model, boolean expectedResult) {
        boolean actualResult = VehicleValidator.validateModel(model);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_model")
    public Object[][] createData() {
        return new Object[][]{
                {"Granta", true},
                {"&", false},
                {"&1", false},
                {"almera", true},
                {"911", true},
                {"megane", true},
                {"model s", true},
                {"412", true},
                {"2101", true},
                {"", false},
                {" ", false}
        };
    }
}