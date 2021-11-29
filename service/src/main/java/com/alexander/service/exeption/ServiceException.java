package com.alexander.service.exeption;

import com.alexander.dao.database.exeptions.DAOExeption;

public class ServiceException extends Exception{
    public ServiceException(String message, DAOExeption daoExeption) {
        super(message);
    }

    public ServiceException(String message) {
        super(message);
    }
}
