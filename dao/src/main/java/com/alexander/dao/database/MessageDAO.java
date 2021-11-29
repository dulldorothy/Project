package com.alexander.dao.database;

import com.alexander.dao.database.exeptions.DAOExeption;
import com.alexander.domain.entity.Message;
import com.alexander.domain.entity.Page;

public interface MessageDAO {
    boolean sendMessage(int ownerID, int userID, int lotID) throws DAOExeption;
    Page<Message> getUnreadMessagePage(int userID, int offset, int recordsPerPage) throws DAOExeption;
    Page<Message> getAllMessagePage(int userID, int offset, int recordsPerPage) throws DAOExeption;
    boolean changeMassageReadStatus(int id) throws DAOExeption;
}
