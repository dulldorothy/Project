package com.alexander.controller.command;

import com.alexander.controller.command.impl.*;

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
    DEFAULT(new GoToMainPageCommand()),
    ERROR_PAGE(new ErrorPage()),
    CREATE_LOT(new CreateLotCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    CHANGE_USER_LASTNAME(new ChangeUserLastnameCommand()),
    CHANGE_USER_FIRSTNAME(new ChangeUserFirstnameCommand()),
    CHANGE_USER_PASSWORD(new ChangeUserPasswordCommand()),
    SEND_MESSAGE_TO_OWNER (new SendMessageToOwnerCommand()),
    GO_TO_UNREAD_MESSAGES(new GoToUnreadMessagesCommand()),
    GO_TO_ALL_MESSAGES(new GoToAllMessagesCommand()),
    CHANGE_USER_IMAGE(new ChangeUserImageCommand()),
    CHANGE_LOT_DESCRIPTION(new ChangeLotDescriptionCommand()),
    CHANGE_LOT_STATUS(new ChangeLotStatusCommand()),
    CHANGE_LOT_TITLE(new ChangeLotTitleCommand()),
    CHANGE_LOT_PRICE(new ChangeLotPriceCommand()),
    CHANGE_LOT_IMAGE(new ChangeLotImageCommand()),
    DELETE_LOT(new DeleteLotCommand()),
    CHANGE_MESSAGE_READ_STATUS(new ChangeMessageReadStatusCommand());


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
