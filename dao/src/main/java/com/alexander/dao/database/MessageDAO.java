package com.alexander.dao.database;

import com.alexander.dao.database.exeptions.DAOException;
import com.alexander.domain.entity.Message;
import com.alexander.domain.entity.Page;

public interface MessageDAO {
    boolean sendMessage(int ownerID, int userID, int lotID) throws DAOException;
    Page<Message> getUnreadMessagePage(int userID, int offset, int recordsPerPage) throws DAOException;
    Page<Message> getAllMessagePage(int userID, int offset, int recordsPerPage) throws DAOException;
    boolean changeMassageReadStatus(int id) throws DAOException;
}
