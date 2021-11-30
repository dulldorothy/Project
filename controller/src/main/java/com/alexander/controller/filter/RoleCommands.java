package com.alexander.controller.filter;

import com.alexander.controller.command.Commands;

import java.util.EnumSet;

import static com.alexander.domain.fields.UserFields.*;
import static com.alexander.controller.command.Commands.*;
class RoleCommands {
    private static RoleCommands instance;


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
            ERROR_PAGE,
            CREATE_LOT,
            CHANGE_USER_FIRSTNAME,
            CHANGE_USER_LASTNAME,
            CHANGE_USER_PASSWORD,
            CHANGE_USER_IMAGE,
            SEND_MESSAGE_TO_OWNER,
            GO_TO_UNREAD_MESSAGES,
            GO_TO_ALL_MESSAGES,
            CHANGE_MESSAGE_READ_STATUS,
            CHANGE_LOT_DESCRIPTION,
            CHANGE_LOT_IMAGE,
            CHANGE_LOT_PRICE,
            CHANGE_LOT_STATUS,
            CHANGE_LOT_TITLE,
            DELETE_LOT,
            DELETE_USER
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
            ERROR_PAGE,
            CREATE_LOT,
            CHANGE_USER_FIRSTNAME,
            CHANGE_USER_LASTNAME,
            CHANGE_USER_PASSWORD,
            CHANGE_USER_IMAGE,
            SEND_MESSAGE_TO_OWNER,
            GO_TO_UNREAD_MESSAGES,
            GO_TO_ALL_MESSAGES,
            CHANGE_MESSAGE_READ_STATUS,
            CHANGE_LOT_DESCRIPTION,
            CHANGE_LOT_IMAGE,
            CHANGE_LOT_PRICE,
            CHANGE_LOT_STATUS,
            CHANGE_LOT_TITLE,
            DELETE_LOT,
            GO_TO_USER_LIST,
            DELETE_USER
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
        switch (role) {
            case GUEST_ROLE: return guestCommands.contains(command);
            case USER_ROLE: return userCommands.contains(command);
            case ADMIN_ROLE: return adminCommands.contains(command);
        }
        return false;
    }




}

