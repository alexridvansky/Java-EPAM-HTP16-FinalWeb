package by.spetr.web.model.pool;

import by.spetr.web.model.exception.ConnectionPoolException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolTest {
    private static final int EXPECTED_POOL_SIZE = 8;
    private static ConnectionPool connectionPool;

    @BeforeMethod
    public void setUp() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test
    public void testGetConnection() throws ConnectionPoolException, SQLException {
        List<Connection> connections = new ArrayList<>();

        try {
            for (int i = 0; i < 8; i++) {
                Connection connection = connectionPool.getConnection();
                connections.add(connection);
            }

            Assert.assertEquals(connections.size(), EXPECTED_POOL_SIZE);
        } finally {
            for (Connection connection : connections) {
                connection.close();
            }
        };
    }
}