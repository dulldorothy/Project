package com.alexander.service;




import com.alexander.domain.entity.Page;
import com.alexander.domain.entity.UserDTO;
import com.alexander.service.exeption.ServiceException;

import java.util.Map;

public interface UserService {

    UserDTO getUserByLoginAndPassword(String login, String password) throws ServiceException;
    boolean deleteUserByID(int id) throws ServiceException;
    boolean saveUser(Map <String, String> userMap) throws ServiceException;
    boolean changeUserFirstName(UserDTO user, String firstName) throws ServiceException;
    boolean changeUserImage(UserDTO user, String encodedImage) throws ServiceException;
    boolean changeUserLastName(UserDTO user, String lastName) throws ServiceException;
    boolean changeUserRole(UserDTO user, String role) throws ServiceException;
    boolean changeUserPassword(UserDTO user, String newPassword) throws ServiceException;
    boolean checkUserIfExistsByLoginAndPass(String login, String pass) throws ServiceException;
    UserDTO getUserByID(int id) throws ServiceException;
    Page<UserDTO> getAllUsers(int offset, int recordsPerPage) throws ServiceException;

}
