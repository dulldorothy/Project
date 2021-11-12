package controller.command;

import service.exeption.ServiceExeption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public interface UploadCommand {
    Router execute(HttpServletRequest request, InputStream inputStream) throws IOException, ServletException, ServiceExeption;


}
