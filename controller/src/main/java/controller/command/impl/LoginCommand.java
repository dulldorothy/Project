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
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static domain.entity.UserFields.*;
public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

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
        } catch (ServiceException e) {
            logger.error("Failed to execute LOGIN  Command", e);
            throw new CommandException(e);
        }
        return router;



    }
}
