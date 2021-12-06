package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.service.ServiceFactory;
import com.alexander.service.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.DESCRIPTION;
import static com.alexander.domain.fields.UserFields.LOT_ID;

public class ChangeLotDescriptionCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router = new Router("/Controller?page=error&command=error_page", Router.RouteType.REDIRECT);
        HttpSession session = req.getSession();
        String description = req.getParameter(DESCRIPTION);
        int lotID = Integer.parseInt(req.getParameter(LOT_ID));
        try {
            ServiceFactory.getInstance().getLotsService().changeLotDescriptionByID(lotID, description);
            router = new Router("/Controller?lot_id=" + lotID + "&command=go_to_lot_page", Router.RouteType.REDIRECT);

        } catch (ServiceException e) {
            LOGGER.error("Failed to change lot description", e);
            throw new CommandException();
        }
        return router;
    }
}
