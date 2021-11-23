package service;




import domain.entity.UserDTO;
import service.exeption.ServiceExeption;

import java.util.Map;

public interface UserService {

    UserDTO getUserByLoginAndPassword(String login, String password) throws ServiceExeption;
    boolean saveUser(Map <String, String> userMap) throws ServiceExeption;
    boolean changeUserFirstName(UserDTO user, String firstName) throws ServiceExeption;
    boolean changeUserImage(UserDTO user, String encodedImage) throws ServiceExeption;
    boolean changeUserLastName(UserDTO user, String lastName) throws ServiceExeption;
    boolean changeUserRole(UserDTO user, String role) throws ServiceExeption;
    boolean changeUserPassword(UserDTO user, String oldPassword, String newPassword) throws ServiceExeption;
    boolean checkUserIfExistsByLoginAndPass(String login, String pass) throws ServiceExeption;
    UserDTO getUserByID(int id) throws ServiceExeption;


}
