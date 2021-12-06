package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.controller.util.Base64Coder;
import com.alexander.domain.entity.UserDTO;
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

import static com.alexander.domain.fields.UserFields.IMAGE;
import static com.alexander.domain.fields.UserFields.SESSION_USER_ATR;

public class ChangeUserImageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        HttpSession session = req.getSession();
        Router router = new Router("/Controller?page=error&command=error_page", Router.RouteType.REDIRECT);

        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        InputStream image = getImage(req);
        String encodedImage = null;
        if (image != null) {
            encodedImage = Base64Coder.encode(image);
        }

        try {
            ServiceFactory.getInstance().getUserService().changeUserImage(user, encodedImage);
            user.setImage(encodedImage);
            session.setAttribute(SESSION_USER_ATR, user);
            router = new Router("/Controller?page=userpage&command=go_to_page", Router.RouteType.REDIRECT);

        } catch (ServiceException e) {
            LOGGER.error("Failed to change user image", e);
            throw new CommandException();
        }
        return router;
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
