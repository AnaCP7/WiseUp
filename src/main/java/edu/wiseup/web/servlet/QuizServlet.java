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

/**
 * Servlet utilizado para generar un cuestionario aleatorio y redirigir al usuario a la página del cuestionario.
 */
@WebServlet(urlPatterns = {"/quiz-servlet"})
public class QuizServlet extends HttpServlet {

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
     * Maneja las solicitudes POST para generar un cuestionario aleatorio y redirigir al usuario a la página del cuestionario.
     *
     * @param req  La solicitud HTTP recibida.
     * @param resp La respuesta HTTP que se enviará.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            QuestionManager man = new QuestionManager();
            int questionsTotal = man.numberOfQuestions(con);

            ArrayList<Integer> questionsIDs = new ArrayList<>();
            Random random = new Random();
            int number;

            // Generar 5 IDs aleatorios de preguntas sin repetición
            for (int i = 0; i < 5; i++) {
                do {
                    number = random.nextInt(questionsTotal);
                } while (questionsIDs.contains(number));
                questionsIDs.add(number);
            }

            // Obtener las preguntas correspondientes a los IDs generados
            ArrayList<Question> questions = man.findQuestionsById(con, questionsIDs);

            // Guardar las preguntas en los atributos de sesión
            req.getSession().setAttribute("questions", questions);

            // Redirigir al usuario a la página del cuestionario
            resp.sendRedirect("/WiseUp/quiz/quiz.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
