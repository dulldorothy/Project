package com.alexander.service.impl;

import com.alexander.dao.database.exeptions.DAOExeption;
import com.alexander.dao.database.impl.DAOFactory;
import com.alexander.domain.entity.Message;
import com.alexander.domain.entity.Page;
import com.alexander.service.MessageService;
import com.alexander.service.exeption.ServiceException;

public class MessageServiceImpl implements MessageService {
    private DAOFactory daoFactory;

    public MessageServiceImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Page<Message> getUnreadMessages(int userID, int offset, int recordsPerPage) throws ServiceException {
        try {
            return daoFactory.getMessageDAO().getUnreadMessagePage(userID,offset,recordsPerPage);
        } catch (DAOExeption daoExeption) {
            throw new ServiceException("Failed to get unread messages");
        }
    }

    @Override
    public Page<Message> getAllMessages(int userID, int offset, int recordsPerPage) throws ServiceException {
        try {
            return daoFactory.getMessageDAO().getAllMessagePage(userID,offset,recordsPerPage);
        } catch (DAOExeption daoExeption) {
            throw new ServiceException("Failed to get all messages");
        }
    }

    @Override
    public boolean changeReadStatus(int messageID) throws ServiceException {
        try {
            return daoFactory.getMessageDAO().changeMassageReadStatus(messageID);
        } catch (DAOExeption daoExeption) {
            throw new ServiceException("Failed to change message status");
        }
    }

    @Override
    public boolean sendMessage(int ownerID, int userID, int lotID) throws ServiceException {
        try {
            return daoFactory.getMessageDAO().sendMessage(ownerID,userID,lotID);
        } catch (DAOExeption daoExeption) {
            throw new ServiceException("Failed to save message");
        }
    }
}
