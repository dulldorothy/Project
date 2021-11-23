package controller.command.impl;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources_zh_TW;
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

public class GoToLotPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {

        LotsService service = new LotServiceImpl();
        Lot lot = null;
        try {
           lot = service.getLotByID(Integer.parseInt(req.getParameter("lot_id")));
        } catch (ServiceExeption e) {
            throw new CommandException("failed to get lot", e);
        }
        req.setAttribute("Lot", lot);
        System.out.println(lot);

        return new Router("lotpage.jsp", Router.RouteType.FORWARD);
    }
}
