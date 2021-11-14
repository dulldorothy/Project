package dao.database.impl;

import dao.database.ConnectionPool;

import dao.database.LotsDAO;
import dao.database.exeptions.DAOExeption;
import domain.entity.Lot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LotsDAOImpl implements LotsDAO {

    private static final String SAVE_LOT = "INSERT INTO lots (user_owner_id, title, price, lot_type,is_active_status, " +
            "time_of_expiration) " +
            "VALUES(?,?,?,?,?);";//TODO rewrite SQL query

    private static final String DELETE_LOT_BY_ID = "DELETE FROM lots WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM lots OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";
    private static final String SELECT_ACTIVE_LOTS = "SELECT * FROM lots WHERE is_active_status = 'active' OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    private static final String CHANGE_PRICE_BY_ID = "UPDATE lots SET price = ? WHERE id = ?;";
    private static final String CHANGE_TITLE_BY_ID = "UPDATE lots SET title = ? WHERE id = ?;";
    private static final String CHANGE_STATUS_BY_ID = "UPDATE lots SET is_active_status = ? WHERE id = ?;";
    private static final String SELECT_LOTS_BY_TAG = "SELECT * FROM lots WHERE tagList LIKE ? OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ;";
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
        parameters.add("%," + tag + ",%");
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
                    .setId(set.getInt("id"))
                    .setPrice(set.getInt("price"))
                    .setTagList(set.getString("tag_list"))
                    .setStatus(set.getString("is_active_status"))
                    .setTitle(set.getString("title"))
                    .setTimeOfExpiration(set.getTimestamp("time_of_expiration"))
                    .setType(set.getString("lot_type"))
                    .setUserOwnerID(set.getInt("user_owner_id"))
                    .create();
            result.add(item);

        }
        return result;
    }

}
