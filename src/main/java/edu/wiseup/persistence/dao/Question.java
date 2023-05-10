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

    public Question() {

    }

    public Question(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.question = result.getString("question");
            this.image = result.getString("image");
            this.optionA = result.getString("optionA");
            this.optionB = result.getString("optionB");
            this.optionC = result.getString("optionC");
            this.optionD = result.getString("optionD");
            this.answer = result.getString("answer").charAt(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
