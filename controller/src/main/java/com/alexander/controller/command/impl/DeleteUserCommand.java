package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.domain.entity.UserDTO;
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

public class DeleteUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        Router router = new Router("/Controller?page=userpage&command=go_to_page", Router.RouteType.FORWARD);
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        int deleteID = Integer.parseInt(req.getParameter(DELETE_ID));
        if (user.getRole().equals(ADMIN_ROLE)) {
            try {
                userService.deleteUserByID(deleteID);
                return new Router("/Controller?page=userlist&command=go_to_user_list&currentPage=1", Router.RouteType.REDIRECT);
            } catch (ServiceException e) {


                throw new CommandException("failed to delete user");
            }
        } else {
            if (user.getId() == deleteID) {
                try {
                    ServiceFactory.getInstance().getUserService().deleteUserByID(deleteID);
                    session.invalidate();
                    return new Router("/Controller?page=index&command=go_to_page", Router.RouteType.REDIRECT);
                } catch (ServiceException e) {
                    LOGGER.error("Failed to execute DeleteUser Command", e);
                    throw new CommandException("failed to delete user");
                }
            }
        }
        return router;


    }
}
