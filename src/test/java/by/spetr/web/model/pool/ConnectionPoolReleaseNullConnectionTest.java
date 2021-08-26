package by.spetr.web.model.pool;

import by.spetr.web.model.exception.ConnectionPoolException;
import by.spetr.web.util.PasswordGenerator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class ConnectionPoolReleaseNullConnectionTest {
    private static ConnectionPool connectionPool;

    @BeforeMethod
    public void setUp() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test(dataProvider = "null connection release")
    public void testReleaseConnection(ProxyConnection connection, boolean expectedResult) {
        boolean result = connectionPool.releaseConnection(connection);

        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider(name = "null connection release")
    public Object[][] createData() throws ConnectionPoolException, SQLException {
        return new Object[][]{
                {null, false}
        };
    }

    @AfterMethod
    public void tearDown() throws ConnectionPoolException {
        connectionPool.destroyPool();
    }
}