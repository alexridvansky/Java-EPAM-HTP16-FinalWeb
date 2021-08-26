package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleValidatorValidateDisplasementTest {

    @Test(dataProvider = "validate_displacement")
    public void testValidateUsername(int displacement, boolean expectedResult) {
        boolean actualResult = VehicleValidator.validateDisplacement(displacement);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_displacement")
    public Object[][] createData() {
        return new Object[][]{
                {2000, true},
                {1600, true},
                {110, true},
                {3500, true},
                {1997, true},
                {1400, true},
                {4400, true},
                {1400, true},
                {7, false},
                {1, false},
                {0, false},
                {-3, false}
        };
    }
}