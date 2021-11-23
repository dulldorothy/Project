package dao.database.exeptions;

import java.sql.SQLException;

public class DAOExeption extends Exception {
    public DAOExeption() {
    }

    public DAOExeption(String message, SQLException throwables) {
        super(message);
    }
}
