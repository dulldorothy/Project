package controller.filter;

import controller.command.Commands;
import controller.command.UploadCommand;
import controller.command.UploadCommands;

import java.util.EnumSet;
import java.util.Locale;

import static controller.command.UploadCommands.*;
import static controller.command.Commands.*;
class RoleCommands {
    private static RoleCommands instance;

    private final EnumSet<UploadCommands> guestUploadCommands = EnumSet.of(
            UploadCommands.DEFAULT
    );

    private final EnumSet<UploadCommands> userUploadCommands = EnumSet.of(
            UploadCommands.DEFAULT,
            CREATE_LOT
    );

    private final EnumSet<UploadCommands> adminUploadCommands = EnumSet.of(

            UploadCommands.DEFAULT,
            CREATE_LOT


    );


    private final EnumSet<Commands> guestCommands = EnumSet.of(
            Commands.DEFAULT,
            LOGIN,
            CHANGE_LOCALE,
            GO_TO_PAGE,
            GO_TO_LOT_PAGE,
            GO_TO_TAG_CATALOG,
            GO_TO_FULL_CATALOG,
            REGISTER
    );

    private final EnumSet<Commands> userCommands = EnumSet.of(
            Commands.DEFAULT,
            LOGIN,
            CHANGE_LOCALE,
            LOGOUT,
            GO_TO_PAGE,
            GO_TO_FULL_CATALOG,
            GO_TO_LOT_PAGE,
            GO_TO_USER_LOTS,
            ADD_TO_BOOKMARK,
            GO_TO_TAG_CATALOG,
            GO_TO_USER_BOOKMARKS,
            ERROR_PAGE
    );

    private final EnumSet<Commands> adminCommands = EnumSet.of(
            Commands.DEFAULT,
            LOGIN,
            CHANGE_LOCALE,
            LOGOUT,
            GO_TO_PAGE,
            GO_TO_FULL_CATALOG,
            GO_TO_LOT_PAGE,
            GO_TO_USER_LOTS,
            ADD_TO_BOOKMARK,
            GO_TO_TAG_CATALOG,
            GO_TO_USER_BOOKMARKS,
            ERROR_PAGE
    );

    private RoleCommands() {
    }

    public static RoleCommands getInstance() {
        if (instance == null) {
            instance = new RoleCommands();
        }
        return instance;
    }

    public boolean checkCommand(String role, Commands command) {
        switch (role.toUpperCase(Locale.ROOT)) {
            case "GUEST": return guestCommands.contains(command);
            case "USER": return userCommands.contains(command);
            case "ADMIN": return adminCommands.contains(command);
        }
        return false;
    }


    public boolean checkUploadCommand(String role, UploadCommands uploadCommand) {
        switch (role) {
            case "GUEST": return guestUploadCommands.contains(uploadCommand);
            case"USER": return userUploadCommands.contains(uploadCommand);
            case "ADMIN": return adminUploadCommands.contains(uploadCommand);
        }
        return false;
    }


}

