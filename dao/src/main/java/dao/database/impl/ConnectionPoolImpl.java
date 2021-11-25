package dao.database.impl;

import dao.database.ConnectionPool;
import dao.database.DataBaseConnector;
import dao.database.exeptions.DAOExeption;


import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {
    private static ConnectionPool instance;
    private String url;
    private String user;
    private String pass;
    private BlockingQueue<Connection> connectionPool;
    private BlockingQueue<Connection> usedConnections;
    private static int INITIAL_POOL_SIZE = 50;



    public ConnectionPoolImpl(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;

        this.connectionPool = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        this.usedConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);

        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++)
                connectionPool.add(DataBaseConnector.getConnection(url, user,pass));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws DAOExeption {
        Connection connection;
        try{
            connection = connectionPool.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            throw new DAOExeption();
        }
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
    public static ConnectionPool getInstance(String url, String user, String pass)
    {
        if (instance == null)
        {
            instance= new ConnectionPoolImpl( url,  user,  pass);
        }
        return instance;
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
