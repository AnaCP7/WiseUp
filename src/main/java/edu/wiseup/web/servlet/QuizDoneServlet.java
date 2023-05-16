package edu.wiseup.web.servlet;

import edu.wiseup.persistence.dao.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/quiz-done-servlet"})
public class QuizDoneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Question> questions = (ArrayList<Question>) req.getSession().getAttribute("questions");
        Question question;
        String answerEntered, correctAnswer;
        int score = 0;

        for (int i = 0; i < 5; i++) {
            question = questions.get(i);
            correctAnswer = question.getAnswer() + "";
            answerEntered = req.getParameter("question-" + (i+1));
            if (answerEntered != null && answerEntered.endsWith(correctAnswer)) {
                score++;
            }
        }

        req.getSession().setAttribute("score", score);
        resp.sendRedirect("/WiseUp/quiz/score.jsp");
    }
}
