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

/**
 * Servlet utilizado para crear una cuenta de usuario.
 */
@WebServlet(urlPatterns = {"/create-account-servlet"})
public class CreateAccountServlet extends HttpServlet {

    /**
     * Método GET del servlet.
     * Redirige al método doPost.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * Método POST del servlet.
     * Crea una cuenta de usuario con los datos proporcionados por el formulario.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEntered = req.getParameter("user");
        String passEntered = req.getParameter("pass1");
        String passConfirmation = req.getParameter("pass2");

        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            UserManager man = new UserManager();

            if (!isLegal(userEntered) || !isLegal(passEntered)){
                req.getSession().setAttribute("error", "Introduce un usuario y contraseña permitidos.");
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
                return;
            }

            resp.sendRedirect("/WiseUp/login/login-form/createAccount.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            req.getSession().setAttribute("error", "Se ha producido un error. Intente nuevamente.");
            resp.sendRedirect("/WiseUp/login/login-form/createAccount.jsp");
        }
    }

    /**
     * Método utilizado para verificar si una cadena es válida.
     * Verifica que la cadena no contenga caracteres especiales o espacios en blanco.
     */
    private boolean isLegal(String str) {
        return !(str.contains("%") || str.contains("'") ||
                str.contains("\"") || str.contains(";") ||
                str.contains(" ") || str.equals(""));
    }
}
