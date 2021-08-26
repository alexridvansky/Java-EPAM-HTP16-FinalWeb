package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleValidatorValidatePriceTest {

    @Test(dataProvider = "validate_price")
    public void testValidateUsername(int price, boolean expectedResult) {
        boolean actualResult = VehicleValidator.validatePrice(price);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_price")
    public Object[][] createData() {
        return new Object[][]{
                {1_600_000, true},
                {350_000, true},
                {200_000, true},
                {140_000, true},
                {1_400, true},
                {44_000, true},
                {110, true},
                {17, true},
                {7, true},
                {1, true},
                {0, true},
                {-3, false},
                {13_600_000, false}
        };
    }
}