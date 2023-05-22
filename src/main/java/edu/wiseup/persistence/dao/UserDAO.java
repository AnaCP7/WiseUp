package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class UserDAO {
    private int id;
    private String username;
    private String password;

    public UserDAO() {

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
}