package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.service.ServiceFactory;
import com.alexander.service.UserService;
import com.alexander.service.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.alexander.domain.fields.UserFields.*;

public class RegisterCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandException {
        Router router;

        Map<String, String> userMap = new HashMap<>();
        userMap.put(USERNAME, request.getParameter(USERNAME));
        userMap.put(FIRSTNAME, request.getParameter(FIRSTNAME));
        userMap.put(LASTNAME, request.getParameter(LASTNAME));
        userMap.put(PASSWORD, request.getParameter(PASSWORD));
        userMap.put(EMAIL, request.getParameter(EMAIL));
        try {
            if (userService.saveUser(userMap)) {

                router = new Router("/Controller?page=login&command=go_to_page", Router.RouteType.REDIRECT);
            } else {
                request.setAttribute("massage", "Invalid Fields");
                router = new Router(PAGE_PREV_PATH + "register.jsp", Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to execute Register  Command", e);
            throw new CommandException(e);
        }

        return router;
    }
}
