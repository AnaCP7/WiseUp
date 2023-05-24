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

/**
 * Servlet utilizado para manejar el inicio de sesión de un usuario.
 */
@WebServlet(urlPatterns = {"/login-servlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Método GET del servlet. Reenvía la solicitud a doPost().
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * Método POST del servlet. Maneja la lógica de inicio de sesión.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener el usuario de la sesión
        User user = (User) req.getSession().getAttribute("userSession");

        if (user != null) {
            // Si el usuario ya está en sesión, redirigir a la página de inicio de sesión exitosa
            resp.sendRedirect("/WiseUp/login/login-done.jsp");
        }
        else {
            // Obtener los datos del formulario de inicio de sesión
            String userEntered = req.getParameter("user");
            String passEntered = req.getParameter("pass");
            String passRequired;

            try (Connection con = new MySQLConnector().getMySQLConnection()) {
                UserManager man = new UserManager();

                if (!isLegal(userEntered) || !isLegal(passEntered)) {
                    // Verificar si el usuario y la contraseña ingresados son válidos
                    // Si no son válidos, establecer un mensaje de error en la sesión
                    req.getSession().setAttribute("error", "Introduce un usuario y contraseña permitidos.");
                }
                else if (man.findByUsername(con, userEntered) == null) {
                    // Verificar si el usuario existe en el sistema
                    // Si no existe, establecer un mensaje de error en la sesión
                    req.getSession().setAttribute("error", "El usuario no se encuentra en el sistema.");
                }
                else {
                    passRequired = man.findByUsername(con, userEntered).getPassword();
                    if (!passEntered.equals(passRequired)) {
                        // Verificar si la contraseña ingresada coincide con la contraseña requerida
                        // Si no coincide, establecer un mensaje de error en la sesión
                        req.getSession().setAttribute("error", "La contraseña no es correcta.");
                    }
                    else {
                        // Si el inicio de sesión es exitoso, crear un objeto User y establecerlo en la sesión
                        user = User.builder().username(userEntered).password(passEntered).build();
                        req.getSession().setAttribute("userSession", user);
                        resp.sendRedirect("/WiseUp/login/login-done.jsp");
                        return;
                    }
                }

                // Redirigir de nuevo a la página de inicio de sesión si hubo errores
                resp.sendRedirect("/WiseUp/login/login-form/logIn.jsp");

            } catch (ClassNotFoundException | SQLException e) {
                // Manejo de excepciones en caso de errores en la conexión o consulta a la base de datos
                req.getSession().setAttribute("error", "Se ha producido un error. Intente nuevamente.");
                resp.sendRedirect("/WiseUp/login/login-form/logIn.jsp");
            }
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
