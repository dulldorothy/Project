package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.service.LotsService;
import com.alexander.service.ServiceFactory;
import com.alexander.service.UserService;
import com.alexander.service.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.*;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {

        Router router;
        HttpSession session = req.getSession();
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        try {
            if (userService.checkUserIfExistsByLoginAndPass(username, password)) {
                session.setAttribute(SESSION_USER_ATR, ServiceFactory.getInstance().getUserService().getUserByLoginAndPassword(username, password));
                router = new Router("/Controller?page=index&command=go_to_page", Router.RouteType.FORWARD);
            } else {
                req.setAttribute("message", "Invalid login or password!");
                router = new Router("/Controller?page=login&command=go_to_page", Router.RouteType.REDIRECT);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to execute LOGIN  Command", e);
            throw new CommandException(e);
        }
        return router;


    }
}
