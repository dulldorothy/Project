package com.alexander.dao.database.impl;

import com.alexander.dao.database.ConnectionPool;

import com.alexander.dao.database.LotsDAO;
import com.alexander.dao.database.exeptions.DAOException;
import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.Page;
import com.alexander.domain.entity.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.alexander.domain.fields.UserFields.*;

public class LotsDAOImpl implements LotsDAO {

    private static final String SAVE_LOT = "INSERT INTO lots (user_owner_id, title, price, is_active_status, " +
            "encodedimage,tag_list,description) " +
            "VALUES(?,?,?,?,?,?,?);";
    private static final String GET_NUMBER_OF_USER_RECORDS = "SELECT COUNT(*) as column FROM lots WHERE user_owner_id = ?;";
    private static final String GET_NUMBER_OF_USER_BOOKMARKS = "SELECT marked_lots_id from user_bookmarks WHERE user_id = ?;";//todo innerS JOIN
    private static final String ADD_LOT_TO_USER_BOOKMARK = "INSERT INTO user_bookmarks (user_id, marked_lots_id) VALUES(?,?);";
    private static final String GET_NUMBER_OF_RECORDS = "SELECT COUNT(*) as column FROM lots;";
    private static final String GET_NUMBER_OF_ACTIVE_RECORDS = "SELECT COUNT(*) as column FROM lots WHERE is_active_status = 'active';";
    private static final String GET_NUMBER_OF_TAG_RECORDS = "SELECT COUNT(*) as column FROM lots WHERE is_active_status = 'active' AND tag_list = ?;";
    private static final String SELECT_LOT_PAGE_BY_ID = "SELECT users.firstname, users.lastname, lots.* FROM lots INNER JOIN users ON lots.user_owner_id = users.id " +
            "WHERE lots.id = ?;";
    private static final String DELETE_LOT_BY_ID = "DELETE FROM messages WHERE lot_id = ?;" +
            "DELETE FROM user_bookmarks WHERE marked_lots_id = ?;" +
            "DELETE FROM lots WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM lots OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";
    private static final String SELECT_ALL_USER_BOOKMARKS = "SELECT * FROM lots WHERE is_active_status = 'active' AND (";
    private static final String SELECT_ACTIVE_LOTS = "SELECT * FROM lots WHERE is_active_status = 'active' OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    private static final String CHANGE_PRICE_BY_ID = "UPDATE lots SET price = ? WHERE id = ?;";
    private static final String CHANGE_TITLE_BY_ID = "UPDATE lots SET title = ? WHERE id = ?;";
    private static final String CHANGE_STATUS_BY_ID = "UPDATE lots SET is_active_status = ? WHERE id = ?;";
    private static final String CHANGE_DESCRIPTION_BY_ID = "UPDATE lots SET description = ? WHERE id = ?;";
    private static final String CHANGE_IMAGE_BY_ID = "UPDATE lots SET encodedimage = ? WHERE id = ?;";
    private static final String SELECT_LOTS_BY_TAG = "SELECT * FROM lots WHERE tag_list = ? AND is_active_status = 'active' OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";
    private static final String SELECT_LOTS_BY_USER = "SELECT * FROM lots WHERE user_owner_id = ? OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";

    private ConnectionPool connectionPool;

    public LotsDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean saveLot(Lot item) throws DAOException {
        List<Object> parameters = LotToListOfParameters(item);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SAVE_LOT)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to add lot to database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public boolean deleteLotById(int id) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        parameters.add(id);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_LOT_BY_ID)) {
            setStatement(statement, parameters).executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("Failed to delete lot from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }


    }

    @Override
    public boolean addLotToUserBookmark(int userID, int lotID) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(userID);
        parameters.add(lotID);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_LOT_TO_USER_BOOKMARK)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change status!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Page<Lot> getAll(int offset, int recordPerPage) throws DAOException {
        List<Object> parameters1 = new ArrayList<>();

        parameters1.add(offset);
        parameters1.add(recordPerPage);

        Connection connection = connectionPool.getConnection();
        setAutoCommit(connection, false);
        try (PreparedStatement statement1 = connection.prepareStatement(SELECT_ALL);
             PreparedStatement statement2 = connection.prepareStatement(GET_NUMBER_OF_RECORDS);
             ResultSet set1 = setStatement(statement1, parameters1).executeQuery();
             ResultSet set2 = setStatement(statement2, null).executeQuery()
        ) {
            set2.next();
            int numberOfPages = (int) Math.ceil(set2.getInt(COLUMN)/(double)recordPerPage);

            connection.commit();
            return new Page.PageBuilder<Lot>()
                    .setListOfItems(resultSetToListOfLots(set1))
                    .setNumberOfPages(numberOfPages)
                    .create();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get lots from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }


    @Override
    public Page<Lot> getActiveLots(int offset, int recordPerPage) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(offset);
        parameters.add(recordPerPage);
        Connection connection = connectionPool.getConnection();
        setAutoCommit(connection, false);
        try (PreparedStatement statement1 = connection.prepareStatement(SELECT_ACTIVE_LOTS);
             PreparedStatement statement2 = connection.prepareStatement(GET_NUMBER_OF_ACTIVE_RECORDS);
             ResultSet set1 = setStatement(statement1, parameters).executeQuery();
             ResultSet set2 = setStatement(statement2, null).executeQuery()
        ) {
            set2.next();
            int numberOfPages = (int) Math.ceil(set2.getInt(COLUMN)/(double)recordPerPage);

            connection.commit();
            return new Page.PageBuilder<Lot>()
                    .setNumberOfPages(numberOfPages)
                    .setListOfItems(resultSetToListOfLots(set1))

                    .create();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get active lots from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public Page<Lot> getLotsByTag(int offset, int recordPerPage, String tag) throws DAOException {
        List<Object> parameters1 = new ArrayList<>();
        List<Object> parameters2 = new ArrayList<>();
        parameters1.add(tag);
        parameters1.add(offset);
        parameters1.add(recordPerPage);
        parameters2.add(tag);
        Connection connection = connectionPool.getConnection();
        setAutoCommit(connection, false);
        try (PreparedStatement statement1 = connection.prepareStatement(SELECT_LOTS_BY_TAG);
             PreparedStatement statement2 = connection.prepareStatement(GET_NUMBER_OF_TAG_RECORDS);
             ResultSet set1 = setStatement(statement1, parameters1).executeQuery();
             ResultSet set2 = setStatement(statement2, parameters2).executeQuery()
        ) {
            set2.next();
            int numberOfPages = (int) Math.ceil(set2.getInt(COLUMN)/(double)recordPerPage);
            connection.commit();
            return new Page.PageBuilder<Lot>()
                    .setListOfItems(resultSetToListOfLots(set1))
                    .setNumberOfPages(numberOfPages)
                    .create();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get" + tag + "lots from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public Page<Lot> getAllUserLots(int offset, int recordsPerPage, int userID) throws DAOException {
        List<Object> parameters1 = new ArrayList<>();
        List<Object> parameters2 = new ArrayList<>();
        parameters1.add(userID);
        parameters1.add(offset);
        parameters1.add(recordsPerPage);
        parameters2.add(userID);
        Connection connection = connectionPool.getConnection();
        setAutoCommit(connection, false);
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LOTS_BY_USER);
             PreparedStatement statement2 = connection.prepareStatement(GET_NUMBER_OF_USER_RECORDS);
             ResultSet set1 = setStatement(statement, parameters1).executeQuery();
             ResultSet set2 = setStatement(statement2, parameters2).executeQuery()
        ) {
            set2.next();
            int numberOfPages = (int) Math.ceil(set2.getInt(COLUMN)/(double)recordsPerPage);
            connection.commit();
            return new Page.PageBuilder<Lot>()
                    .setListOfItems(resultSetToListOfLots(set1))
                    .setNumberOfPages(numberOfPages)
                    .create();
        } catch (SQLException throwables) {
            throw new DAOException();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Page<Lot> getAllUserBookmarkLots(int offset, int recordsPerPage, int userID) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        List<Integer> LotsID = getIDOfUserBookmarkLots(userID);
        parameters.add(offset);
        parameters.add(recordsPerPage);
        StringBuilder query = new StringBuilder(SELECT_ALL_USER_BOOKMARKS);
        if (LotsID.isEmpty())
        {
            return new Page.PageBuilder<Lot>()
                .setNumberOfPages(0)
                .setListOfItems(new ArrayList<Lot>())
                .create();
        }

        for (Integer id : LotsID) query.append("  id = ").append(id.toString()).append(" OR");
        query = new StringBuilder(query.substring(0, query.length() - 2) + ") OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query.toString());
             ResultSet set = setStatement(statement, parameters).executeQuery()) {
            int numberOfPages = (int) Math.ceil(LotsID.size()/(double)recordsPerPage);


            return new Page.PageBuilder<Lot>()
                    .setNumberOfPages(numberOfPages)
                    .setListOfItems(resultSetToListOfLots(set))
                    .create();
        } catch (SQLException throwables) {
            throw new DAOException();
        }
    }

    @Override
    public Page<Lot> getLotPageByID(int id) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement1 = connection.prepareStatement(SELECT_LOT_PAGE_BY_ID);
        ResultSet set = setStatement(statement1,parameters).executeQuery()) {
            set.next();
            return new Page.PageBuilder<Lot>()
                    .setUserOwner(
                            new UserDTO.UserDTOBuilder()
                            .setFirstname(set.getString(FIRSTNAME))
                            .setLastname(set.getString(LASTNAME))
                            .create())
                    .setLot(
                            new Lot.LotBuilder()
                                    .setPrice(set.getInt(PRICE))
                                    .setDescription(set.getString(DESCRIPTION))
                                    .setImage(set.getString(ENCODED_IMAGE))
                                    .setStatus(set.getString(LOT_STATUS))
                                    .setTagList(set.getString(TAG_LIST))
                                    .setId(set.getInt(ID))
                                    .setUserOwnerID(set.getInt(OWNER_ID))
                                    .setTitle(set.getString(TITLE))
                                    .create()
                    ).create();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to select lot!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public int getNumberOfUserLots(int userID) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(userID);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_USER_RECORDS)) {
            ResultSet set = setStatement(statement, parameters).executeQuery();
            set.next();
            return set.getInt(COLUMN);
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get number of user lots!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private List<Integer> getIDOfUserBookmarkLots(int userID) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(userID);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_USER_BOOKMARKS)) {
            ResultSet set = setStatement(statement, parameters).executeQuery();
            List<Integer> list = new ArrayList<>();
            while (set.next()) {
                list.add(set.getInt(MARKED_LOT));
            }
            return list;
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get number of user lots!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public int getNumberOfActiveLotsByTag(String tag) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(tag);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_TAG_RECORDS)) {
            ResultSet set = setStatement(statement, parameters).executeQuery();
            set.next();
            return set.getInt(COLUMN);
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get number of active lot!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public int getNumberOfActiveLots() throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_ACTIVE_RECORDS)) {
            ResultSet set = setStatement(statement, null).executeQuery();
            set.next();
            return set.getInt(COLUMN);
        } catch (SQLException throwables) {
            throw new DAOException("Failed to get number of active lot!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    @Override
    public boolean changeLotPriceById(int id, int price) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(price);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_PRICE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change price !", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean changeLotTitleById(int id, String title) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(title);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_TITLE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change title !", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }


    }

    @Override
    public boolean changeLotStatusById(int id, String status) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(status);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change status!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }


    }

    @Override
    public boolean changeLotDescriptionByID(int id, String description) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(description);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(CHANGE_DESCRIPTION_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change status!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean changeLotImageByID(int id, String encodedImage) throws DAOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(encodedImage);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(CHANGE_IMAGE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw new DAOException("Failed to change status!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    private PreparedStatement setStatement(PreparedStatement statement, List<Object> parameters) throws SQLException {
        if (parameters != null) {
            int i = 1;
            for (Object param : parameters) {
                switch (param.getClass().getSimpleName()) {
                    case STRING:
                        statement.setString(i, (String) param);
                        break;
                    case INTEGER:
                        statement.setInt(i, (Integer) param);
                        break;
                }

                i++;
            }
        }
        return statement;
    }

    private List<Lot> resultSetToListOfLots(ResultSet set) throws SQLException {
        List<Lot> result = new ArrayList<>();
        while (set.next()) {
            Lot item = new Lot.LotBuilder()
                    .setId(set.getInt(ID))
                    .setPrice(set.getInt(PRICE))
                    .setTagList(set.getString(TAG_LIST))
                    .setStatus(set.getString(LOT_STATUS))
                    .setTitle(set.getString(TITLE))
                    .setImage(set.getString(ENCODED_IMAGE.toLowerCase(Locale.ROOT)))
                    .setUserOwnerID(set.getInt(OWNER_ID))
                    .setDescription(set.getString(DESCRIPTION))
                    .create();
            result.add(item);

        }
        return result;
    }
    private List<Object> LotToListOfParameters(Lot lot) {
        List<Object> resultList = new ArrayList<>();
        resultList.add(lot.getUserOwnerID());
        resultList.add(lot.getTitle());
        resultList.add(lot.getPrice());
        resultList.add(lot.getStatus());
        resultList.add(lot.getImage());
        resultList.add(lot.getTagList());
        resultList.add(lot.getDescription());

        return resultList;
    }
    private void setAutoCommit(Connection connection, boolean b) throws DAOException {
        try {
            connection.setAutoCommit(b);
        } catch (SQLException e) {
            throw new DAOException();
        }
    }
}
