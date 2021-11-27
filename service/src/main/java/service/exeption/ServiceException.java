package service.exeption;

import dao.database.exeptions.DAOExeption;
import javafx.scene.control.cell.TextFieldListCell;

import java.lang.reflect.Executable;

public class ServiceException extends Exception{
    public ServiceException(String message, DAOExeption daoExeption) {
        super(message);
    }

    public ServiceException(String message) {
        super(message);
    }
}
