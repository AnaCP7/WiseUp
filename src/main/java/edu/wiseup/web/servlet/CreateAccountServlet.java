package edu.wiseup.web.servlet;

import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/create-account-servlet"})
public class CreateAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEntered = req.getParameter("user");
        String passEntered = req.getParameter("pass1");
        String passConfirmation = req.getParameter("pass2");

        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            UserManager man = new UserManager();

            if (!isLegal(userEntered) | !isLegal(passEntered)){
                req.getSession().setAttribute("error", "Introduce usuario y contraseña permitidos.");
            }
            else if (!passEntered.equals(passConfirmation)) {
                req.getSession().setAttribute("error", "Las contraseñas deben coincidir.");
            }
            else if (man.findByUsername(con, userEntered) != null) {
                req.getSession().setAttribute("error", "El usuario ya se encuentra en el sistema.");
            }
            else {
                man.addUser(con, userEntered, passEntered);
                resp.sendRedirect("/WiseUp/login/sign-up-done.jsp");
            }

            resp.sendRedirect("/WiseUp/login/login-form/createAccount.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            req.getSession().setAttribute("error", "Se ha producido un error. Intente de nuevo.");
            resp.sendRedirect("/WiseUp/login/login-form/createAccount.jsp");
        }
    }

    private boolean isLegal(String str) {
        return !(str.contains("%") || str.contains("'") ||
                str.contains("\"") || str.contains(";") ||
                str.contains(" ") || str.equals(""));
    }
}