package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.dao.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserManager implements Findable<UserDAO>{
    @Override
    public List<UserDAO> findAll(Connection con) {
        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM user");

            //result.beforeFirst();

            List<UserDAO> users = new ArrayList<>();

            while (result.next()) {
                users.add(new UserDAO(result));
            }

            return users;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<UserDAO> findAllByIds(Connection con, Set<Integer> ids) {
        String sql = String.format(
                "SELECT * "
                + "FROM score a, user b "
                + "WHERE a.id_user = b.id "
                + "AND a.id IN (%s)",
                ids.stream().map(data -> "\"" + data + "\"").collect(Collectors.joining(", ")));

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);

            //result.beforeFirst();

            List<UserDAO> users = new ArrayList<>();

            while (result.next()) {
                Score score = new Score(result);
                UserDAO user = new UserDAO(result);
                score.setUser(user);
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDAO findById(Connection con, int id) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();
            //result.beforeFirst();

            UserDAO user = null;

            while (result.next()) {
                user = new UserDAO(result);
            }

            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserDAO findByUsername(Connection con, String username) {
        String sql = "SELECT * FROM user WHERE username LIKE '" + username + "'";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();

            UserDAO user = null;
            while (result.next()) {
                user = new UserDAO(result);
            }
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(Connection con, String username, String password) {
        try (PreparedStatement stm = con.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?);")) {
            stm.setString(1, username);
            stm.setString(2, password);

            stm.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
