package controller.command.impl;

import controller.command.Command;
import domain.entity.Lot;
import service.LotsService;
import service.impl.LotServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToFullCatalog implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lot> resultLots;
        LotsService service = new LotServiceImpl();
        resultLots = service.getAllActiveLots();
        req.setAttribute("Lots", resultLots);
        req.getRequestDispatcher("searchresult.jsp").forward(req, resp);
    }
}
