package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import service.UserService;
import service.exeption.ServiceExeption;
import service.impl.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class RegisterCommandImpl implements Command {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandException {
        Router router;
        UserService service = new UserServiceImpl();
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username",request.getParameter("username"));
        userMap.put("firstname",request.getParameter("firstname"));
        userMap.put("lastname",request.getParameter("lastname"));
        userMap.put("password",request.getParameter("password"));
        try {
            if(service.saveUser(userMap))
            {

                router = new Router("login.jsp", Router.RouteType.REDIRECT);
            }else {
                request.setAttribute("massage", "Invalid Fields");
                router = new Router("register.jsp", Router.RouteType.FORWARD);
            }
        } catch (ServiceExeption e) {
            throw new CommandException("Register Command failed",e);
        }

        return router;
    }
}
