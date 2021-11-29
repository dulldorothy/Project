package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.Page;
import com.alexander.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.alexander.service.LotsService;
import com.alexander.service.exeption.ServiceException;
import com.alexander.service.impl.LotServiceImpl;
import sun.security.krb5.internal.PAEncTSEnc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.*;

public class GoToLotPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {


        Page<Lot> page;
        try {
           page = ServiceFactory.getInstance().getLotsService().getLotByID(Integer.parseInt(req.getParameter(LOT_ID)));
        } catch (ServiceException e) {
            logger.error("Failed to execute LotPage  Command", e);
            throw new CommandException(e);
        }
        req.setAttribute(LOT, page.getLot());
        req.setAttribute(USER_OWNER, page.getUserOwner());
        return new Router("/jsp/lotpage.jsp", Router.RouteType.FORWARD);
    }
}
