package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Question;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionManagerTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockStatement;
    @Mock
    private ResultSet mockResultSet;

    private QuestionManager questionManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        questionManager = new QuestionManager();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Question> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(new Question(1, "Question 1"));
        expectedQuestions.add(new Question(2, "Question 2"));

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("question")).thenReturn("Question 1", "Question 2");

        List<Question> actualQuestions = questionManager.findAll(mockConnection);

        assertEquals(expectedQuestions.size(), actualQuestions.size());
        for (int i = 0; i < expectedQuestions.size(); i++) {
            assertEquals(expectedQuestions.get(i).getId(), actualQuestions.get(i).getId());
            assertEquals(expectedQuestions.get(i).getQuestion(), actualQuestions.get(i).getQuestion());
        }

        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery("SELECT * FROM question");
        verify(mockResultSet, times(3)).next();
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getString("question");
    }

    @Test
    public void testFindById() throws Exception {
        int expectedId = 1;
        String expectedQuestion = "Question 1";

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(expectedId);
        when(mockResultSet.getString("question")).thenReturn(expectedQuestion);

        Question actualQuestion = questionManager.findById(mockConnection, expectedId);

        assertEquals(expectedId, actualQuestion.getId());
        assertEquals(expectedQuestion, actualQuestion.getQuestion());

        verify(mockConnection).prepareStatement("SELECT * FROM question WHERE id = ?");
        verify(mockStatement).setInt(1, expectedId);
        verify(mockStatement).executeQuery();
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet).getInt("id");
        verify(mockResultSet).getString("question");
    }

    @Test
    public void testFindQuestionsById() throws Exception {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("question")).thenReturn("Question 1", "Question 2");

        ArrayList<Question> actualQuestions = questionManager.findQuestionsById(mockConnection, ids);

        assertEquals(ids.size(), actualQuestions.size());
        for (int i = 0; i < ids.size(); i++) {
            assertEquals(ids.get(i).intValue(), actualQuestions.get(i).getId());
            assertEquals("Question " + ids.get(i), actualQuestions.get(i).getQuestion());
        }

        verify(mockConnection, times(ids.size())).prepareStatement("SELECT * FROM question WHERE id = ?");
        for (int i = 0; i < ids.size(); i++) {
            verify(mockStatement).setInt(1, ids.get(i));
        }
        verify(mockStatement, times(ids.size())).executeQuery();
        verify(mockResultSet, times(ids.size() + 1)).next();
        verify(mockResultSet, times(ids.size())).getInt("id");
        verify(mockResultSet, times(ids.size())).getString("question");
    }
}
