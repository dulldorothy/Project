package com.alexander.service;

import com.alexander.dao.database.impl.DAOFactory;
import com.alexander.service.impl.LotServiceImpl;
import com.alexander.service.impl.MessageServiceImpl;
import com.alexander.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance;
    private UserService userService;
    private LotsService lotsService;
    private MessageService messageService;


    public UserService getUserService() {
        return userService;
    }

    public LotsService getLotsService() {
        return lotsService;
    }
    public MessageService getMessageService(){
        return messageService;
    }
    private ServiceFactory() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        userService = new UserServiceImpl(daoFactory);
        messageService = new MessageServiceImpl(daoFactory);
        lotsService = new LotServiceImpl(daoFactory);
    }

    public static ServiceFactory getInstance() {
        if (instance == null)
        {
            instance = new ServiceFactory();
        }
        return instance;
    }
}
