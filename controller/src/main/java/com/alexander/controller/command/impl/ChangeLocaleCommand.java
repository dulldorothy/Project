package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.alexander.domain.fields.UserFields.LOCALE;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        String locale = req.getParameter(LOCALE);
        session.setAttribute(LOCALE, locale);
        return new Router("/jsp/index.jsp", Router.RouteType.FORWARD);
    }
}
