package controller.command.impl;

import controller.command.Router;
import controller.command.UploadCommand;
import controller.exeptions.CommandException;
import controller.util.Base64Coder;

import domain.entity.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LotsService;
import service.exeption.ServiceException;
import service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CreateLotCommand implements UploadCommand {
    private static final Logger logger = LogManager.getLogger();
   @Override
    public Router execute(HttpServletRequest request, InputStream image) throws IOException, ServletException, CommandException {
        HttpSession session = request.getSession();
        Map<String,String> lotMap = new HashMap<>();


        UserDTO user = (UserDTO) session.getAttribute("user");
        lotMap.put("price",request.getParameter("price"));
        lotMap.put("title",request.getParameter("title"));
        lotMap.put("user_owner_id",String.valueOf(user.getId()));
        lotMap.put("tag_list",request.getParameter("tags"));
        lotMap.put("description",request.getParameter("description"));
        String encodedImage = null;
        if (image != null){
            encodedImage = Base64Coder.encode(image);
        }
        lotMap.put("encodedImage", encodedImage);

        LotsService service = new LotServiceImpl();
        try {
            service.saveLot(lotMap);
            return new Router("Controller?page=lotcreation&command=go_to_user_lots&currentPage=1", Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute("errorMessage","Incorrect fields");
            logger.error("Failed to execute CreateLotCommand Command", e);
            return new Router("lotcreation.jsp", Router.RouteType.FORWARD);

        }








    }
}
