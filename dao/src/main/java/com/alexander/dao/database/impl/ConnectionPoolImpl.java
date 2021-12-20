package com.alexander.dao.database.impl;

import com.alexander.dao.database.ConnectionPool;
import com.alexander.dao.database.exeptions.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {
    private static ConnectionPool instance;
    private static final String DATABASE_CONFIG_PATH = "db.properties";
    private static final String DRIVER = "database.driver",
                                URL = "database.url",
                                USERNAME ="database.username",
                                PASSWORD ="database.password",
                                POOL_SIZE ="database.poolsize",
                                DATABASE_NAME="database.name";
    private String url;
    private String user;
    private String pass;
    private BlockingQueue<Connection> connectionPool;
    private BlockingQueue<Connection> usedConnections;
    private boolean driverIsConnected = false;
    private final int initialPoolSize;
    private Properties properties;



    public ConnectionPoolImpl() {
        loadProperties();
        url = getProperty(URL) + getProperty(DATABASE_NAME);
        user = getProperty(USERNAME);
        pass = getProperty(PASSWORD);
        initialPoolSize = Integer.parseInt(getProperty(POOL_SIZE));
        connectionPool = new ArrayBlockingQueue<>(initialPoolSize);
        usedConnections = new ArrayBlockingQueue<>(initialPoolSize);

        try {
            for (int i = 0; i < initialPoolSize; i++)
                connectionPool.add(getConnection(url, user, pass));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws DAOException {
        Connection connection;
        try {
            connection = connectionPool.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            throw new DAOException();
        }
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPoolImpl();
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
    private String getProperty(String key)
    {
        return properties.getProperty(key);
    }
    private Connection getConnection(String databaseURL, String login, String password) {
        Connection connection = null;
        try {
            getJDBCDriver();
            connection = DriverManager.getConnection(databaseURL, login, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    private void getJDBCDriver() {
        if (!driverIsConnected) {
            try {
                Class.forName(getProperty(DRIVER));
                driverIsConnected = true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void loadProperties() {
        try(InputStream is = ConnectionPool.class.getClassLoader().getResourceAsStream(DATABASE_CONFIG_PATH)) {
            System.out.println(is);
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
