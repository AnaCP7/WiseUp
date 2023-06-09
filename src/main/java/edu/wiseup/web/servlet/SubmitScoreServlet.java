package edu.wiseup.web.servlet;

import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.manager.ScoreManager;
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
import java.time.Instant;

@WebServlet(urlPatterns = {"/submit-score-servlet"})
public class SubmitScoreServlet extends HttpServlet {

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
     * Recupera el puntaje del usuario desde la solicitud y lo guarda en la base de datos.
     * Luego redirige la solicitud a la página de clasificación (ranking-servlet) para actualizar la lista de puntajes.
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
            UserManager uman = new UserManager();

            int score = Integer.parseInt(req.getParameter("score"));
            User user = (User) req.getSession().getAttribute("userSession");
            int idUser = uman.findByUsername(con, user.getUsername()).getId();
            sman.addScore(con, idUser, score, Instant.now());

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendRedirect("/WiseUp/ranking-servlet");
        }

        resp.sendRedirect("/WiseUp/ranking-servlet");
    }
}
