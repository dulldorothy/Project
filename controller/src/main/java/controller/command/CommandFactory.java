package controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class CommandFactory {


    private CommandFactory commandFactory;

    CommandFactory getInstance() {
        return commandFactory;
    }



    public Command createCommand(HttpServletRequest request)
    {
        String commandName =request.getParameter("command");
        Command command;
        if (commandName != null)
        {
            try{
                command =Commands.valueOf(commandName.toUpperCase(Locale.ROOT)).getCommand();
            }catch (IllegalArgumentException e )
            {
                command = Commands.ERROR_PAGE.getCommand();
            }
        }else {
            command = Commands.ERROR_PAGE.getCommand();
        }
        return command;
    }

}
