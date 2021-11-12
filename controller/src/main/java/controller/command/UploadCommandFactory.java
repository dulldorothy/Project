package controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class UploadCommandFactory {
    private static UploadCommandFactory instance = new UploadCommandFactory();

    private UploadCommandFactory() {

    }

    public static UploadCommandFactory getInstance() {
        return instance;
    }


    public UploadCommand createCommand(HttpServletRequest req) {
        String commandName = req.getParameter("command");
        UploadCommand command;

        if (commandName == null) {
            return UploadCommands.DEFAULT.getCommand();
        } else {
            try{
                UploadCommands commands = UploadCommands.valueOf(commandName.toUpperCase(Locale.ROOT));
                command = commands.getCommand();
            }catch (IllegalArgumentException e)
            {
                command = UploadCommands.DEFAULT.getCommand();
            }
        }
        return command;

    }


}
