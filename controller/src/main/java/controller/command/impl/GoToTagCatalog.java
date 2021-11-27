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
import java.util.List;

public class GoToTagCatalog implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        String tag = req.getParameter("tag");
        List<Lot> resultLots;
        LotsService service = new LotServiceImpl();
        int currentPage = Integer.parseInt(req.getParameter("currentPage")) - 1;
        int numberOfPages;
        try {
            numberOfPages = service.getNumberOfTagPages(10,tag);
            resultLots = service.getTagActiveLots(currentPage * 10, 10,tag);
        } catch (ServiceException e) {
            logger.error("Failed to execute FullCatalog  Command", e);
            throw new CommandException("Get All active lots command failed",e );
        }
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("Lots", resultLots);
        Router router = new Router("searchresult.jsp", Router.RouteType.FORWARD);
        return router;
    }
}
