package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.PAGE;
import static com.alexander.domain.fields.UserFields.PAGE_PREV_PATH;

public class GoToPageCommandImpl implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Router router;
        String uri = req.getParameter(PAGE);
        uri= PAGE_PREV_PATH + uri + ".jsp";
        router = new Router(uri, Router.RouteType.FORWARD);
        return router;
    }
}
