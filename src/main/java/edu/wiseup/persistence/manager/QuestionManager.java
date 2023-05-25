package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase QuestionManager es responsable de gestionar las operaciones de búsqueda y recuperación de preguntas desde la base de datos.
 * Implementa la interfaz Findable para proporcionar métodos de búsqueda de preguntas.
 */
public class QuestionManager implements Findable<Question> {

    /**
     * Recupera todas las preguntas de la base de datos.
     *
     * @param con La conexión a la base de datos.
     * @return Una lista de todas las preguntas encontradas.
     */
    @Override
    public List<Question> findAll(Connection con) {
        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM question");

            List<Question> questions = new ArrayList<>();

            while (result.next()) {
                questions.add(new Question(result));
            }

            return questions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Question> findAllByCategory(Connection con, String category) {
        try (Statement stm=con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM question " + getWhere(category));

            List<Question> questions = new ArrayList<>();

            while (result.next()) {
                questions.add(new Question(result));
            }

            return questions;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene el número total de preguntas en la base de datos.
     *
     * @param con La conexión a la base de datos.
     * @return El número total de preguntas, o -1 si ocurre un error.
     */
    public int numberOfQuestions(Connection con) {
        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT COUNT(*) FROM question");
            int number = 0;
            if (result.next()) {
                number = result.getInt(1);
            }
            return number;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int numberOfQuestionsByCategory(Connection con, String category) {
        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT COUNT(*) FROM question " + getWhere(category));
            int number = 0;
            if (result.next()) {
                number = result.getInt(1);
            }
            return number;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Busca una pregunta por su identificador.
     *
     * @param con La conexión a la base de datos.
     * @param id  El identificador de la pregunta.
     * @return La pregunta encontrada, o null si no se encuentra ninguna pregunta con el identificador especificado.
     */
    @Override
    public Question findById(Connection con, int id) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM question WHERE id = ?")) {
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            Question question = null;

            while (result.next()) {
                question = new Question(result);
            }

            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Busca varias preguntas por sus identificadores.
     *
     * @param con La conexión a la base de datos.
     * @param ids Los identificadores de las preguntas.
     * @return Una lista de preguntas encontradas.
     */
    public ArrayList<Question> findQuestionsById(Connection con, ArrayList<Integer> ids) {
        ArrayList<Question> questions = new ArrayList<>();
        for (int id : ids) {
            questions.add(findById(con, id));
        }
        return questions;
    }

    public ArrayList<Question> findQuestionsByIdByCategory(Connection con, ArrayList<Integer> ids, String category) {
        List<Question> allQuestions = findAllByCategory(con, category);
        ArrayList<Question> questions = new ArrayList<>();
        for (int id : ids) {
            questions.add(allQuestions.get(id));
        }
        return questions;
    }

    private String getWhere(String condition) {
        if (condition.equals("art") || condition.equals("art_history") ||
                condition.equals("technology") || condition.equals("science") || condition.equals("literature")) {
            return "WHERE category = '" + condition + "'";
        }
        else if (condition.equals("women")) {
            return "WHERE women = 1";
        }
        else {
            return "";
        }
    }
}
