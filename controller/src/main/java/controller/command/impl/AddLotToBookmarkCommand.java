package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
import domain.entity.User;
import domain.entity.UserDTO;
import service.LotsService;
import service.exeption.ServiceExeption;
import service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddLotToBookmarkCommand implements Command {

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        int lotID = Integer.parseInt(req.getParameter("lot_id"));
        LotsService service =new LotServiceImpl();
        try {
            service.addLotToUserBookmarks(user.getId(), lotID);
            return new Router("userpage.jsp", Router.RouteType.REDIRECT);
        } catch (ServiceExeption serviceExeption) {
            throw new CommandException("failed to add lot to user bookmarks" ,serviceExeption);
        }
    }
}
