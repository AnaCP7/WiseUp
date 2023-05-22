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

    // Si no se va a llamar desde ningún lado, se debería eliminar.
   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEntered = req.getParameter("user");
        String passEntered = req.getParameter("pass1");
        String passConfirmation = req.getParameter("pass2");

        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            UserManager man = new UserManager();
            if (isLegal(userEntered) && isLegal(passEntered) && passEntered.equals(passConfirmation)
            && man.findByUsername(con, userEntered) == null) {
                man.addUser(con, userEntered, passEntered);
                resp.sendRedirect("/WiseUp/login/sign-up-done.jsp");
            } else {
                resp.sendRedirect("/WiseUp/login/login-form/createAccount.html"); //Mandar con aviso de que no es correcto

             ///WiseUp/login/create-account.jsp
            }
        } catch (ClassNotFoundException | SQLException e) {
            resp.sendRedirect("/WiseUp/login/login-form/createAccount.html"); //Mandar con aviso de que no es correcto

            //Ruta anterior:///WiseUp/login/create-account.jsp
        }
    }

    private boolean isLegal(String str) {
        return !(str.contains("%") || str.contains("'") ||
                str.contains("\"") || str.contains(";") ||
                str.contains(" ") || str.equals(""));
    }
}