package controller.command.impl;

import controller.command.Router;
import controller.command.UploadCommand;
import controller.util.Base64Coder;

import domain.entity.UserDTO;
import service.LotsService;
import service.exeption.ServiceExeption;
import service.impl.LotServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CreateLotCommand implements UploadCommand {
    @Override
    public Router execute(HttpServletRequest request, InputStream image) throws IOException, ServletException, ServiceExeption {
        HttpSession session = request.getSession();
        Map<String,String> lotMap = new HashMap<>();


        UserDTO user = (UserDTO) session.getAttribute("user");
        lotMap.put("price",request.getParameter("price"));
        lotMap.put("title",request.getParameter("title"));
        lotMap.put("user_owner_id",String.valueOf(user.getId()));
        lotMap.put("tagList",request.getParameter("tagList"));
        lotMap.put("description",request.getParameter(""));
        String encodedImage = null;
        if (image != null){
            encodedImage = Base64Coder.encode(image);
        }
        lotMap.put("image", encodedImage);

        LotsService service = new LotServiceImpl();
        if(service.saveLot(lotMap))
        {
            return new Router("lotpage.jsp", Router.RouteType.REDIRECT);
        }
        request.setAttribute("errorMessage","Incorrect fields");


        return new Router("lotcreation.jsp", Router.RouteType.FORWARD);
        //TODO lotcreation page jsp


    }
}
