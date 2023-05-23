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
        }
        else {
            String userEntered = req.getParameter("user");
            String passEntered = req.getParameter("pass");
            String passRequired;

            try (Connection con = new MySQLConnector().getMySQLConnection()) {
                UserManager man = new UserManager();

                if (!isLegal(userEntered) | !isLegal(passEntered)) {
                    req.getSession().setAttribute("error", "Introduce usuario y contraseña permitidos.");
                }
                else if (man.findByUsername(con, userEntered) == null) {
                    req.getSession().setAttribute("error", "El usuario no se encuentra en el sistema.");
                }
                else {
                    passRequired = man.findByUsername(con, userEntered).getPassword();
                    if (!passEntered.equals(passRequired)) {
                        req.getSession().setAttribute("error", "La contraseña no es correcta.");
                    }
                    else {
                        user = User.builder().username(userEntered).password(passEntered).build();
                        //req.getSession().setMaxInactiveInterval(Integer.parseInt(getServletContext().getInitParameter("sessionTimeout")));
                        req.getSession().setAttribute("userSession", user);
                        resp.sendRedirect("/WiseUp/login/login-done.jsp");
                    }
                }

                resp.sendRedirect("/WiseUp/login/login-form/logIn.jsp");

            } catch (ClassNotFoundException | SQLException e) {
                req.getSession().setAttribute("error", "Se ha producido un error. Intente de nuevo.");
                resp.sendRedirect("/WiseUp/login/login-form/logIn.jsp");
            }
        }
    }

    private boolean isLegal(String str) {
        return !(str.contains("%") || str.contains("'") ||
                str.contains("\"") || str.contains(";") ||
                str.contains(" ") || str.equals(""));
    }
}