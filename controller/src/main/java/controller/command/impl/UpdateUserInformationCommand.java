package controller.command.impl;

import controller.command.Router;
import controller.command.UploadCommand;
import controller.exeptions.CommandException;
import controller.util.Base64Coder;
import domain.entity.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.exeption.ServiceException;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

public class UpdateUserInformationCommand implements UploadCommand {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request, InputStream inputStream) throws IOException, ServletException, ServiceException, CommandException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        String encodedImage = null;
        if (inputStream != null) {
            encodedImage = Base64Coder.encode(inputStream);
        }

        UserService service = new UserServiceImpl();

        try {
            service.changeUserFirstName(user, request.getParameter("firstname"));
            service.changeUserLastName(user, request.getParameter("lastname"));
            service.changeUserPassword(user, request.getParameter("password"));
            service.changeUserImage(user, encodedImage);
        } catch (ServiceException serviceException) {
            logger.error("Failed to execute UserInformation  Command", serviceException);
            throw new CommandException(serviceException);
        }
        return new Router("userpage.jsp", Router.RouteType.REDIRECT);
    }
}
