package controller.command.impl;

import controller.command.Router;
import controller.command.UploadCommand;
import controller.exeptions.CommandException;
import controller.util.Base64Coder;
import domain.entity.UserDTO;
import service.UserService;
import service.exeption.ServiceExeption;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

public class UpdateUserImageCommand implements UploadCommand {
    @Override
    public Router execute(HttpServletRequest request, InputStream inputStream) throws IOException, ServletException, ServiceExeption, CommandException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        String encodedImage = null;
        if (inputStream != null) {
            encodedImage = Base64Coder.encode(inputStream);
        }

        UserService service = new UserServiceImpl();

        try {
            service.changeUserImage(user, encodedImage);
        } catch (ServiceExeption serviceExeption) {
            throw new CommandException("Failed to update user image");
        }
        return new Router("userpage.jsp", Router.RouteType.REDIRECT);
    }
}
