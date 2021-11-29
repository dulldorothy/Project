package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
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
public class AddLotToBookmarkCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        int lotID = Integer.parseInt(req.getParameter(LOT_ID));

        try {
            ServiceFactory.getInstance().getLotsService().addLotToUserBookmarks(user.getId(), lotID);
            return new Router("Controller?page=userpage&command=go_to_page", Router.RouteType.REDIRECT);
        } catch (ServiceException serviceException) {
            logger.error("Failed to execute AddLotToBookmark Command", serviceException);
            throw new CommandException();
        }
    }
}
