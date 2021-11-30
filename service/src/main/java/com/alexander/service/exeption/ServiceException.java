package com.alexander.service.exeption;

import com.alexander.dao.database.exeptions.DAOException;

public class ServiceException extends Exception{
    public ServiceException(String message, DAOException daoException) {
        super(message);
    }

    public ServiceException(String message) {
        super(message);
    }
}
