package edu.wiseup.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Servlet encargado de cerrar la sesión del usuario.
 * Al acceder a este servlet mediante una solicitud GET o POST,
 * se eliminan todos los atributos de la sesión y se redirige al usuario a la página principal.
 */


@WebServlet(urlPatterns = {"/logout-servlet"})
public class LogoutServlet extends HttpServlet {

    /**
     * Método GET del servlet. Realiza la desconexión del usuario.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los nombres de los atributos de la sesión
        Enumeration<String> attributes = req.getSession().getAttributeNames();

        // Remover todos los atributos de la sesión
        while (attributes.hasMoreElements()) {
            req.getSession().removeAttribute(attributes.nextElement());
        }

        // Redirigir a la página principal
        resp.sendRedirect("/WiseUp/");
    }

    /**
     * Método POST del servlet. Reenvía la solicitud a doGet().
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
