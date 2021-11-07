package controller.command.impl;

import controller.command.Command;
import controller.command.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorPage implements Command {

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Router router;
        String uri = "error";
        uri= uri + ".jsp";
        router = new Router(uri, Router.RouteType.FORWARD);
        return router;
    }
}
