package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Question {
    private int id;
    private String question;
    private String image;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char answer;
    private String category;

    public Question() {

    }

    public Question(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.question = result.getString("question");
            this.image = result.getString("image");
            this.optionA = result.getString("option_a");
            this.optionB = result.getString("option_b");
            this.optionC = result.getString("option_c");
            this.optionD = result.getString("option_d");
            this.answer = result.getString("answer").charAt(0);
            this.category = result.getString("category");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
