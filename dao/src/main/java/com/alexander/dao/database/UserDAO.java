package com.alexander.dao.database;


import com.alexander.dao.database.exeptions.DAOExeption;
import com.alexander.domain.entity.User;
import com.alexander.domain.entity.UserDTO;

public interface UserDAO {
    boolean saveUser(User user) throws DAOExeption;
    boolean setUserBookmark(int id) throws DAOExeption;
    int  getUserIDbyLogin(String login) throws DAOExeption;
    UserDTO getUserByID(int id) throws DAOExeption;
    UserDTO getUserByLoginAndPass(String login, String password) throws DAOExeption;
    boolean deleteUserByID(int id) throws DAOExeption;
    boolean changeUserFirstNameByID(int id, String newUserFirstName) throws DAOExeption;
    boolean changeUserLastNameByID(int id, String newUserLastName) throws DAOExeption;
    boolean changeUserRoleByID(int id, String newRole) throws DAOExeption;
    boolean changeUsernameByID (int id, String newUsername) throws DAOExeption;
    boolean changeUserImageByID (int id ,String encodedImage) throws DAOExeption;
    boolean changeUserPasswordByID (int id, String password) throws DAOExeption;
    boolean isUserExistsByLoginAndPassword(String login, String password) throws DAOExeption;
}
