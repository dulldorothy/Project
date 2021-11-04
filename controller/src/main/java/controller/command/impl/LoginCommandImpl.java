package controller.command.impl;

import controller.command.Command;
import dao.database.impl.DAOFactory;
import dao.entity.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommandImpl implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);


        DAOFactory daoFactory = new DAOFactory();
        if (!daoFactory.getUserDAO().userExistsByLoginAndPassword(req.getParameter("username"),
                req.getParameter("password"))){
            req.setAttribute("error", "No such login or password!");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

        UserDTO user = daoFactory.getUserDAO().getUserByLoginAndPass(req.getParameter("username"),
                req.getParameter("password"));

        session.setAttribute("username", user.getUserName());
        session.setAttribute("lastname", user.getLastName());
        session.setAttribute("firstname", user.getFirstName());

        resp.sendRedirect("userpage.jsp");







    }
}
