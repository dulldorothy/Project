package com.alexander.controller.command.impl;

import com.alexander.controller.command.Command;
import com.alexander.controller.command.Router;
import com.alexander.controller.exeptions.CommandException;
import com.alexander.controller.util.Base64Coder;
import com.alexander.domain.entity.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static com.alexander.domain.fields.UserFields.IMAGE;
import static com.alexander.domain.fields.UserFields.SESSION_USER_ATR;

public class UpdateLotInformationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);

        String encodedImage = null;
        InputStream image = getImage(request);
        if (image != null) {
            encodedImage = Base64Coder.encode(image);
        }


        //todo
        return null;
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
