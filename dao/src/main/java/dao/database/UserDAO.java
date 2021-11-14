package dao.database;


import dao.database.exeptions.DAOExeption;
import domain.entity.User;
import domain.entity.UserDTO;

public interface UserDAO {
    boolean saveUser(User user) throws DAOExeption;
    UserDTO getUserByID(int id) throws DAOExeption;
    UserDTO getUserByLoginAndPass(String login, String password) throws DAOExeption;
    boolean deleteUserByID(int id) throws DAOExeption;
    boolean changeUserFirstNameByID(int id, String newUserFirstName) throws DAOExeption;
    boolean changeUserLastNameByID(int id, String newUserLastName) throws DAOExeption;
    boolean changeUserRoleByID(int id, String newRole) throws DAOExeption;
    boolean changeUsernameByID (int id, String newUsername) throws DAOExeption;
    boolean changeUserPasswordByID (int id, String password) throws DAOExeption;
    boolean userExistsByLoginAndPassword(String login, String password) throws DAOExeption;
}
