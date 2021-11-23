package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import domain.entity.Lot;
import domain.entity.UserDTO;
import service.LotsService;
import service.exeption.ServiceExeption;
import service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToUserBookmarks implements Command {
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
        } catch (ServiceExeption e) {
            throw new CommandException("Get All bookmark lots command failed",e );
        }
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("Lots", resultLots);
        Router router = new Router("searchresult.jsp", Router.RouteType.FORWARD);
        return router;
    }
}
