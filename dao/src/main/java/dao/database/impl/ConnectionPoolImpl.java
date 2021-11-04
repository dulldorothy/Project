package dao.database.impl;

import dao.database.ConnectionPool;
import dao.database.DataBaseConnector;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolImpl implements ConnectionPool {
    private String url;
    private String user;
    private String pass;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;

    public static ConnectionPoolImpl create(String url,  String user,String pass){
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i= 0; i < INITIAL_POOL_SIZE; i++)
        {
            pool.add(DataBaseConnector.getConnection(url,user, pass));
        }
        return new ConnectionPoolImpl(url,user,pass,pool);
    }


    public ConnectionPoolImpl(String url, String user, String pass, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.connectionPool = connectionPool;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
