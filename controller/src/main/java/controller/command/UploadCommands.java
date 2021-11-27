package controller.command;

import controller.command.impl.CreateLotCommand;
import controller.command.impl.GoToMainPageCommand;
import controller.command.impl.RegisterCommandImpl;

public enum UploadCommands {
    CREATE_LOT(new CreateLotCommand()),
    DEFAULT(null);
    private UploadCommand command;

    UploadCommands (UploadCommand command)
    {
        this.command = command;
    }

    public UploadCommand getCommand(){
        return command;
    }

}
