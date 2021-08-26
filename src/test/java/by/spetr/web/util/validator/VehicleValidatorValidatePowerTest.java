package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleValidatorValidatePowerTest {

    @Test(dataProvider = "validate_power")
    public void testValidateUsername(int power, boolean expectedResult) {
        boolean actualResult = VehicleValidator.validatePower(power);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_power")
    public Object[][] createData() {
        return new Object[][]{
                {1_400, true},
                {110, true},
                {17, true},
                {7, true},
                {1, true},
                {0, false},
                {-3, false},
                {44_000, false},
                {140_000, false},
                {200_000, false},
                {350_000, false},
                {1_600_000, false},
                {13_600_000, false}
        };
    }
}