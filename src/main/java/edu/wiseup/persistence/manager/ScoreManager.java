package edu.wiseup.persistence.manager;


import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.dao.UserDAO;

import java.sql.*;
import java.time.Instant;
import java.util.*;

public class ScoreManager implements Findable<Score> {
    @Override
    public List<Score> findAll(Connection con) {
        try (Statement stm=con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM question");

            //result.beforeFirst();
            List<Score> scores = new ArrayList<>();

            UserManager uman = new UserManager();
            Score score = null;

            while (result.next()) {
                score = new Score(result);
                score.setUser(uman.findById(con, result.getInt("id_user")));
                scores.add(score);
            }

            return scores;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Score findById(Connection con, int id) {
        String sql =
                "SELECT * "
                + "FROM score a, user b "
                + "where a.id = ? "
                + "and a.id_user = b.id";

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();
            //result.beforeFirst();

            Score score = null;

            while (result.next()) {
                score = new Score(result);
                UserDAO user = new UserDAO(result);
                score.setUser(user);
            }

            return score;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addScore(Connection con, int idUser, int score, Instant date) {
        String sql = "INSERT INTO score (id_user, score, date) VALUES (?, ?, ?);";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idUser);
            stm.setInt(2, score);
            stm.setTimestamp(3, Timestamp.from(date));

            stm.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Score> findAllSorted(Connection con) {
        String sql = "SELECT * FROM score ORDER BY score DESC, date ASC";

        try (Statement stm=con.createStatement()) {
            ResultSet result = stm.executeQuery(sql);

            //result.beforeFirst();
            List<Score> scores = new ArrayList<>();

            UserManager uman = new UserManager();
            Score score = null;

            while (result.next()) {
                score = new Score(result);
                score.setUser(uman.findById(con, result.getInt("id_user")));
                scores.add(score);
            }

            return scores;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
