package dao.database;

import dao.entity.User;
import dao.entity.UserDTO;

public interface UserDAO {
    boolean saveUser(User user);
    UserDTO getUserByID(int id);
    UserDTO getUserByLoginAndPass(String login, String password);
    boolean deleteUserByID(int id);
    boolean changeUserFirstNameByID(int id, String newUserFirstName);
    boolean changeUserLastNameByID(int id, String newUserLastName);
    boolean changeUserRoleByID(int id, String newRole);
    boolean changeUsernameByID (int id, String newUsername);
    boolean changeUserPasswordByID (int id, String password);
    boolean userExistsByLoginAndPassword(String login, String password);
}
