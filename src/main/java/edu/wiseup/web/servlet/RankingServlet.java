package edu.wiseup.web.servlet;

import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.manager.ScoreManager;
import edu.wiseup.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/ranking-servlet"})
public class RankingServlet extends HttpServlet {

    private ScoreService service;

    @Override
    public void init()
            throws
            ServletException {
        service = new ScoreService(new MySQLConnector(), new ScoreManager());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws
            ServletException,
            IOException {

        try {
            if (req.getParameter("id") !=null && !req.getParameter("id").trim().isEmpty()) {
                int id=Integer.parseInt(req.getParameter("id"));
                req.getSession().setAttribute("score", service.findScoreById(id));
            }
            else {
                req.getSession().setAttribute("scores", service.findAllScores());
            }
            resp.sendRedirect("/WiseUp/quiz/ranking.jsp");

        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}