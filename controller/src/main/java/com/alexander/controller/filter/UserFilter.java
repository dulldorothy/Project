package com.alexander.controller.filter;

import com.alexander.controller.command.CommandEnum;
import com.alexander.domain.entity.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

import static com.alexander.domain.fields.UserFields.*;
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
        if (session.getAttribute(SESSION_USER_ATR) == null) {
            UserDTO user = new UserDTO.UserDTOBuilder()
                    .setRole(GUEST_ROLE)
                    .create();
            session.setAttribute(SESSION_USER_ATR, user);
        }
        if (session.getAttribute(LOCALE) == null)
        {
            session.setAttribute(LOCALE, ENGLISH);
        }
        String commandName = request.getParameter(COMMAND);
        CommandEnum command;
        if (commandName != null) {
            try {
                command = CommandEnum.valueOf(commandName.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                command = null;
            }
        } else {
            command = CommandEnum.DEFAULT;
        }

        RoleCommands roleCommand = RoleCommands.getInstance();
        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER_ATR);
        if (command != null) {
            if (!roleCommand.checkCommand(user.getRole(), command)) {
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
