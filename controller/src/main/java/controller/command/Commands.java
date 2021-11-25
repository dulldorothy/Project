package controller.command;

import controller.command.impl.*;

public enum Commands {
    REGISTER(new RegisterCommandImpl()),
    LOGIN(new LoginCommand()),
    GO_TO_PAGE(new GoToPageCommandImpl()),
    LOGOUT(new LogOutCommandImpl()),
    GO_TO_FULL_CATALOG(new GoToFullCatalog()),
    GO_TO_LOT_PAGE(new GoToLotPageCommand()),
    GO_TO_TAG_CATALOG(new GoToTagCatalog()),
    GO_TO_USER_LOTS(new GoToUserLots()),
    ADD_TO_BOOKMARK(new AddLotToBookmarkCommand()),
    GO_TO_USER_BOOKMARKS(new GoToUserBookmarks()),

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
