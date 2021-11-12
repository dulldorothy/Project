package service.exeption;

import javafx.scene.control.cell.TextFieldListCell;

import java.lang.reflect.Executable;

public class ServiceExeption extends Exception{
    public ServiceExeption(String message) {
        super(message);
    }
}
