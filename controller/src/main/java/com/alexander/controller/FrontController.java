package com.alexander.controller;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.CommandFactory;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)

public class FrontController extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doExecute(req, resp);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doExecute(req, resp);
        } catch (CommandException e) {
                e.printStackTrace();
        }
    }


    private void doExecute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
            Router router;
            Command command = CommandFactory.getInstance().createCommand(req);
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
                default: {
                    resp.sendRedirect(req.getContextPath() + "error.jsp");
                }
            }
    }


}
