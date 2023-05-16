package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

@Data
public class Score {
    private int id;
    private UserDAO user;
    private int score;
    private Instant date;

    public Score() {

    }

    public Score(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.score = result.getInt("score");
            this.date = Instant.parse(result.getString("date"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
