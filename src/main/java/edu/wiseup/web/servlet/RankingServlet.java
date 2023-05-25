package edu.wiseup.web.servlet;

import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.manager.ScoreManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/ranking-servlet"})
public class RankingServlet extends HttpServlet {

    /**
     * Maneja las solicitudes GET enviadas al servlet.
     * Redirige la solicitud a doPost para mantener la consistencia.
     *
     * @param req  La solicitud HTTP.
     * @param resp La respuesta HTTP.
     * @throws ServletException Si ocurre un error de servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * Maneja las solicitudes POST enviadas al servlet.
     * Recupera los puntajes de los usuarios desde la base de datos y los agrega al atributo de sesión.
     * Luego redirige la solicitud a la página de clasificación (ranking.jsp).
     *
     * @param req  La solicitud HTTP.
     * @param resp La respuesta HTTP.
     * @throws ServletException Si ocurre un error de servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            ScoreManager sman = new ScoreManager();

            req.getSession().setAttribute("scores", sman.findAllSorted(con));

            resp.sendRedirect("/WiseUp/quiz/ranking.jsp");
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
