package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleValidatorValidateMileageTest {

    @Test(dataProvider = "validate_mileage")
    public void testValidateUsername(int mileage, boolean expectedResult) {
        boolean actualResult = VehicleValidator.validateMileage(mileage);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_mileage")
    public Object[][] createData() {
        return new Object[][]{
                {200_000, true},
                {1_600_000, true},
                {110, true},
                {350_000, true},
                {17, true},
                {140_000, true},
                {44_000, true},
                {1_400, true},
                {7, true},
                {1, true},
                {0, true},
                {-3, false},
                {13_600_000, false}
        };
    }
}