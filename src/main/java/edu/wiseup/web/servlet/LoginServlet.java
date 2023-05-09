package edu.wiseup.web.servlet;

import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.manager.UserManager;
import edu.wiseup.web.servlet.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login-servlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("userSession");

        if (user != null) {
            resp.sendRedirect("/WiseUp/login/login-done.jsp");
        } else {
            String userEntered = req.getParameter("user");
            String passEntered = req.getParameter("pass");
            String passRequired = "";

            try (Connection con = new MySQLConnector().getMySQLConnection()) {
                UserManager man = new UserManager();

                if (man.findByUsername(con, userEntered) == null) {
                    resp.sendRedirect("/WiseUp/login/login.jsp"); //Mandar con aviso de que no es correcto
                } else {
                    passRequired = man.findByUsername(con, userEntered).getPassword();
                    if (!passEntered.equals(passRequired)) {
                        resp.sendRedirect("/WiseUp/login/login.jsp"); //Mandar con aviso de que no es correcto
                    } else {
                        user = User.builder().username(userEntered).password(passEntered).build();
                        req.getSession().setMaxInactiveInterval(Integer.parseInt(getServletContext().getInitParameter("sessionTimeout")));
                        req.getSession().setAttribute("userSession", user);

                        resp.sendRedirect("/WiseUp/login/login-done.jsp");
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                resp.sendRedirect("/WiseUp/login/login.jsp"); //Mandar con aviso de que no es correcto
            }
        }
    }
}