package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.dao.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScoreManagerTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;
    @Mock
    private UserManager mockUserManager;

    @InjectMocks
    private ScoreManager scoreManager;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    public Connection getMockConnection() {
        return mockConnection;
    }

    public void setMockConnection(Connection mockConnection) {
        this.mockConnection = mockConnection;
    }

    @Test
    public void testFindAll() throws Exception {

        int id1 = 1;
        int id2 = 2;
        String userName1 = "User 1";
        String userName2 = "User 2";

        Score score1 = new Score();
        score1.setId(id1);
        score1.setUser(new UserDAO(id1, userName1));

        Score score2 = new Score();
        score2.setId(id2);
        score2.setUser(new UserDAO(id2, userName2));

        List<Score> expectedScores = new ArrayList<>();
        expectedScores.add(score1);
        expectedScores.add(score2);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(id1, id2);
        when(mockResultSet.getInt("id_user")).thenReturn(id1, id2);
        when(mockUserManager.findById(mockConnection, id1)).thenReturn(new UserDAO(id1, userName1));
        when(mockUserManager.findById(mockConnection, id2)).thenReturn(new UserDAO(id2, userName2));


        List<Score> actualScores;
        actualScores = Collections.<Score>unmodifiableList(scoreManager.findAll(mockConnection));


        assertEquals(expectedScores.size(), actualScores.size());
        for (int i = 0; i < expectedScores.size(); i++) {
            Score expectedScore = expectedScores.get(i);
            Score actualScore = actualScores.get(i);

            assertEquals(expectedScore.getId(), actualScore.getId());
            assertEquals(expectedScore.getUser().getId(), actualScore.getUser().getId());
            assertEquals(expectedScore.getUser().getUsername(), actualScore.getUser().getUsername());
        }


        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery("SELECT * FROM question");
        verify(mockResultSet, times(3)).next();
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getInt("id_user");
        verify(mockUserManager).findById(mockConnection, id1);
        verify(mockUserManager).findById(mockConnection, id2);
    }

    @Test
    public void testFindById() throws Exception {

        int id = 1;
        String userName = "User 1";

        Score expectedScore = new Score();
        expectedScore.setId(id);
        expectedScore.setUser(new UserDAO(id, userName));


        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(id);
        when(mockResultSet.getInt("id_user")).thenReturn(id);
        when(mockUserManager.findById(mockConnection, id)).thenReturn(new UserDAO(id, userName));


        Score actualScore;
        actualScore = scoreManager.findById(mockConnection, id);


        assertEquals(expectedScore.getId(), actualScore.getId());
        assertEquals(expectedScore.getUser().getId(), actualScore.getUser().getId());
        assertEquals(expectedScore.getUser().getUsername(), actualScore.getUser().getUsername());


        verify(mockConnection).prepareStatement("SELECT * FROM score a, user b where a.id = ? and a.id_user = b.id");
        verify(mockPreparedStatement).setInt(1, id);
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet).getInt("id");
        verify(mockResultSet).getInt("id_user");
        verify(mockUserManager).findById(mockConnection, id);
    }

    @Test
    public void testAddScore() throws Exception {

        int idUser = 1;
        int score = 100;
        Instant date = Instant.now();


        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        scoreManager.addScore(mockConnection, idUser, score, date);

        verify(mockConnection).prepareStatement("INSERT INTO score (id_user, score, date) VALUES (?, ?, ?);");
        verify(mockPreparedStatement).setInt(1, idUser);
        verify(mockPreparedStatement).setInt(2, score);
        verify(mockPreparedStatement).setTimestamp(3, java.sql.Timestamp.from(date));
        verify(mockPreparedStatement).execute();
    }

    @Test
    public void testFindAllSorted() throws Exception {

        int id1 = 1;
        int id2 = 2;
        String userName1 = "User 1";
        String userName2 = "User 2";

        Score score1 = new Score();
        score1.setId(id1);
        score1.setUser(new UserDAO(id1, userName1));

        Score score2 = new Score();
        score2.setId(id2);
        score2.setUser(new UserDAO(id2, userName2));

        List<Score> expectedScores = new ArrayList<>();
        expectedScores.add(score1);
        expectedScores.add(score2);


        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(id1, id2);
        when(mockResultSet.getInt("id_user")).thenReturn(id1, id2);
        when(mockUserManager.findById(mockConnection, id1)).thenReturn(new UserDAO(id1, userName1));
        when(mockUserManager.findById(mockConnection, id2)).thenReturn(new UserDAO(id2, userName2));

        List<Score> actualScores;
        actualScores = scoreManager.findAllSorted(mockConnection);

        assertEquals(expectedScores.size(), actualScores.size());
        for (int i = 0; i < expectedScores.size(); i++) {
            Score expectedScore = expectedScores.get(i);
            Score actualScore = actualScores.get(i);

            assertEquals(expectedScore.getId(), actualScore.getId());
            assertEquals(expectedScore.getUser().getId(), actualScore.getUser().getId());
            assertEquals(expectedScore.getUser().getUsername(), actualScore.getUser().getUsername());
        }


        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery("SELECT * FROM score ORDER BY score DESC, date ASC");
        verify(mockResultSet, times(3)).next();
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getInt("id_user");
        verify(mockUserManager).findById(mockConnection, id1);
        verify(mockUserManager).findById(mockConnection, id2);
    }
}

