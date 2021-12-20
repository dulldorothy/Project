package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.controller.util.Base64Coder;
import com.alexander.service.LotsService;
import com.alexander.service.ServiceFactory;
import com.alexander.service.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static com.alexander.domain.fields.UserFields.IMAGE;
import static com.alexander.domain.fields.UserFields.LOT_ID;

public class ChangeLotImageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final LotsService lotsService = ServiceFactory.getInstance().getLotsService();
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        Router router = new Router("/Controller?page=error&command=error_page", Router.RouteType.REDIRECT);
        InputStream image = getImage(req);
        int lotID = Integer.parseInt(req.getParameter(LOT_ID));
        String encodedImage = null;
        if (image != null) {
            encodedImage = Base64Coder.encode(image);
        }

        try {
            lotsService.changeLotImageByID(lotID, encodedImage);
            router = new Router("/Controller?lot_id=" + lotID + "&command=go_to_lot_page", Router.RouteType.REDIRECT);

        } catch (ServiceException e) {
            LOGGER.error("Failed to change Lot image", e);
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
