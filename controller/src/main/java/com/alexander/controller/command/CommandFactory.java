package com.alexander.controller.command;

import com.alexander.controller.exeptions.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static com.alexander.domain.fields.UserFields.*;

public class CommandFactory {


    private static CommandFactory commandFactory = new CommandFactory() ;



    public static CommandFactory getInstance() {
        return commandFactory;
    }

    private CommandFactory(){

    }

    public Command createCommand(HttpServletRequest request) throws CommandException {
        String commandName = request.getParameter(COMMAND);
        Command command = CommandEnum.ERROR_PAGE.getCommand();
        if (commandName != null && !commandName.equals(EMPTY_STRING))
        {
            try{
                command = CommandEnum.valueOf(commandName.toUpperCase(Locale.ROOT)).getCommand();
            }catch (IllegalArgumentException e )
            {
             throw new CommandException(e);
            }
        }
        return command;
    }

}
