package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.exeption.ServiceException;
import service.impl.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class RegisterCommandImpl implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandException {
        Router router;
        UserService service = new UserServiceImpl();
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", request.getParameter("username"));
        userMap.put("firstname", request.getParameter("firstname"));
        userMap.put("lastname", request.getParameter("lastname"));
        userMap.put("password", request.getParameter("password"));
        try {
            if (service.saveUser(userMap)) {

                router = new Router("/Controller?page=login&command=go_to_page", Router.RouteType.REDIRECT);
            } else {
                request.setAttribute("massage", "Invalid Fields");
                router = new Router("register.jsp", Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Failed to execute Register  Command", e);
            throw new CommandException(e);
        }

        return router;
    }
}
