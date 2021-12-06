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

import static com.alexander.domain.fields.UserFields.LOT_ID;
import static com.alexander.domain.fields.UserFields.PRICE;

public class ChangeLotPriceCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router = new Router("/Controller?page=error&command=error_page", Router.RouteType.REDIRECT);
        HttpSession session = req.getSession();
        int lotID = Integer.parseInt(req.getParameter(LOT_ID));
        int price = Integer.parseInt(req.getParameter(PRICE));
        try {
            ServiceFactory.getInstance().getLotsService().changeLotPriceByID(lotID, price);
            router = new Router("/Controller?lot_id=" + lotID + "&command=go_to_lot_page", Router.RouteType.REDIRECT);

        } catch (ServiceException e) {
            LOGGER.error("Failed to change lot price");
            throw new CommandException();
        }
        return router;
    }
}
