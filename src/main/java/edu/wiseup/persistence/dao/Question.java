package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Question {
    int id;
    String question;
    String image;
    String answer;

    public Question() {

    }

    public Question(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.question = result.getString("question");
            this.image = result.getString("image");
            this.answer = result.getString("answer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
