package service.impl;

import dao.database.impl.DAOFactory;
import domain.entity.User;
import domain.entity.UserDTO;
import service.UserService;

import service.validator.ServiceValidator;

import java.util.Map;

import static service.UserFields.*;

public class UserServiceImpl implements UserService {


    @Override
    public UserDTO getUserByLoginAndPassword(String login, String password) {
        DAOFactory daoFactory = new DAOFactory();
        UserDTO dto = daoFactory.getUserDAO().getUserByLoginAndPass(login ,password);

        return dto;
    }

    @Override
    public boolean saveUser(Map<String, String> userMap) {
        User user = new User.UserBuilder()
                .setFirstname(userMap.get(FIRSTNAME))
                .setLastname(userMap.get(LASTNAME))
                .setUsername(userMap.get(USERNAME))
                .setRole("user").create();
        if(!ServiceValidator.validate(user))
        {
            return false;
        }
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getUserDAO().saveUser(user);
    }

    @Override
    public boolean changeUserFirstName(UserDTO user, String firstName) {
        if (!ServiceValidator.validate(firstName)){
            return false;
        }
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getUserDAO().changeUserFirstNameByID(user.getId(), firstName);
    }

    @Override
    public boolean changeUserLastName(UserDTO user, String lastName) {
        if (!ServiceValidator.validate(lastName)){
            return false;
        }
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getUserDAO().changeUserLastNameByID(user.getId(), lastName);
    }

    @Override
    public boolean changeUserRole(UserDTO user, String role) {
        if (!ServiceValidator.validate(role))
        {
            return false;
        }
        DAOFactory daoFactory = new DAOFactory();

        return  daoFactory.getUserDAO().changeUserRoleByID(user.getId(), role);
    }

    @Override
    public boolean changeUserPassword(UserDTO user, String oldPassword, String newPassword) {
        if (!ServiceValidator.validate(newPassword))
        {
            return false;
        }
        DAOFactory daoFactory = new DAOFactory();

        return  daoFactory.getUserDAO().changeUserPasswordByID(user.getId(), newPassword);
    }

    @Override
    public boolean checkUserIfExistsByLoginAndPass(String login, String pass) {
        if(!ServiceValidator.validate(login) || !ServiceValidator.validate(pass)){
            return false;
        }
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getUserDAO().userExistsByLoginAndPassword(login,pass);
    }

    @Override
    public UserDTO getUserByID(int id) {
        DAOFactory daoFactory = new DAOFactory();
        UserDTO result = daoFactory.getUserDAO().getUserByID(id);
        return result;
    }

    private UserDTO castUserDaoToService(dao.entity.UserDTO a)
    {
        return new UserDTO.UserDTOBuilder()
                .setID(a.getId())
                .setFirstname(a.getFirstName())
                .setLastname(a.getLastName())
                .setUsername(a.getUserName())
                .setRole(a.getRole())
                .create();
    }
}
