package edu.wiseup.persistence.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScoreTest {

    @Mock
    private ResultSet resultSetMock;

    @InjectMocks
    private Score score;

    @Test
    public void testScoreConstructor() throws SQLException {
        int id = 1;
        int scoreValue = 100;
        String dateStr = "2023-05-30T12:34:56.00Z";

        when(resultSetMock.getInt("id")).thenReturn(id);
        when(resultSetMock.getInt("score")).thenReturn(scoreValue);
        when(resultSetMock.getString("date")).thenReturn(dateStr);

        score = new Score(resultSetMock);

        assertEquals(id, score.getId());
        assertEquals(scoreValue, score.getScore());
        assertEquals(Instant.parse(dateStr), score.getDate());
    }
}
