package com.alexander.dao.database;


import com.alexander.dao.database.exeptions.DAOException;
import com.alexander.domain.entity.Page;
import com.alexander.domain.entity.User;
import com.alexander.domain.entity.UserDTO;

public interface UserDAO {
    Page<UserDTO> getAllUsers(int offset, int recordsPerPage) throws DAOException;
    boolean saveUser(User user) throws DAOException;
    boolean setUserBookmark(int id) throws DAOException;
    int  getUserIDbyLogin(String login) throws DAOException;
    UserDTO getUserByID(int id) throws DAOException;
    UserDTO getUserByLoginAndPass(String login, String password) throws DAOException;
    boolean deleteUserByID(int id) throws DAOException;
    boolean changeUserFirstNameByID(int id, String newUserFirstName) throws DAOException;
    boolean changeUserLastNameByID(int id, String newUserLastName) throws DAOException;
    boolean changeUserRoleByID(int id, String newRole) throws DAOException;
    boolean changeUsernameByID (int id, String newUsername) throws DAOException;
    boolean changeUserImageByID (int id ,String encodedImage) throws DAOException;
    boolean changeUserPasswordByID (int id, String password) throws DAOException;
    boolean isUserExistsByLoginAndPassword(String login, String password) throws DAOException;
}
