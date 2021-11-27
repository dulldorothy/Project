package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import domain.entity.Lot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LotsService;
import service.exeption.ServiceException;
import service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToLotPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {

        LotsService service = new LotServiceImpl();
        Lot lot;
        try {
           lot = service.getLotByID(Integer.parseInt(req.getParameter("lot_id")));
        } catch (ServiceException e) {
            logger.error("Failed to execute LotPage  Command", e);
            throw new CommandException(e);
        }
        req.setAttribute("Lot", lot);
        System.out.println(lot);

        return new Router("lotpage.jsp", Router.RouteType.FORWARD);
    }
}
