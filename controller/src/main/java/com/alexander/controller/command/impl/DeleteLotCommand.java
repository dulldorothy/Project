package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.service.LotsService;
import com.alexander.service.ServiceFactory;
import com.alexander.service.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.ERROR_MESSAGE;
import static com.alexander.domain.fields.UserFields.LOT_ID;

public class DeleteLotCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final LotsService lotsService = ServiceFactory.getInstance().getLotsService();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router;


        try {
            if (lotsService.deleteLotByID(Integer.parseInt(req.getParameter(LOT_ID)))) {
                router = new Router("/Controller?page=index&command=go_to_page", Router.RouteType.REDIRECT);
            } else {
                req.setAttribute(ERROR_MESSAGE, "Elimination error");
                router = new Router("/Controller?lot_id=" + req.getParameter(LOT_ID) + "&command=go_to_lot_page", Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to execute DeleteLot Command", e);
            throw new CommandException(e);
        }

        return router;

    }
}
