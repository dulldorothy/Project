package com.alexander.service;

import com.alexander.domain.entity.Message;
import com.alexander.domain.entity.Page;
import com.alexander.service.exeption.ServiceException;

public interface MessageService {
    Page<Message> getUnreadMessages(int userID, int offset, int recordsPerPage) throws ServiceException;
    Page<Message> getAllMessages(int userID, int offset, int recordsPerPage) throws ServiceException;
    boolean changeReadStatus(int messageID) throws  ServiceException;
    boolean sendMessage(int ownerID, int userID, int lotID) throws ServiceException;

}
