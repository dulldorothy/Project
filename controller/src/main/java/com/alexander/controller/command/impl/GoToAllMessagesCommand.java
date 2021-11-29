package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.domain.entity.Message;
import com.alexander.domain.entity.Page;
import com.alexander.domain.entity.UserDTO;
import com.alexander.service.ServiceFactory;
import com.alexander.service.exeption.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static com.alexander.domain.fields.UserFields.*;
import static com.alexander.domain.fields.UserFields.NUMBER_OF_PAGES;

public class GoToAllMessagesCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router = new Router("/Controller?page=error&command=error_page", Router.RouteType.REDIRECT);
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        int currentPage = Integer.parseInt(req.getParameter(CURRENT_PAGE));
        Page<Message> page = new Page.PageBuilder<Message>()
                .setListOfItems(new ArrayList<>())
                .setNumberOfPages(0).create();
        try{
            page = ServiceFactory.getInstance().getMessageService().getAllMessages(user.getId(), 10*(currentPage-1), 10);
            router = new Router("/jsp/messages.jsp", Router.RouteType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException();
        }
        req.setAttribute(MESSAGE_LIST, page.getListOfItems());
        req.setAttribute(NUMBER_OF_PAGES, page.getNumberOfPages());
        return router ;
    }
}
