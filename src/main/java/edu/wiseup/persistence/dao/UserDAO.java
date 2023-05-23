package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class UserDAO {
    private int id;
    private String username;
    private String password;

    public UserDAO(int id1, String userName1) {

    }

    public UserDAO(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.username = result.getString("username");
            this.password = result.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getUserName() {
        return 0;
    }
}