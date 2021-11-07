package controller.command;

import controller.command.impl.*;

public enum Commands {
    LOGIN(new LoginCommandImpl()),
    REGISTER(new RegisterCommandImpl()),
    GO_TO_PAGE(new GoToPageCommandImpl()),
    LOG_OUT(new LogOutCommandImpl()),
    GO_TO_FULL_CATALOG(new GoToFullCatalog()),
    ERROR_PAGE(new ErrorPage());

    private Command command;

    Commands(Command command)
    {
        this.command = command;
    }

    Command getCommand()
    {
        return command;
    }
}
