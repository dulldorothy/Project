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

import static com.alexander.domain.fields.UserFields.LASTNAME;
import static com.alexander.domain.fields.UserFields.SESSION_USER_ATR;

public class ChangeUserLastnameCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router = new Router("/Controller?page=error&command=error_page", Router.RouteType.REDIRECT);
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        String lastname = req.getParameter(LASTNAME);
        try {
            ServiceFactory.getInstance().getUserService().changeUserLastName(user, lastname);
            user.setLastName(lastname);
            session.setAttribute(SESSION_USER_ATR, user);
            router = new Router("/Controller?page=userpage&command=go_to_page", Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            LOGGER.error("Failed to chage user lastname", e);
            throw new CommandException();
        }
        return router;
    }
}
