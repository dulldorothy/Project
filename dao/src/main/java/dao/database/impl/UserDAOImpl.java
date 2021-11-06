package dao.database.impl;

import dao.database.ConnectionPool;
import dao.database.UserDAO;
import dao.database.exeptions.UserDAOExeption;
import domain.entity.User;
import domain.entity.UserDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";
    private static final String SAVE_USER = "INSERT INTO users (username, pass, lastname, firstname, role) VALUES(?,?,?,?,?);";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND pass = ?;";
    private static final String SET_FIRSTNAME_BY_ID = "UPDATE users SET firstname = ? WHERE id = ?;";
    private static final String SET_LASTNAME_BY_ID = "UPDATE users SET lastname = ? WHERE id = ?;";
    private static final String SET_PASS_BY_ID = "UPDATE users SET pass = ? WHERE id = ?;";
    private static final String SET_USERNAME_BY_ID = "UPDATE users SET username = & WHERE id = ?;";
    private static final String SET_ROLE_BY_ID = "UPDATE users SET role = ? WHERE id = ?;";

    ConnectionPool connectionPool;

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean saveUser(User user) {
        List<Object> parameters = user.userToListOfParameters();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SAVE_USER)) {
            System.out.println("Gnida");
            setStatement(statement, parameters).execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public UserDTO getUserByID(int id) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            ResultSet resultSet = setStatement(statement, parameters).executeQuery();
            return ResultSetToUser(resultSet);
        } catch (SQLException | UserDAOExeption throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }


    @Override
    public UserDTO getUserByLoginAndPass(String login, String password) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(login);
        parameters.add(password);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            ResultSet resultSet = setStatement(statement, parameters).executeQuery();
            return ResultSetToUser(resultSet);
        } catch (SQLException | UserDAOExeption throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public boolean deleteUserByID(int id) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            setStatement(statement, parameters).executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean changeUserFirstNameByID(int id, String newUserFirstName) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(newUserFirstName);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_FIRSTNAME_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean changeUserLastNameByID(int id, String newUserLastName) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(newUserLastName);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_LASTNAME_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean changeUserRoleByID(int id, String newRole) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(newRole);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_ROLE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean changeUsernameByID(int id, String newUsername) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(newUsername);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_USERNAME_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean changeUserPasswordByID(int id, String password) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(password);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_PASS_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean userExistsByLoginAndPassword(String login, String password) {
        List<Object> param = new ArrayList<>();
        param.add(login);
        param.add(password);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            ResultSet set = setStatement(statement, param).executeQuery();
            return set.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private PreparedStatement setStatement(PreparedStatement statement, List<Object> parameters) throws SQLException {
        int i = 1;
        for (Object param : parameters) {
            if (param.getClass().getSimpleName().equals("String")) {
                statement.setString(i, (String) param);
            } else if (param.getClass().getSimpleName().equals("Integer")) {
                statement.setInt(i, (Integer) param);
            }
            i++;
        }
        return statement;
    }

    private UserDTO ResultSetToUser(ResultSet resultSet) throws SQLException, UserDAOExeption {

        if (!resultSet.next()) {
            throw new UserDAOExeption("No such login or password!");
        }

        return new UserDTO.UserDTOBuilder()
                .setFirstname(resultSet.getString("firstname"))
                .setLastname(resultSet.getString("lastname"))
                .setUsername(resultSet.getString("username"))
                .setRole(resultSet.getString("role"))
                .setID(resultSet.getInt("id")).create();


    }

}
