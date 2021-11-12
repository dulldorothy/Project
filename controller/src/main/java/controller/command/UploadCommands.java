package controller.command;

import controller.command.impl.RegisterCommandImpl;

public enum UploadCommands {

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
