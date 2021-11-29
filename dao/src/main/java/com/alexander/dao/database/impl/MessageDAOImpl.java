package com.alexander.dao.database.impl;

import com.alexander.dao.database.ConnectionPool;
import com.alexander.dao.database.MessageDAO;
import com.alexander.dao.database.exeptions.DAOExeption;
import com.alexander.domain.entity.Message;
import com.alexander.domain.entity.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.alexander.domain.fields.UserFields.*;

public class MessageDAOImpl implements MessageDAO {
    private static final String SAVE_MESSAGE = "INSERT INTO messages (to_id, from_id, lot_id, read_status) VALUES (?,?,?,?);";
    private static final String GET_UNREAD_MESSAGES = "SELECT messages.id, users.email, users.username, lots.title FROM ((messages " +
            "INNER JOIN users ON messages.from_id = users.id)" +
            "INNER JOIN lots ON messages.lot_id = lots.id)" +
            "WHERE messages.to_id = ? AND messages.read_status = false  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    private static final String GET_NUMBER_OF_UNREAD_MESSAGES = "SELECT COUNT(*) as column FROM ((messages " +
            "INNER JOIN users ON messages.from_id = users.id)" +
            "INNER JOIN lots ON messages.lot_id = lots.id)" +
            "WHERE messages.to_id = ? AND messages.read_status = false ;";
    private static final String GET_ALL_MESSAGES = "SELECT messages.id, users.email, users.username, lots.title FROM ((messages " +
            "INNER JOIN users ON messages.from_id = users.id)" +
            "INNER JOIN lots ON messages.lot_id = lots.id)" +
            "WHERE messages.to_id = ? OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    private static final String GET_NUMBER_OF_ALL_MESSAGES = "SELECT COUNT(*) as column FROM ((messages " +
            "INNER JOIN users ON messages.from_id = users.id)" +
            "INNER JOIN lots ON messages.lot_id = lots.id)" +
            "WHERE messages.to_id = ?;";
    private static final String CHANGE_READ_STATUS = "UPDATE messages SET read_status = true WHERE id = ?";
    private final ConnectionPool connectionPool;

    public MessageDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean sendMessage(int ownerID, int userID, int lotID) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(ownerID);
        parameters.add(userID);
        parameters.add(lotID);
        parameters.add(false);

        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SAVE_MESSAGE)) {
            setStatement(statement, parameters).execute();
            return true;
        } catch (SQLException throwables) {
            throw new DAOExeption();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Page<Message> getUnreadMessagePage(int userID, int offset, int recordsPerPage) throws DAOExeption {
        List<Object> parameters1 = new ArrayList<>();
        List<Object> parameters2 = new ArrayList<>();
        parameters1.add(userID);
        parameters1.add(offset);
        parameters1.add(recordsPerPage);
        parameters2.add(userID);
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement1 = connection.prepareStatement(GET_UNREAD_MESSAGES);
             PreparedStatement statement2 = connection.prepareStatement(GET_NUMBER_OF_UNREAD_MESSAGES);
             ResultSet set1 = setStatement(statement1, parameters1).executeQuery();
             ResultSet set2 = setStatement(statement2, parameters2).executeQuery()) {
            set2.next();
            return new Page.PageBuilder<Message>()
                    .setListOfItems(resultSetToListOfMessages(set1))
                    .setNumberOfPages((int) Math.ceil(set2.getInt(COLUMN)/recordsPerPage))
                    .create();
        } catch (SQLException throwables) {
            throw new DAOExeption();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Page<Message> getAllMessagePage(int userID, int offset, int recordsPerPage) throws DAOExeption {
        List<Object> parameters1 = new ArrayList<>();
        List<Object> parameters2 = new ArrayList<>();
        parameters1.add(userID);
        parameters1.add(offset);
        parameters1.add(recordsPerPage);
        parameters2.add(userID);
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement1 = connection.prepareStatement(GET_ALL_MESSAGES);
             PreparedStatement statement2 = connection.prepareStatement(GET_NUMBER_OF_ALL_MESSAGES);
             ResultSet set1 = setStatement(statement1, parameters1).executeQuery();
             ResultSet set2 = setStatement(statement2, parameters2).executeQuery()) {
            connection.setAutoCommit(false);
            set2.next();
            connection.commit();
            return new Page.PageBuilder<Message>()
                    .setListOfItems(resultSetToListOfMessages(set1))
                    .setNumberOfPages((int) Math.ceil(set2.getInt(COLUMN)/recordsPerPage))
                    .create();
        } catch (SQLException throwables) {
            throw new DAOExeption();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public boolean changeMassageReadStatus(int id) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_READ_STATUS)) {
            setStatement(statement, parameters).execute();
            return true;
        } catch (SQLException throwables) {
            throw new DAOExeption();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private PreparedStatement setStatement(PreparedStatement statement, List<Object> parameters) throws SQLException {
        int i = 1;
        if (parameters != null) {
            for (Object param : parameters) {
                if (param.getClass().getSimpleName().equals(STRING)) {
                    statement.setString(i, (String) param);
                } else if (param.getClass().getSimpleName().equals(INTEGER)) {
                    statement.setInt(i, (Integer) param);
                } else if (param.getClass().getSimpleName().equals(BOOLEAN)) {
                    statement.setBoolean(i, (Boolean) param);
                }
                i++;
            }
        }
        return statement;
    }

    private List<Message> resultSetToListOfMessages(ResultSet set) throws SQLException {
        List<Message> list = new ArrayList<>();
        while (set.next()) {
            list.add(new Message.MessageBuilder()
                    .setId(set.getInt(ID))
                    .setBuyerName(set.getString(USERNAME))
                    .setLotName(set.getString(TITLE))
                    .setEmail(set.getString(EMAIL))
                    .create());
        }
        return list;
    }

}
