package controller.command.impl;

import controller.command.Command;
import controller.command.Router;


import controller.exeptions.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.exeption.ServiceExeption;
import service.impl.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static domain.entity.UserFields.*;
public class LoginCommand implements Command {



    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {

        Router router;
        HttpSession session = req.getSession();
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        UserService service = new UserServiceImpl();
        try {
            if (service.checkUserIfExistsByLoginAndPass(username,password ))
            {

                session.setAttribute(SESSION_USER_ATR, service.getUserByLoginAndPassword(username,password));
                router = new Router("index.jsp", Router.RouteType.FORWARD);
            }else {
                req.setAttribute("message", "Invalid login or password!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                router = new Router("login.jsp", Router.RouteType.REDIRECT);
            }
        } catch (ServiceExeption e) {
            throw new CommandException("Login command failed", e);
        }
        return router;



    }
}
