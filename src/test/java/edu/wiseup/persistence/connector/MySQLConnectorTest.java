package edu.wiseup.persistence.connector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MySQLConnectorTest {

    @Mock
    private Properties mockProperties;

    @Mock
    private Connection mockConnection;

    private MySQLConnector mySQLConnector;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mySQLConnector = spy(new MySQLConnector());
        doReturn(mockProperties).when(mySQLConnector).getProp();
    }

    @Test
    void testGetMySQLConnection() throws SQLException, ClassNotFoundException {
        // Configuración del mock
        when(mockProperties.getProperty(anyString())).thenReturn("mock-value");
        when(mockConnection.getCatalog()).thenReturn("mock-catalog");

        // Simulamos el registro del controlador para evitar la excepción ClassNotFoundException
        doNothing().when(mySQLConnector).registerDriver();

        // Simulamos la obtención de la conexión
        doReturn(mockConnection).when(mySQLConnector).getConnection();

        // Ejecutamos el método que queremos probar
        Connection actualConnection = mySQLConnector.getMySQLConnection();

        // Verificamos que se hayan llamado los métodos necesarios
        verify(mySQLConnector).registerDriver();
        verify(mySQLConnector).getConnection();

        // Verificamos el resultado
        assertEquals("mock-catalog", actualConnection.getCatalog());

        // Verificamos que se haya cerrado la conexión
        verify(mockConnection).close();
    }

    @Test
    void testGetURL() {

        when(mockProperties.getProperty(anyString())).thenReturn("mock-value");


        String actualURL = mySQLConnector.getURL();


        assertEquals("expected-url", actualURL);  // Actualiza con la URL esperada


        verify(mockProperties, times(13)).getProperty(anyString());
    }
}
