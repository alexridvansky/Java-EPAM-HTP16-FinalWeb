package by.spetr.web.util.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleValidatorValidateModelYearTest {

    @Test(dataProvider = "validate_model_year")
    public void testValidateUsername(String modelYear, boolean expectedResult) {
        boolean actualResult = VehicleValidator.validateModelYear(modelYear);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "validate_model_year")
    public Object[][] createData() {
        return new Object[][]{
                {"2001", true},
                {"1874", false},
                {"110", false},
                {"2019", true},
                {"1991", true},
                {"2018", true},
                {"2005", true},
                {"12", false},
                {"1", false},
                {"", false},
                {" ", false}
        };
    }
}