package service.exeption;

import dao.database.exeptions.DAOExeption;
import javafx.scene.control.cell.TextFieldListCell;

import java.lang.reflect.Executable;

public class ServiceExeption extends Exception{
    public ServiceExeption(String message, DAOExeption daoExeption) {
        super(message);
    }
}
