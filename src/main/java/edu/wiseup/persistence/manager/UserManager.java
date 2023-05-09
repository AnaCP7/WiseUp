package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
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
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM user WHERE username = ?")) {
            stm.setString(1, username);
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
