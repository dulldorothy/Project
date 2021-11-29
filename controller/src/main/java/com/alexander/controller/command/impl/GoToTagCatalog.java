package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.Page;
import com.alexander.service.LotsService;
import com.alexander.service.ServiceFactory;
import com.alexander.service.exeption.ServiceException;
import com.alexander.service.impl.LotServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.*;

public class GoToTagCatalog implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        String tag = req.getParameter(TAG);
        Page<Lot> page;
        int currentPage = Integer.parseInt(req.getParameter(CURRENT_PAGE)) - 1;
        try {

            page = ServiceFactory.getInstance().getLotsService().getTagActiveLots(currentPage * 10, 10,tag);
        } catch (ServiceException e) {
            logger.error("Failed to execute FullCatalog  Command", e);
            throw new CommandException("Get All active lots command failed",e );
        }
        req.setAttribute(NUMBER_OF_PAGES, page.getNumberOfPages());
        req.setAttribute(LOT_LIST, page.getListOfItems());
        Router router = new Router("/jsp/searchresult.jsp", Router.RouteType.FORWARD);
        return router;
    }
}
