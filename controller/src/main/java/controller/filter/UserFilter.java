package controller.filter;

import controller.command.Command;
import controller.command.Commands;
import controller.command.UploadCommand;
import controller.command.UploadCommands;
import domain.entity.User;
import domain.entity.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;
@WebFilter(urlPatterns = "/Controller")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            UserDTO user = new UserDTO.UserDTOBuilder()
                    .setRole("guest")
                    .create();
            session.setAttribute("user", user);
        }
        String commandName = request.getParameter("command");
        Commands command;
        UploadCommands uploadCommand = UploadCommands.DEFAULT;
        if (commandName != null) {
            try {
                command = Commands.valueOf(commandName.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                command = null;
            }
            try {
                uploadCommand = UploadCommands.valueOf(commandName.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                uploadCommand = null;
            }
        } else {
            command = Commands.DEFAULT;
        }

        RoleCommands roleCommand = RoleCommands.getInstance();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (command != null) {
            if (!roleCommand.checkCommand(user.getRole(), command)) {
                resp.sendRedirect("/Controller?page=index&command=go_to_page");
            } else {
                chain.doFilter(request, response);
            }
        } else if (uploadCommand != null) {
            if (!roleCommand.checkUploadCommand(user.getRole(), uploadCommand)) {
                resp.sendRedirect("/Controller?page=index&command=go_to_page");
            } else {
                chain.doFilter(request, response);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
