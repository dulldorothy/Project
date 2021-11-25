package dao.database.impl;

import dao.database.ConnectionPool;

import dao.database.LotsDAO;
import dao.database.exeptions.DAOExeption;
import domain.entity.Lot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static domain.entity.UserFields.*;
public class LotsDAOImpl implements LotsDAO {

    private static final String SAVE_LOT = "INSERT INTO lots (user_owner_id, title, price, is_active_status, " +
            "encodedimage,tag_list,description) " +
            "VALUES(?,?,?,?,?,?,?);";//TODO rewrite SQL query
    private static final String GET_NUMBER_OF_USER_RECORDS = "SELECT COUNT(*) as column FROM lots WHERE user_owner_id = ?;";
    private static final String GET_NUMBER_OF_USER_BOOKMARKS = "SELECT marked_lots_id from user_bookmarks WHERE user_id = ?;";
    private static final String ADD_LOT_TO_USER_BOOKMARK = "UPDATE user_bookmarks SET marked_lots_id  = ? WHERE user_id = ?";

    private static final String GET_NUMBER_OF_RECORDS = "SELECT COUNT(*) as column FROM lots WHERE is_active_status = 'active';";
    private static final String GET_NUMBER_OF_TAG_RECORDS = "SELECT COUNT(*) as column FROM lots WHERE is_active_status = 'active' AND tag_list = ?;";
    private static final String SELECT_LOT_BY_ID = "SELECT * FROM lots WHERE id = ?";
    private static final String DELETE_LOT_BY_ID = "DELETE FROM lots WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM lots OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";
    private static final String SELECT_ALL_USER_BOOLMARKS = "SELECT * FROM lots WHERE";
    private static final String SELECT_ACTIVE_LOTS = "SELECT * FROM lots WHERE is_active_status = 'active' OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    private static final String CHANGE_PRICE_BY_ID = "UPDATE lots SET price = ? WHERE id = ?;";
    private static final String CHANGE_TITLE_BY_ID = "UPDATE lots SET title = ? WHERE id = ?;";
    private static final String CHANGE_STATUS_BY_ID = "UPDATE lots SET is_active_status = ? WHERE id = ?;";
    private static final String SELECT_LOTS_BY_TAG = "SELECT * FROM lots WHERE tag_list = ? OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";
    private static final String SELECT_LOTS_BY_USER = "SELECT * FROM lots WHERE user_owner_id = ? OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";

    ConnectionPool connectionPool;

    public LotsDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean saveLot(Lot item) throws DAOExeption {
        List<Object> parameters = item.LotToListOfParameters();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SAVE_LOT)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
           throw new DAOExeption("Failed to add lot to database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public boolean deleteLotById(int id) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_LOT_BY_ID)) {
            setStatement(statement, parameters).executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throw new DAOExeption("Failed to delete lot from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }



    }

    @Override
    public boolean addLotToUserBookmark(int userID, int lotID) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();

        String lotsID = getIDOfUserBookmarkLots(userID);
        if (lotsID.contains(lotID + ";"))
        {
           return false;
        }
        lotsID = lotsID + lotID +";";
        parameters.add( lotsID);
        parameters.add(userID);
        Connection connection = connectionPool.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(ADD_LOT_TO_USER_BOOKMARK)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to change status!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Lot> getAll(int offset, int recordPerPage) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(offset);
        parameters.add(recordPerPage);

        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet set = setStatement(statement, parameters).executeQuery();
            return resultSetToListOfLots(set);
        } catch (SQLException throwables) {
           throw  new DAOExeption("Failed to get lots from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }


    @Override
    public List<Lot> getActiveLots(int offset, int recordPerPage) throws DAOExeption {

        List<Object> parameters = new ArrayList<>();
        parameters.add(offset);
        parameters.add(recordPerPage);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ACTIVE_LOTS)) {
            ResultSet set = setStatement(statement, parameters).executeQuery();
           return resultSetToListOfLots(set);
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to get active lots from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public List<Lot> getLotsByTag(int offset, int recordPerPage, String tag) throws DAOExeption {
        List<Lot> result;
        List<Object> parameters = new ArrayList<>();
        parameters.add(tag);
        parameters.add(offset);
        parameters.add(recordPerPage);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LOTS_BY_TAG)) {
            ResultSet set = setStatement(statement, parameters).executeQuery();
            result = resultSetToListOfLots(set);

            for (Lot item : result) {
                System.out.println(item);
            }
            return result;
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to get" + tag + "lots from database!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public List<Lot> getAllUserLots(int offset, int recordsPerPage, int userID) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(userID);
        parameters.add(offset);
        parameters.add(recordsPerPage);
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_LOTS_BY_USER))
        {
            ResultSet set = setStatement(statement,parameters).executeQuery();
            return resultSetToListOfLots(set);
        } catch (SQLException throwables) {
            throw new DAOExeption();
        }
    }

    @Override
    public List<Lot> getAllUserBookmarkLots(int offset, int recordsPerPage, int userID) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(offset);
        parameters.add(recordsPerPage);
        String query = SELECT_ALL_USER_BOOLMARKS;
        for (String id: getIDOfUserBookmarkLots(userID).split(";"))
        {
            query = query + "  id = " + id + " OR";
        }
        query = query.substring(0,query.length()-2) + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            ResultSet set = setStatement(statement,parameters).executeQuery();
            return resultSetToListOfLots(set);
        } catch (SQLException throwables) {
            throw new DAOExeption();
        }
    }

    @Override
    public Lot getLotByID(int id) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LOT_BY_ID))
        {
            List<Lot> result  = resultSetToListOfLots(setStatement(statement,parameters).executeQuery());
            return result.get(0);
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to select lot!", throwables);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public int getNumberOfUserLots(int userID) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(userID);
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_USER_RECORDS))
        {

            ResultSet set = setStatement(statement, parameters).executeQuery();
            set.next();
            return set.getInt("column");
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to get number of user lots!", throwables);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public String getIDOfUserBookmarkLots(int userID) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(userID);
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_USER_BOOKMARKS))
        {

            ResultSet set = setStatement(statement, parameters).executeQuery();
            set.next();
            String lotsID = set.getString("marked_lots_id");
            return  lotsID;


        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to get number of user lots!", throwables);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public int getNumberOfActiveLotsByTag(String tag) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(tag);
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_TAG_RECORDS))
        {

            ResultSet set = setStatement(statement, parameters).executeQuery();
            set.next();
            return set.getInt("column");
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to get number of active lot!", throwables);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public int getNumberOfActiveLots() throws DAOExeption {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_RECORDS))
        {
            ResultSet set = setStatement(statement, null).executeQuery();
            set.next();
            return set.getInt("column");
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to get number of active lot!", throwables);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }


    @Override
    public boolean changeLotPriceById(int id, int price) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(price);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_PRICE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to change price !", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public boolean changeLotTitleById(int id, String title) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(title);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_TITLE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to change title !", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }


    }

    @Override
    public boolean changeLotStatusById(int id, String status) throws DAOExeption {
        List<Object> parameters = new ArrayList<>();
        parameters.add(status);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throw  new DAOExeption("Failed to change status!", throwables);
        } finally {
            connectionPool.releaseConnection(connection);
        }


    }

    private PreparedStatement setStatement(PreparedStatement statement, List<Object> parameters) throws SQLException {
        if (parameters != null) {
            int i = 1;
            for (Object param : parameters) {
                switch (param.getClass().getSimpleName()) {
                    case "String":
                        statement.setString(i, (String) param);
                        break;
                    case "Integer":
                        statement.setInt(i, (Integer) param);
                        break;
                    case "Timestamp":
                        statement.setTimestamp(i, (Timestamp) param);
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
                    .setImage(set.getString(IMAGE.toLowerCase(Locale.ROOT)))
                    .setUserOwnerID(set.getInt(OWNER_ID))
                    .setDescription(set.getString(DESCRIPTION))
                    .create();
            result.add(item);

        }
        return result;
    }

}
