package com.alexander.service.impl;

import com.alexander.dao.database.exeptions.DAOExeption;
import com.alexander.dao.database.impl.DAOFactory;
import com.alexander.domain.entity.User;
import com.alexander.domain.entity.UserDTO;
import com.alexander.service.UserService;

import com.alexander.service.exeption.ServiceException;
import com.alexander.service.validator.ServiceValidator;

import java.util.Map;

import static com.alexander.domain.fields.UserFields.*;

public class UserServiceImpl implements UserService {
    private DAOFactory daoFactory;
    public UserServiceImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public UserDTO getUserByLoginAndPassword(String login, String password) throws ServiceException {
        DAOFactory daoFactory = new DAOFactory();
        UserDTO dto = null;
        try {
            return daoFactory.getUserDAO().getUserByLoginAndPass(login ,password);
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to get user from database by login and password", e);
        }


    }

    @Override
    public boolean deleteUserByID(int id) throws ServiceException {
        DAOFactory daoFactory = new DAOFactory();
        try
        {
            daoFactory.getUserDAO().deleteUserByID(id);
            return true;
        } catch (DAOExeption daoExeption) {
            throw new ServiceException("failed to delete user");
        }

    }

    @Override
    public boolean saveUser(Map<String, String> userMap) throws ServiceException {
        User user = new User.UserBuilder()
                .setFirstname(userMap.get(FIRSTNAME))
                .setLastname(userMap.get(LASTNAME))
                .setUsername(userMap.get(USERNAME))
                .setPassword(userMap.get(PASSWORD))
                .setRole(USER_ROLE).
                setEmail(userMap.get(EMAIL)).
                create();


        if(!ServiceValidator.validate(user))
        {
           throw new ServiceException("User validation failed");
        }

        DAOFactory daoFactory = new DAOFactory();
        try {
            daoFactory.getUserDAO().saveUser(user);
            int id = daoFactory.getUserDAO().getUserIDbyLogin(userMap.get(USERNAME));
            daoFactory.getUserDAO().setUserBookmark(id);
            return true;
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to save user", e);
        }
    }

    @Override
    public boolean changeUserFirstName(UserDTO user, String firstName) throws ServiceException {
        if (!ServiceValidator.validate(firstName)){
            throw new ServiceException("Failed to validate firstname");
        }
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().changeUserFirstNameByID(user.getId(), firstName);
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to change firstname", e);
        }
    }

    @Override
    public boolean changeUserImage(UserDTO user, String encodedImage) throws ServiceException {

        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().changeUserImageByID(user.getId(), encodedImage);
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to change lastname",e);
        }
    }

    @Override
    public boolean changeUserLastName(UserDTO user, String lastName) throws ServiceException {
        if (!ServiceValidator.validate(lastName)){
            throw new ServiceException("Failed to validate lastname");
        }
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().changeUserLastNameByID(user.getId(), lastName);
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to change lastname",e);
        }
    }

    @Override
    public boolean changeUserRole(UserDTO user, String role) throws ServiceException {
        if (!ServiceValidator.validate(role))
        {
            throw new ServiceException("Failed to validate user role");
        }
        DAOFactory daoFactory = new DAOFactory();

        try {
            return  daoFactory.getUserDAO().changeUserRoleByID(user.getId(), role);
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to change user role",e );
        }
    }

    @Override
    public boolean changeUserPassword(UserDTO user, String newPassword) throws ServiceException {
        if (!ServiceValidator.validate(newPassword))
        {
            throw new ServiceException("Failed to validate password");
        }
        DAOFactory daoFactory = new DAOFactory();

        try {
            return  daoFactory.getUserDAO().changeUserPasswordByID(user.getId(), newPassword);
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to change user password", e);
        }
    }

    @Override
    public boolean checkUserIfExistsByLoginAndPass(String login, String pass) throws ServiceException {
        if(!ServiceValidator.validate(login) || !ServiceValidator.validate(pass)){
            throw new ServiceException("User validation failed");
        }
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().isUserExistsByLoginAndPassword(login,pass);
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to get user by login and password", e);
        }
    }

    @Override
    public UserDTO getUserByID(int id) throws ServiceException {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getUserDAO().getUserByID(id);
        } catch (DAOExeption e) {
            throw new ServiceException("Failed to get user by id",e);
        }

    }


}
