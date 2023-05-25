package edu.wiseup.persistence.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionTest {

    @Test
    public void testQuestionConstructor() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("question")).thenReturn("Sample question");
        Mockito.when(resultSet.getString("option_a")).thenReturn("Option A");
        Mockito.when(resultSet.getString("option_b")).thenReturn("Option B");
        Mockito.when(resultSet.getString("option_c")).thenReturn("Option C");
        Mockito.when(resultSet.getString("option_d")).thenReturn("Option D");
        Mockito.when(resultSet.getString("answer")).thenReturn("A");
        Mockito.when(resultSet.getString("category")).thenReturn("Sample category");

        Question question = new Question(resultSet);

        Assertions.assertEquals(1, question.getId());
        Assertions.assertEquals("Sample question", question.getQuestion());
        Assertions.assertEquals("Option A", question.getOptionA());
        Assertions.assertEquals("Option B", question.getOptionB());
        Assertions.assertEquals("Option C", question.getOptionC());
        Assertions.assertEquals("Option D", question.getOptionD());
        Assertions.assertEquals('A', question.getAnswer());
        Assertions.assertEquals("Sample category", question.getCategory());
    }
}



