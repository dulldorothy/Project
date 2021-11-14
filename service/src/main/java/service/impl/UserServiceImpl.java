package service.impl;

import dao.database.exeptions.DAOExeption;
import dao.database.impl.DAOFactory;
import domain.entity.User;
import domain.entity.UserDTO;
import service.UserService;

import service.exeption.ServiceExeption;
import service.validator.ServiceValidator;

import java.util.Map;

import static service.UserFields.*;

public class UserServiceImpl implements UserService {


    @Override
    public UserDTO getUserByLoginAndPassword(String login, String password) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        UserDTO dto = null;
        try {
            return daoFactory.getUserDAO().getUserByLoginAndPass(login ,password);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to get user from database by login and password", e);
        }


    }

    @Override
    public boolean saveUser(Map<String, String> userMap) throws ServiceExeption {
        User user = new User.UserBuilder()
                .setFirstname(userMap.get(FIRSTNAME))
                .setLastname(userMap.get(LASTNAME))
                .setUsername(userMap.get(USERNAME))
                .setPassword(userMap.get(PASSWORD))
                .setRole("user").create();

        if(!ServiceValidator.validate(user))
        {
           throw new ServiceExeption("User validation failed");
        }

        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().saveUser(user);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to save user", e);
        }
    }

    @Override
    public boolean changeUserFirstName(UserDTO user, String firstName) throws ServiceExeption {
        if (!ServiceValidator.validate(firstName)){
            throw new ServiceExeption("Failed to validate firstname");
        }
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().changeUserFirstNameByID(user.getId(), firstName);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change firstname", e);
        }
    }

    @Override
    public boolean changeUserLastName(UserDTO user, String lastName) throws ServiceExeption {
        if (!ServiceValidator.validate(lastName)){
            throw new ServiceExeption("Failed to validate lastname");
        }
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().changeUserLastNameByID(user.getId(), lastName);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change lastname",e);
        }
    }

    @Override
    public boolean changeUserRole(UserDTO user, String role) throws ServiceExeption {
        if (!ServiceValidator.validate(role))
        {
            throw new ServiceExeption("Failed to validate user role");
        }
        DAOFactory daoFactory = new DAOFactory();

        try {
            return  daoFactory.getUserDAO().changeUserRoleByID(user.getId(), role);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change user role",e );
        }
    }

    @Override
    public boolean changeUserPassword(UserDTO user, String oldPassword, String newPassword) throws ServiceExeption {
        if (!ServiceValidator.validate(newPassword))
        {
            throw new ServiceExeption("Failed to validate password");
        }
        DAOFactory daoFactory = new DAOFactory();

        try {
            return  daoFactory.getUserDAO().changeUserPasswordByID(user.getId(), newPassword);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change user password", e);
        }
    }

    @Override
    public boolean checkUserIfExistsByLoginAndPass(String login, String pass) throws ServiceExeption {
        if(!ServiceValidator.validate(login) || !ServiceValidator.validate(pass)){
            throw new ServiceExeption("User validation failed");
        }
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().userExistsByLoginAndPassword(login,pass);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to get user by login and password", e);
        }
    }

    @Override
    public UserDTO getUserByID(int id) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().getUserByID(id);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to get user by id",e);
        }

    }


}
