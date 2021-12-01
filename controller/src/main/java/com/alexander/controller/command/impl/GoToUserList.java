package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.domain.entity.Page;
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

public class GoToUserList implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        int currentPage = Integer.parseInt(req.getParameter(CURRENT_PAGE)) - 1;
        Page<UserDTO> page;
        try {

            page = ServiceFactory.getInstance().getUserService().getAllUsers(currentPage * 10, 10);
        } catch (ServiceException e) {
            LOGGER.error("Failed to execute User List  Command", e);
            throw new CommandException(e);
        }
        req.setAttribute(NUMBER_OF_PAGES, page.getNumberOfPages());
        req.setAttribute(USER_LIST, page.getListOfItems());
        Router router = new Router(PAGE_PREV_PATH +"userlist.jsp", Router.RouteType.FORWARD);
        return router;
    }
}
