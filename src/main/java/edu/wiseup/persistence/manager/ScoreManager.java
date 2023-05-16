package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.dao.UserDAO;

import java.sql.*;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class ScoreManager {
    public List<Score> findAll(Connection con) {
        try (Statement stm=con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM question");

            //result.beforeFirst();

            List<Score> scores = new ArrayList<>();
            Map<Integer, Integer> users = new HashMap<>();

            while (result.next()) {
                scores.add(new Score(result));
                users.put(result.getInt("id"), result.getInt("id_user"));
            }

            fillUsers(con, users, scores);

            return scores;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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

    private void fillUsers(Connection con, Map<Integer, Integer> users, List<Score> scores) {
        Set<Integer> userIDs = new HashSet<>(users.values());
        Map<Integer, UserDAO> usersMap = new UserManager().findAllByIds(con, userIDs).stream()
                .collect(Collectors.toMap(UserDAO::getId, data -> data));

        scores.forEach(score -> {
            int userID = users.get(score.getId());
            UserDAO foundUser = usersMap.get(userID);
            score.setUser(foundUser);
        });
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
}
