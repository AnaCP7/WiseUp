package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionManager {
    public List<Question> findAll(Connection con) {
        try (Statement stm=con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM question");

            //result.beforeFirst();

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

    public int numberOfQuestions(Connection con) {
        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT COUNT(*) FROM question");
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

    public Question findById(Connection con, int id) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM question WHERE id = ?")) {
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();
            //result.beforeFirst();

            Question question = null;

            while (result.next()) {
                question = new Question(result);
            }

            return question;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Question> findQuestionsById(Connection con, ArrayList<Integer> ids) {
        ArrayList<Question> questions = new ArrayList<>();
        for (int id : ids) {
            questions.add(findById(con, id));
        }
        return questions;
    }
}
