package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Data
public class Score {
    private int id;
    private UserDAO user;
    private int score;
    private LocalDateTime date;

    public Score() {

    }

    public Score(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.score = result.getInt("score");
            this.date = LocalDateTime.parse(result.getString("date"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
