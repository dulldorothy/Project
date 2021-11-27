package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LotsService;
import service.exeption.ServiceException;
import service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteLotCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router;

        LotsService service = new LotServiceImpl();

        try {
            if(service.deleteLotByID(Integer.parseInt(req.getParameter("lotID"))))
            {
                router = new Router("/Controller?page=index&command=go_to_page", Router.RouteType.REDIRECT);
            }else {
                req.setAttribute("errormessage", "Elimination error" );
                router = new Router("/Controller?lot_id=" + req.getParameter("lotID") +"&command=go_to_lot_page", Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Failed to execute DeleteLot Command", e);
            throw new CommandException(e);
        }

        return router;

    }
}
