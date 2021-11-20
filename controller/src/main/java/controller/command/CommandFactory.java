package controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static domain.entity.UserFields.*;

public class CommandFactory {


    private static CommandFactory commandFactory = new CommandFactory() ;



    public static CommandFactory getInstance() {
        return commandFactory;
    }



    public Command createCommand(HttpServletRequest request)
    {
        String commandName = request.getParameter(COMMAND);
        Command command;
        if (commandName != null)
        {
            try{
                command =   Commands.valueOf(commandName.toUpperCase(Locale.ROOT)).getCommand();
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
