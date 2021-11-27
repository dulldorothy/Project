package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.exeptions.CommandException;
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

public class AddLotToBookmarkCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        int lotID = Integer.parseInt(req.getParameter("lot_id"));
        LotsService service =new LotServiceImpl();
        try {
            service.addLotToUserBookmarks(user.getId(), lotID);
            return new Router("Controller?page=userpage&command=go_to_page", Router.RouteType.REDIRECT);
        } catch (ServiceException serviceException) {
            logger.error("Failed to execute AddLotToBookmark Command", serviceException);
            throw new CommandException();
        }
    }
}
