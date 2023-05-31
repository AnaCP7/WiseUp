package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserManagerTest {
    private UserManager userManager;
    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        userManager = new UserManager();
        mockConnection = mock(Connection.class);
    }

//    @Test
//    public void testFindAll_ReturnsAllUsers() throws SQLException {
//        // Arrange
//        Statement mockStatement = mock(Statement.class);
//        ResultSet mockResultSet = mock(ResultSet.class);
//        when(mockConnection.createStatement()).thenReturn(mockStatement);
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//
//        List<UserDAO> expectedUsers = new ArrayList<>();
//        expectedUsers.add(new UserDAO(1, "user1"));
//        expectedUsers.add(new UserDAO(2, "user2"));
//
//        // Mock the UserDAO objects from the result set
//        when(mockResultSet.next()).thenReturn(true, true, true);
//        when(mockResultSet.getInt("id")).thenReturn(1, 2);
//        when(mockResultSet.getString("username")).thenReturn("user1", "user2");
//
//        // Act
//        List<UserDAO> actualUsers = userManager.findAll(mockConnection);
//
//        // Assert
//        assertEquals(expectedUsers.size(), actualUsers.size());
//        assertTrue(actualUsers.containsAll(expectedUsers));
//    }

    @Test
    public void testFindById_ReturnsUserWithMatchingId() throws SQLException {
        // Arrange
        int userId = 1;
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        UserDAO expectedUser = new UserDAO(userId, "user1");

        // Mock the UserDAO object from the result set
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(0);
        when(mockResultSet.getString("username")).thenReturn(null);

        // Act
        UserDAO actualUser = userManager.findById(mockConnection, userId);

        // Assert
        assertEquals(expectedUser, actualUser);
    }


}
