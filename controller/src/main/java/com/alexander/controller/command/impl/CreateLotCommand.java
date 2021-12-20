package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.controller.util.Base64Coder;
import com.alexander.domain.entity.UserDTO;
import com.alexander.service.LotsService;
import com.alexander.service.ServiceFactory;
import com.alexander.service.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.alexander.domain.fields.UserFields.*;

public class CreateLotCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final LotsService lotsService = ServiceFactory.getInstance().getLotsService();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandException {
        HttpSession session = request.getSession();
        Map<String, String> lotMap = new HashMap<>();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        lotMap.put(PRICE, request.getParameter(PRICE));
        lotMap.put(TITLE, request.getParameter(TITLE));
        lotMap.put(OWNER_ID, String.valueOf(user.getId()));
        lotMap.put(TAG_LIST, request.getParameter(TAG_LIST));
        lotMap.put(DESCRIPTION, request.getParameter(DESCRIPTION));
        InputStream image = getImage(request);
        String encodedImage = null;
        if (image != null) {
            encodedImage = Base64Coder.encode(image);
        }
        lotMap.put(ENCODED_IMAGE, encodedImage);
        try {
            lotsService.saveLot(lotMap);
            return new Router("Controller?page=lotcreation&command=go_to_user_lots&currentPage=1", Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE, "Incorrect fields");
            LOGGER.error("Failed to execute CreateLotCommand Command", e);
            return new Router(PAGE_PREV_PATH+"lotcreation.jsp", Router.RouteType.FORWARD);
        }
    }

    private InputStream getImage(HttpServletRequest request) throws CommandException {
        InputStream image = null;
        Part part = null;
        try {
            part = request.getPart(IMAGE);
            if (part.getSize() != 0) {
                image = part.getInputStream();
            }
        } catch (IOException e) {
            throw new CommandException(e);
        } catch (ServletException e) {
            throw new CommandException(e);
        }
        return image;
    }
}
