package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.service.ServiceFactory;
import com.alexander.service.exeption.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.MESSAGE_ID;

public class ChangeMessageReadStatusCommand implements Command {

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router = new Router("/Controller?page=error&command=error_page", Router.RouteType.REDIRECT);
        int messageID = Integer.parseInt(req.getParameter(MESSAGE_ID));
        try {
            ServiceFactory.getInstance().getMessageService().changeReadStatus(messageID);
            router = new Router("/Controller?page=messages&command=go_to_unread_messages&currentPage=1", Router.RouteType.REDIRECT);

        } catch (ServiceException e) {
            throw new CommandException();
        }
        return router;

    }
}
