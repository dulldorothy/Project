package controller.command;

import javax.servlet.http.HttpServletRequest;

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
                command =Commands.valueOf(commandName).getCommand();
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
