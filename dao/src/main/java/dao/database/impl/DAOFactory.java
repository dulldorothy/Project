package dao.database.impl;

import dao.database.ConnectionPool;
import dao.database.LotsDAO;
import dao.database.UserDAO;

public class DAOFactory {

    private final LotsDAO lotsDAO;
    private final UserDAO userDAO;

    public DAOFactory() {
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance(DatabaseConfig.getDatabaseURL(),
                DatabaseConfig.getLogin(), DatabaseConfig.getPassword());
        this.userDAO = new UserDAOImpl(connectionPool);
        this.lotsDAO = new LotsDAOImpl(connectionPool);
    }

    public LotsDAO getLotsDAO() {
        return lotsDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
