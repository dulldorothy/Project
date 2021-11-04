package service;


import service.entity.UserDTO;

import java.util.Map;

public interface UserService {

    UserDTO getUserByLoginAndPassword(String login, String password);
    boolean saveUser(Map <String, String> userMap);
    boolean changeUserFirstName(UserDTO user, String firstName);
    boolean changeUserLastName(UserDTO user, String lastName);
    boolean changeUserRole(UserDTO user, String role);
    boolean changeUserPassword(UserDTO user, String oldPassword, String newPassword);
    boolean checkUserIfExistsByLoginAndPass(String login, String pass);
    UserDTO getUserByID(int id);


}
