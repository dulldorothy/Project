package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import domain.entity.Lot;
import service.LotsService;
import service.exeption.ServiceExeption;
import service.impl.LotServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToFullCatalog implements Command {

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        List<Lot> resultLots;
        LotsService service = new LotServiceImpl();
        int currentPage = Integer.parseInt(req.getParameter("currentPage")) - 1;
        int numberOfPages;
        try {
            numberOfPages = service.getNumberOfPages(10);
            resultLots = service.getAllActiveLots(currentPage * 10, 10);
        } catch (ServiceExeption e) {
            throw new CommandException("Get All active lots command failed",e );
        }
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("Lots", resultLots);
        Router router = new Router("searchresult.jsp", Router.RouteType.FORWARD);
        return router;
    }
}
