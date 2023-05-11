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
import java.time.LocalDateTime;

@WebServlet(urlPatterns = {"/submit-score-servlet"})
public class SubmitScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //mete score en database y muestra ranking
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            ScoreManager sman = new ScoreManager();
            UserManager uman = new UserManager();

            int score = Integer.parseInt(req.getParameter("score"));
            User user = (User) req.getSession().getAttribute("userSession");
            int idUser = uman.findByUsername(con, user.getUsername()).getId();
            sman.addScore(con, idUser, score, LocalDateTime.now());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/WiseUp/quiz/ranking.jsp");
    }
}