package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleValidatorValidateMakeTest {

    @Test(dataProvider = "validate_make")
    public void testValidateUsername(String make, boolean expectedResult) {
        boolean actualResult = VehicleValidator.validateMake(make);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_make")
    public Object[][] createData() {
        return new Object[][]{
                {"lada", true},
                {"zaz", false},
                {"Жора", false},
                {"nissan", true},
                {"anna", true},
                {"1ann", false},
                {"ann1", true},
                {"moskvich", true},
                {"жигули", false},
                {"", false},
                {" ", false}
        };
    }
}