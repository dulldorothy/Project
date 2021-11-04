package controller;

import controller.command.Command;
import controller.command.impl.GoToPageCommandImpl;
import controller.command.impl.LogOutCommandImpl;
import controller.command.impl.LoginCommandImpl;
import controller.command.impl.RegisterCommandImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontController extends HttpServlet {

    Map<String, Command> comMap =new HashMap<>();

    @Override
    public void init() throws ServletException {
        comMap.put("login", new LoginCommandImpl());
        comMap.put("register", new RegisterCommandImpl());
        comMap.put("logout", new LogOutCommandImpl());
        comMap.put("goToPage", new GoToPageCommandImpl());
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
        Command command;

        try{

            command = comMap.get(req.getParameter("command"));
            command.execute(req, resp);
        }catch (Exception e)
        {
            e.getStackTrace();
        }

        //TODO body code

        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
        System.out.println(req.getParameter("command"));





    }


}
