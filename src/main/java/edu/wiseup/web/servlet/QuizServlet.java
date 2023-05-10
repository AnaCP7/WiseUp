package edu.wiseup.web.servlet;


import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.dao.Question;
import edu.wiseup.persistence.manager.QuestionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

@WebServlet(urlPatterns = {"/quiz-servlet"})
public class QuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection con = new MySQLConnector().getMySQLConnection()) {
            QuestionManager man = new QuestionManager();
            int questionsTotal = man.numberOfQuestions(con);

            ArrayList<Integer> questionsIDs = new ArrayList<>();
            Random random = new Random();
            int number;
            for (int i = 0; i < 5; i++) {
                do {
                    number = random.nextInt(questionsTotal);
                } while (questionsIDs.contains(number));
                questionsIDs.add(number);
            }

            ArrayList<Question> questions = man.findQuestionsById(con, questionsIDs);
            req.getSession().setAttribute("questions", questions);
            resp.sendRedirect("/WiseUp/quiz/quiz.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
