package controller.exeptions;

public class CommandNotFoundExeption extends Exception{
    public CommandNotFoundExeption(String message) {
        super("Command not Found!");
    }
}
