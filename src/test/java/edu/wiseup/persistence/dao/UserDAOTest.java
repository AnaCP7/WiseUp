package edu.wiseup.persistence.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDAOTest {

    @Test
    public void testUserDAOConstructor() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("username")).thenReturn("testuser");
        Mockito.when(resultSet.getString("password")).thenReturn("password");

        UserDAO userDAO = new UserDAO(resultSet);

        assertEquals(1, userDAO.getId());
        assertEquals("testuser", userDAO.getUsername());
        assertEquals("password", userDAO.getPassword());
    }
}
