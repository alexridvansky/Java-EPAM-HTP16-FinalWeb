package model.pool;

import by.spetr.web.model.entity.User;
import by.spetr.web.model.pool.ConnectionPool;
import by.spetr.web.model.exception.ConnectionPoolException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolGenericTest {

    @Test
    public void testAllinAll() throws ConnectionPoolException {
        //ConnectionPoolGeneric<ProxyConnection> pool = ConnectionPoolGeneric.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        System.out.println(connection);
        //ProxyConnection connection1 = ConnectionCreator.createConnection();
        try (Statement statement = connection.createStatement()){
            String sql = "SELECT * FROM user";
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> results = new ArrayList<>();
//            while (resultSet.next()) {
//                results.add(new User(
//                        resultSet.getLong("user_id"),
//                        resultSet.getString("login"),
//                        resultSet.getByte(4),
//                        resultSet.getByte(5),
//                        resultSet.getString(6)
//                ));
//            }
            results.forEach(System.out::println);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}