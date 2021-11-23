package controller.command.impl;

import controller.command.Command;
import controller.command.Router;
import controller.command.UploadCommand;
import service.exeption.ServiceExeption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class LotCreationCommand implements UploadCommand {


    @Override
    public Router execute(HttpServletRequest request, InputStream inputStream) throws IOException, ServletException, ServiceExeption {
        System.out.println("XD");
         return new Router("main", Router.RouteType.REDIRECT);

    }
}
