package by.spetr.web.model.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionCreator {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties PROP = new Properties();
    private static final String PROPERTIES_PATH = "properties/db.properties";
    private static final String DRIVER_TAG = "db.driver";
    private static final String URL_TAG = "db.url";
    private static final String POOL_SIZE_TAG = "pool.size";
    private static final String JDBC_DRIVER_NAME;
    private static final String DB_URL;
    static final int POOL_SIZE;

    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream propertiesInput = classLoader.getResourceAsStream(PROPERTIES_PATH);
            PROP.load(propertiesInput);
            JDBC_DRIVER_NAME = PROP.getProperty(DRIVER_TAG);
            DB_URL = PROP.getProperty(URL_TAG);
            POOL_SIZE = Integer.parseInt(PROP.getProperty(POOL_SIZE_TAG));
        } catch (IOException e) {
            logger.fatal("Properties file not found or error loading properties from the file", e);
            throw new RuntimeException("Properties file not found or error loading properties from the file", e);
        }

        try {
            Class.forName(JDBC_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            logger.fatal("JDBCDriver class not found", e);
            throw new RuntimeException("JDBCDriver class not found", e);
        }
    }

    private ConnectionCreator() {}

    static Connection createConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(DB_URL, PROP);

        return connection;
    }
}
