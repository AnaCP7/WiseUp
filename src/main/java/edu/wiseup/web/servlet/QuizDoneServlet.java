package edu.wiseup.web.servlet;

import edu.wiseup.persistence.dao.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/quiz-done-servlet"})
public class QuizDoneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Instant endTime = Instant.now();
        Instant startTime = Instant.parse(req.getParameter("start"));

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

        req.getSession().setAttribute("correctAnswers", score);

        score *= 100;
        long ms = startTime.until(endTime, ChronoUnit.MILLIS);

        if (ms > 10000 && ms <= 60000) {
            score = score - (int) (score * ((ms/1000 - 10) / 2) / 100);
        } else if (ms > 60000 && ms <= 100000) {
            score = score - (int) (score * ((ms/1000 - 60) * 0.625 + 25) / 100);
        } else if (ms > 100000) {
            score /= 2;
        }

        req.getSession().setAttribute("score", score);
        req.getSession().setAttribute("timeTaken", ms/1000);
        resp.sendRedirect("/WiseUp/quiz/score.jsp");
    }
}
