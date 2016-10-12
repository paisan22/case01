package database;

import java.sql.*;
import java.util.List;

/**
 * Created by paisanrietbroek on 07/10/16.
 */
abstract class DAO <T> {

    private final static
        String driver = "oracle.jdbc.driver.OracleDriver",
             database = "jdbc:oracle:thin:@localhost:1521:xe",
             username = "system",
             password = "oracle";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName (driver);
        connection = DriverManager.getConnection(database, username, password);
        connection.setAutoCommit(false);
    }

    void closeEverything() throws SQLException {
        connection.commit();         // commit to make it persisted
        preparedStatement.close();
        if (resultSet != null) {
            resultSet.close();
        }
        connection.close();
    }

    public abstract List<?> getAll() throws SQLException, ClassNotFoundException;
    public abstract T get(int id) throws SQLException, ClassNotFoundException;
    public abstract int add(T object) throws SQLException, ClassNotFoundException;
    public abstract int delete(int id) throws SQLException, ClassNotFoundException;
    public abstract int update(T t) throws SQLException, ClassNotFoundException;


}
