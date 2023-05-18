package edu.wiseup.persistence.connector;

import static org.mockito.Mockito.*;

import edu.wiseup.persistence.connector.MySQLConnector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnectorTest {

    @Test
    public void testGetMySQLConnection() throws ClassNotFoundException, SQLException {
        // Crea el mock
        MySQLConnector mySQLConnector = mock(MySQLConnector.class);


        when(mySQLConnector.getMySQLConnection()).thenReturn(mock(Connection.class));

        // Realiza la llamada a getConnection
        Connection connection = mySQLConnector.getMySQLConnection();

        // Realiza más pruebas utilizando la conexión
        Assertions.assertNotNull(connection);
        // Comprueba que la conexión es nula
        Assertions.assertNotNull(connection);
        // Comprueba que la conexión esté abierta
        Assertions.assertTrue(connection.isValid(1));

        // Comprueba que la conexión esté en el esquema correcto
        Assertions.assertEquals("nombre_esquema", connection.getSchema());

        // Close connection y verificarlo
        connection.close();
        Assertions.assertTrue(connection.isClosed());


        // Comprueba que se haya llamado al método getMySQLConnection
        verify(mySQLConnector).getMySQLConnection();
    }


}
