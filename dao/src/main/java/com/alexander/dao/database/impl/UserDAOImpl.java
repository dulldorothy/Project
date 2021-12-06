package com.alexander.dao.database.impl;

import com.alexander.dao.database.ConnectionPool;
import com.alexander.dao.database.UserDAO;

import com.alexander.dao.database.exeptions.DAOException;
import com.alexander.domain.entity.Page;
import com.alexander.domain.entity.User;
import com.alexander.domain.entity.UserDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static com.alexander.domain.fields.UserFields.*;


public class UserDAOImpl implements UserDAO {
    private static final String GET_USER_ID_BY_LOGIN = "SELECT id FROM users WHERE username = ?;";
    private static final String GET_ID_OF_USER_LOTS = "SELECT id FROM  lots WHERE user_owner_id = ?;";
    private static final String SET_USER_BOOKMARK = "INSERT INTO user_bookmarks (user_id, marked_lots_id) VALUES (?, '');";
    private static final String DELETE_USER_BY_ID = "DELETE FROM user_bookmarks WHERE user_id = ?;" +
            "DELETE FROM user_lots WHERE user_id = ?;" +
            "DELETE FROM users WHERE id = ?;";
    private static final String SAVE_USER = "INSERT INTO users (username, pass, lastname, firstname, role, encodedImage,email) VALUES(?,?,?,?,?,?,?);";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND pass = ?;";
    private static final String SET_USERIMAGE_BY_ID = "UPDATE users SET encodedImage = ? WHERE id = ?;";
    private static final String SET_FIRSTNAME_BY_ID = "UPDATE users SET firstname = ? WHERE id = ?;";
    private static final String SET_LASTNAME_BY_ID = "UPDATE users SET lastname = ? WHERE id = ?;";
    private static final String SET_PASS_BY_ID = "UPDATE users SET pass = ? WHERE id = ?;";
    private static final String SET_USERNAME_BY_ID = "UPDATE users SET username = & WHERE id = ?;";
    private static final String SET_ROLE_BY_ID = "UPDATE users SET role = ? WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";
    private static final String GET_NUMBER_OF_USERS = "SELECT COUNT(*) as column FROM users;";
    private ConnectionPool connectionPool;

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Page<UserDTO> getAllUsers(int offset, int recordsPerPage) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(offset);
        parameters.add(recordsPerPage);
        Connection connection = connectionPool.getConnection();
        setAutoCommit(connection, false);
        try (PreparedStatement statement1 = connection.prepareStatement(GET_NUMBER_OF_USERS);
             PreparedStatement statement2 = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet set1 = setStatement(statement1, null).executeQuery();
             ResultSet set2 = setStatement(statement2, parameters).executeQuery()) {
            set1.next();
            int numberOfUsers = set1.getInt(COLUMN);
            connection.commit();
            return new Page.PageBuilder<UserDTO>()
                    .setNumberOfPages((int) Math.ceil(numberOfUsers / (double) recordsPerPage))
                    .setListOfItems(ResultSetToListOfUsers(set2)).create();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return new Page.PageBuilder<UserDTO>()
                .setNumberOfPages(0)
                .setListOfItems(new ArrayList<UserDTO>()).create();
    }

    @Override
    public boolean saveUser(User user) throws DAOException {
        List<Object> parameters = userToListOfParameters(user);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SAVE_USER)) {
            setStatement(statement, parameters).execute();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("Failed to add User to database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean setUserBookmark(int id) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_USER_BOOKMARK)) {
            setStatement(statement, parameters).execute();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("Failed to add User to database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public int getUserIDbyLogin(String login) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(login);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_ID_BY_LOGIN)) {
            ResultSet set = setStatement(statement, parameters).executeQuery();
            set.next();

            return set.getInt("id");
        } catch (SQLException throwables) {
            throw new DAOException("Failed to add User to database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public UserDTO getUserByID(int id) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            ResultSet resultSet = setStatement(statement, parameters).executeQuery();
            return ResultSetToUser(resultSet);
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get User from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }


    @Override
    public UserDTO getUserByLoginAndPass(String login, String password) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(login);
        parameters.add(password);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            ResultSet resultSet = setStatement(statement, parameters).executeQuery();
            return ResultSetToUser(resultSet);
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get User from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean deleteUserByID(int id) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        List<Object> parameters2 = new ArrayList<>();
        parameters.add(id);
        parameters2.get(id);
        parameters2.get(id);
        parameters2.get(id);
        Connection connection = connectionPool.getConnection();
        setAutoCommit(connection, false);
        try (PreparedStatement statement1 = connection.prepareStatement(GET_ID_OF_USER_LOTS);
             ResultSet set1 = setStatement(statement1, parameters).executeQuery()) {
            String query = "";
            for (Integer lotID : getIDOfUserLots(set1)
            ) {
                query = query + "DELETE FROM messages WHERE lot_id = " + lotID + ";" +
                        "DELETE FROM user_bookmarks WHERE marked_lots_id = " + lotID + ";" +
                        "DELETE FROM lots WHERE id = " + lotID + ";";
            }
            query = query + DELETE_USER_BY_ID;
            PreparedStatement statement2 = connection.prepareStatement(query);
            ;
            setStatement(statement2, parameters2).executeUpdate();
            connection.commit();
            statement2.close();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("Failed to delete User from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    private void setAutoCommit(Connection connection, boolean b) throws DAOException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException();
        }
    }

    @Override
    public boolean changeUserFirstNameByID(int id, String newUserFirstName) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(newUserFirstName);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_FIRSTNAME_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change user firstname", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean changeUserLastNameByID(int id, String newUserLastName) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(newUserLastName);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_LASTNAME_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change user lastname", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean changeUserRoleByID(int id, String newRole) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(newRole);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_ROLE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change user role", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean changeUsernameByID(int id, String newUsername) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(newUsername);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_USERNAME_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change username", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean changeUserImageByID(int id, String encodedImage) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(encodedImage);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_USERIMAGE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change image", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public boolean changeUserPasswordByID(int id, String password) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(password);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_PASS_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change user password", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean isUserExistsByLoginAndPassword(String login, String password) throws DAOException {
        List<Object> param = new ArrayList<>();
        param.add(login);
        param.add(password);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            ResultSet set = setStatement(statement, param).executeQuery();
            return set.next();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get user from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    private PreparedStatement setStatement(PreparedStatement statement, List<Object> parameters) throws SQLException {
        int i = 1;
        if (parameters != null) {
            for (Object param : parameters) {
                if (param.getClass().getSimpleName().equals("String")) {
                    statement.setString(i, (String) param);
                } else if (param.getClass().getSimpleName().equals("Integer")) {
                    statement.setInt(i, (Integer) param);
                }
                i++;
            }
        }

        return statement;
    }

    private List<UserDTO> ResultSetToListOfUsers(ResultSet resultSet) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new UserDTO.UserDTOBuilder()
                    .setFirstname(resultSet.getString(FIRSTNAME))
                    .setLastname(resultSet.getString(LASTNAME))
                    .setUsername(resultSet.getString(USERNAME))
                    .setRole(resultSet.getString(ROLE))
                    .setID(resultSet.getInt(ID))
                    .setImage(resultSet.getString(ENCODED_IMAGE))
                    .create());
        }
        return list;
    }

    private UserDTO ResultSetToUser(ResultSet resultSet) throws SQLException {

        if (!resultSet.next()) {
            throw new SQLException();
        }

        return new UserDTO.UserDTOBuilder()
                .setFirstname(resultSet.getString(FIRSTNAME))
                .setLastname(resultSet.getString(LASTNAME))
                .setUsername(resultSet.getString(USERNAME))
                .setRole(resultSet.getString(ROLE))
                .setID(resultSet.getInt(ID))
                .setImage(resultSet.getString(ENCODED_IMAGE))
                .create();


    }

    private List<Object> userToListOfParameters(User user) {
        List<Object> resultList = new ArrayList<>();
        resultList.add(user.getUserName());
        resultList.add(user.getPassword());
        resultList.add(user.getLastName());
        resultList.add(user.getFirstName());
        resultList.add(user.getRole());
        resultList.add("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");
        resultList.add(user.getEmail());
        return resultList;
    }

    private List<Integer> getIDOfUserLots(ResultSet set) throws SQLException {
        List<Integer> lotsID = new ArrayList<>();
        while (set.next()) {
            lotsID.get(set.getInt("column"));
        }
        return lotsID;
    }


}
