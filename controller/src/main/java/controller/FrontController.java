package controller;

import controller.command.*;
import controller.exeptions.CommandException;
import service.exeption.ServiceExeption;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


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
        try {
            Router router;
            try {
                InputStream image = null;
                Part part = req.getPart("image");
                if (part.getSize() != 0) {
                    image = part.getInputStream();
                }
                UploadCommand uploadCommand = UploadCommandFactory.getInstance().createCommand(req);
                router = uploadCommand.execute(req, image);
            } catch (ServletException | ServiceExeption e) {
                Command command = CommandFactory.getInstance().createCommand(req);
                router = command.execute(req, resp);
            }
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
        }finally {
            //todo
        }
//        } catch (ControllerException e) {
//            req.setAttribute("errormessage", e);
//            req.getRequestDispatcher("error.jsp").forward(req, resp);
//
//        }


    }


}
