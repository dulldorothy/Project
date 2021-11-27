package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import domain.entity.Lot;
import domain.entity.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LotsService;
import service.exeption.ServiceException;
import service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToUserBookmarks implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<Lot> resultLots;
        LotsService service = new LotServiceImpl();
        int currentPage = Integer.parseInt(req.getParameter("currentPage")) - 1;
        int numberOfPages;
        try {
            numberOfPages = service.getNumberOfUserBookmarkLots(10, user.getId());
            resultLots = service.getUserBookmarkLots(currentPage * 10, 10, user.getId());
        } catch (ServiceException e) {
            logger.error("Failed to execute UserBookmarks  Command", e);
            throw new CommandException(e);
        }
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("Lots", resultLots);
        Router router = new Router("searchresult.jsp", Router.RouteType.FORWARD);
        return router;
    }
}
