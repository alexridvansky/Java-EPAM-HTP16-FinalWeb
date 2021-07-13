package by.spetr.web.model.dao;

import by.spetr.web.model.entity.AbstractEntity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class AbstractDAO <T extends AbstractEntity> {
    private static final Logger logger = LogManager.getLogger();

    public void close(Statement statement){
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("Error during closing statement", e);
            // todo: ???
        }
    }

    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Error during returning connection to the pool", e);
            // todo: ???
        }
    }
}
