package dao.database.impl;

import dao.database.ConnectionPool;

import dao.database.LotsDAO;
import domain.entity.Lot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LotsDAOImpl implements LotsDAO {

    private static final String SAVE_LOT = "INSERT INTO lots (user_owner_id, title, price, lot_type,is_active_status, " +
            "time_of_expiration) " +
            "VALUES(?,?,?,?,?);";

    private static final String DELETE_LOT_BY_ID = "DELETE FROM lots WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM lots";
    private static final String SELECT_ACTIVE_LOTS = "SELECT * FROM lots WHERE is_active_status = 'active';";
    private static final String CHANGE_PRICE_BY_ID = "UPDATE lots SET price = ? WHERE id = ?;";
    private static final String CHANGE_TITLE_BY_ID = "UPDATE lots SET title = ? WHERE id = ?;";
    private static final String CHANGE_STATUS_BY_ID = "UPDATE lots SET is_active_status = ? WHERE id = ?;";
    ConnectionPool connectionPool;

    public LotsDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean saveLot(Lot item) {
        List<Object> parameters = item.LotToListOfParameters();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SAVE_LOT)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean deleteLotById(int id) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_LOT_BY_ID)) {
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
    public List<Lot> getAll() {

        List<Lot> result;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet set = setStatement(statement, null).executeQuery();
            result = resultSetToListOfLots(set);

            for (Lot item : result) {
                System.out.println(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }


    @Override
    public List<Lot> getActiveLots() {
        List<Lot> result;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ACTIVE_LOTS)) {
            ResultSet set = setStatement(statement, null).executeQuery();
            result = resultSetToListOfLots(set);

            for (Lot item : result) {
                System.out.println(item);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }


    @Override
    public boolean changeLotPriceById(int id, int price) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(price);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_PRICE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean changeLotTitleById(int id, String title) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(title);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_TITLE_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;

    }

    @Override
    public boolean changeLotStatusById(int id, String status) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(status);
        parameters.add(id);
        Connection connection = connectionPool.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS_BY_ID)) {
            return setStatement(statement, parameters).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;

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
