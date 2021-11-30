package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.domain.entity.UserDTO;
import com.alexander.service.ServiceFactory;
import com.alexander.service.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.*;

public class SendMessageToOwnerCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router = new Router("/Controller?page=error&command=error_page", Router.RouteType.REDIRECT);
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        int lotID = Integer.parseInt(req.getParameter(LOT_ID));
        int ownerID = Integer.parseInt(req.getParameter(OWNER_ID));
        try {
            ServiceFactory.getInstance().getMessageService().sendMessage(ownerID, user.getId(), lotID);
            router = new Router("/Controller?page=userpage&command=go_to_page", Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            LOGGER.error("Failed to send message to owner", e);
            throw new CommandException();
        }
        return router;
    }
}
