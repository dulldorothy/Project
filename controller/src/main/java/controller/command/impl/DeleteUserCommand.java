package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import domain.entity.UserDTO;
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


public class DeleteUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        Router router = new Router("/Controller?page=userpage&command=go_to_page", Router.RouteType.FORWARD);
        UserDTO user = (UserDTO) session.getAttribute("user");
        UserService service = new UserServiceImpl();
        int deleteID = Integer.parseInt(req.getParameter("deleteID"));
        if (user.getRole().equals("admin")) {
            try {
                service.deleteUserByID(deleteID);
                return new Router("/Controller?page=usermanager&command=go_to_page", Router.RouteType.REDIRECT);
            } catch (ServiceException e) {


                throw new CommandException("failed to delete user");
            }
        } else {
            if (user.getId() == deleteID) {
                try {
                    service.deleteUserByID(deleteID);
                    session.invalidate();
                    return new Router("/Controller?page=index&command=go_to_page", Router.RouteType.REDIRECT);
                } catch (ServiceException e) {
                    logger.error("Failed to execute DeleteUser Command", e);
                    throw new CommandException("failed to delete user");
                }
            }
        }
        return router;


    }
}
