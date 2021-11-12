package controller.command;

import controller.command.impl.*;

public enum Commands {
    REGISTER(new RegisterCommandImpl()),
    LOGIN(new LoginCommand()),
    GO_TO_PAGE(new GoToPageCommandImpl()),
    LOGOUT(new LogOutCommandImpl()),
    GO_TO_FULL_CATALOG(new GoToFullCatalog()),
    CREATE_LOT(new LotCreationCommand()),
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
