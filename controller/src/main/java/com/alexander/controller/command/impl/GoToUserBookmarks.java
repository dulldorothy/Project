package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.Page;
import com.alexander.domain.entity.UserDTO;
import com.alexander.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.alexander.service.LotsService;
import com.alexander.service.exeption.ServiceException;
import com.alexander.service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.*;

public class GoToUserBookmarks implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        Page<Lot> page;
        int currentPage = Integer.parseInt(req.getParameter(CURRENT_PAGE)) - 1;
        try {

            page = ServiceFactory.getInstance().getLotsService().getUserBookmarkLots(currentPage * 10, 10, user.getId());
        } catch (ServiceException e) {
            logger.error("Failed to execute UserBookmarks  Command", e);
            throw new CommandException(e);
        }

            req.setAttribute(NUMBER_OF_PAGES, page.getNumberOfPages());
            req.setAttribute(LOT_LIST, page.getListOfItems());

        Router router = new Router("/jsp/searchresult.jsp", Router.RouteType.FORWARD);
        return router;
    }
}