package controller;

import controller.command.Command;
import controller.command.CommandFactory;
import controller.command.Router;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FrontController extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doExecute(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doExecute(req, resp);
    }


    private void doExecute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Router router;

        CommandFactory commandFactory = new CommandFactory();

        Command command = commandFactory.createCommand(req);
        router = command.execute(req, resp);

            switch (router.getRouteType()) {
            case FORWARD: {
                req.getRequestDispatcher(router.getPagePath()).forward(req, resp);
                break;
            }
            case REDIRECT: {
                resp.sendRedirect(req.getContextPath() + router.getPagePath());
                break;
            }
            default:{
                resp.sendRedirect(req.getContextPath() + "error.jsp");
            }
        }

        //TODO body code



    }


}
