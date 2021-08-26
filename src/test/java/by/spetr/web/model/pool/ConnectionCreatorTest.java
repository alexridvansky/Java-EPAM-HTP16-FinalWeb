package by.spetr.web.model.pool;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class ConnectionCreatorTest {
    @Test(dataProvider = "validate_connection")
    public void testCreateConnection(Connection connection, boolean expectedResult) {
        Assert.assertNotNull(connection);
    }

    @DataProvider(name = "validate_connection")
    public Object[][] createData() throws SQLException {
        return new Object[][]{
                {ConnectionCreator.createConnection(), true},
                {ConnectionCreator.createConnection(), true},
                {ConnectionCreator.createConnection(), true},
                {ConnectionCreator.createConnection(), true},
                {ConnectionCreator.createConnection(), true},
                {ConnectionCreator.createConnection(), true},
                {ConnectionCreator.createConnection(), true},
                {ConnectionCreator.createConnection(), true}
        };
    }
}