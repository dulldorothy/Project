package com.alexander.dao.database.impl;

import com.alexander.dao.database.ConnectionPool;
import com.alexander.dao.database.LotsDAO;
import com.alexander.dao.database.MessageDAO;
import com.alexander.dao.database.UserDAO;

public class DAOFactory {
    private static  DAOFactory instance;
    private final LotsDAO lotsDAO;
    private final UserDAO userDAO;
    private final MessageDAO messageDAO;
    public DAOFactory() {
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        this.userDAO = new UserDAOImpl(connectionPool);
        this.lotsDAO = new LotsDAOImpl(connectionPool);
        this.messageDAO = new MessageDAOImpl(connectionPool);
    }

    public LotsDAO getLotsDAO() {
        return lotsDAO;
    }
    public static synchronized DAOFactory getInstance()
    {
        if (instance == null)
        {
            instance = new DAOFactory();
        }
        return instance;
    }
    public MessageDAO getMessageDAO() {
        return messageDAO;
    }
    public UserDAO getUserDAO() {
        return userDAO;
    }
}
