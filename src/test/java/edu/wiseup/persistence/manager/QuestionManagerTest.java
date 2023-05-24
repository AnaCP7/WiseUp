package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionManagerTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private QuestionManager questionManager;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        questionManager = new QuestionManager();
    }

    @Test
    void testFindAll() throws Exception {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks

        // Simular el comportamiento del ResultSet
        when(mockResultSet.next()).thenReturn(true, true, false); // Indica que hay 2 filas en el ResultSet

        // Simular el comportamiento del Statement
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Simular el comportamiento de la conexión
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        // Crear una instancia de QuestionManager
        QuestionManager questionManager = new QuestionManager();

        // Llamar al método findAll()
        List<Question> questions = questionManager.findAll(mockConnection);

        // Verificar los resultados
        assertEquals(2, questions.size());

        // Verificar que los métodos del mock se hayan llamado correctamente
        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery("SELECT * FROM question");
        verify(mockResultSet, times(2)).next();
        verify(mockStatement).close();
        verify(mockResultSet).close();
    }



    @Test
    public void testNumberOfQuestions() throws Exception {
        int expectedNumber = 5;

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(expectedNumber);

        int actualNumber = questionManager.numberOfQuestions(mockConnection);

        assertEquals(expectedNumber, actualNumber);

        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery("SELECT COUNT(*) FROM question");
        verify(mockResultSet).next();
        verify(mockResultSet).getInt(1);
    }

    @Test
    public void testFindById() throws Exception {
        int expectedId = 1;
        String expectedQuestion = "Question 1";

        // Configurar el mock para el objeto PreparedStatement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configurar el mock para el objeto ResultSet
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(expectedId);
        when(mockResultSet.getString("question")).thenReturn(expectedQuestion);

        // Llamar al método bajo prueba
        Question actualQuestion;
        actualQuestion = questionManager.findById(mockConnection, expectedId);

        // Realizar las verificaciones
        assertEquals(expectedId, actualQuestion.getId());
        assertEquals(expectedQuestion, actualQuestion.getQuestion());

        // Verificar las llamadas a los métodos
        verify(mockConnection).prepareStatement("SELECT * FROM question WHERE id = ?");
        verify(mockPreparedStatement).setInt(1, expectedId);
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet).getInt("id");
        verify(mockResultSet).getString("question");
    }

    @Test
    public void testFindQuestionsById() throws Exception {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        List<Question> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(new Question(1, "Question 1"));
        expectedQuestions.add(new Question(2, "Question 2"));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("question")).thenReturn("Question 1", "Question 2");

        List<Question> actualQuestions;
        actualQuestions = questionManager.findQuestionsById(mockConnection, ids);

        assertEquals(expectedQuestions.size(), actualQuestions.size());
        for (int i = 0; i < expectedQuestions.size(); i++) {
            assertEquals(expectedQuestions.get(i).getId(), actualQuestions.get(i).getId());
            assertEquals(expectedQuestions.get(i).getQuestion(), actualQuestions.get(i).getQuestion());
        }

        verify(mockConnection, times(ids.size())).prepareStatement("SELECT * FROM question WHERE id = ?");
        verify(mockPreparedStatement, times(ids.size())).setInt(1, ids.get(anyInt()));
        verify(mockPreparedStatement, times(ids.size())).executeQuery();
        verify(mockResultSet, times(3)).next();
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getString("question");
    }
}
