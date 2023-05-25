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


/**
 * Servlet utilizado para procesar el envío del cuestionario completado por el usuario.
 * Calcula la puntuación del cuestionario y el tiempo transcurrido, y los guarda en los atributos de sesión.
 * Luego redirige al usuario a la página de puntuación.
 */
@WebServlet(urlPatterns = {"/quiz-done-servlet"})
public class QuizDoneServlet extends HttpServlet {

    /**
     * Maneja las solicitudes GET enviándolas al método doPost().
     *
     * @param req  La solicitud HTTP recibida.
     * @param resp La respuesta HTTP que se enviará.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * Maneja las solicitudes POST enviadas desde el cuestionario completado.
     * Calcula la puntuación del cuestionario, el tiempo transcurrido y los guarda en los atributos de sesión.
     * Redirige al usuario a la página de puntuación.
     *
     * @param req  La solicitud HTTP recibida.
     * @param resp La respuesta HTTP que se enviará.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Instant endTime = Instant.now();
        Instant startTime = Instant.parse(req.getParameter("start"));

        ArrayList<Question> questions = (ArrayList<Question>) req.getSession().getAttribute("questions");
        Question question;
        String answerEntered, correctAnswer;
        int score = 0;

        // Calcular la puntuación del cuestionario
        for (int i = 0; i < 5; i++) {
            question = questions.get(i);
            correctAnswer = question.getAnswer() + "";
            answerEntered = req.getParameter("question-" + (i + 1));
            if (answerEntered != null && answerEntered.endsWith(correctAnswer)) {
                score++;
            }
        }

        req.getSession().setAttribute("correctAnswers", score);

        // Calcular la puntuación final considerando el tiempo transcurrido
        score *= 100;
        long ms = startTime.until(endTime, ChronoUnit.MILLIS);

        if (ms > 10000 && ms <= 60000) {
            score = score - (int) (score * ((ms / 1000 - 10) / 2) / 100);
        } else if (ms > 60000 && ms <= 100000) {
            score = score - (int) (score * ((ms / 1000 - 60) * 0.625 + 25) / 100);
        } else if (ms > 100000) {
            score /= 2;
        }

        // Guardar la puntuación y el tiempo transcurrido en los atributos de sesión
        req.getSession().setAttribute("score", score);
        req.getSession().setAttribute("timeTaken", ms / 1000);

        // Redirigir al usuario a la página de puntuación
        resp.sendRedirect("/WiseUp/quiz/score.jsp");
    }
}
