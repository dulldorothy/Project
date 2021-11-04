package dao.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnector {


    private static boolean isDriverConnected = false;

    public static Connection getConnection(String databaseURL, String login, String password) {

        Connection connection = null;
        try {
            getJDBCDriver();
            connection = DriverManager.getConnection(databaseURL, login, password);
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    private static void getJDBCDriver()
    {
        if (!isDriverConnected)
        {
            try{
                Class.forName("org.postgresql.Driver");
                isDriverConnected = true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
