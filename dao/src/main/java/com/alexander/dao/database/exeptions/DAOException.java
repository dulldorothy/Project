package com.alexander.dao.database.exeptions;

import java.sql.SQLException;

public class DAOException extends Exception {
    public DAOException() {
    }

    public DAOException(String message, SQLException throwables) {
        super(message);
    }
}
