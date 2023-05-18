/*package edu.wiseup.persistence.connector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
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
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        mySQLConnector = spy(new MySQLConnector());
        doReturn(mockProperties).when(mySQLConnector).getProp();

        // Cargar propiedades de configuración desde un archivo de prueba
        InputStream input = getClass().getClassLoader().getResourceAsStream("test-config.properties");
        mockProperties.load(input);
    }

    @Test
    void testGetMySQLConnection() throws SQLException, ClassNotFoundException {
        // Configurar el comportamiento del mock
        when(mockProperties.getProperty(anyString())).thenReturn("mock-value");
        when(mockConnection.getCatalog()).thenReturn("mock-catalog");

        // Simular el registro del controlador para evitar la excepción ClassNotFoundException
        doNothing().when(mySQLConnector).registerDriver();

        // Simular la obtención de la conexión
        doReturn(mockConnection).when(mySQLConnector).getConnection();

        // Ejecutar el método que queremos probar
        Connection actualConnection = mySQLConnector.getMySQLConnection();

        // Verificar que se hayan llamado los métodos necesarios
        verify(mySQLConnector).registerDriver();
        verify(mySQLConnector).getConnection();

        // Verificar el resultado
        assertEquals("mock-catalog", actualConnection.getCatalog());

        // Verificar que se haya cerrado la conexión
        verify(mockConnection).close();
    }

    @Test
    void testGetURL() {
        when(mockProperties.getProperty(anyString())).thenReturn("mock-value");

        String actualURL = mySQLConnector.getURL();

        // Actualizar con la URL esperada
        String expectedURL = "expected-url";

        assertEquals(expectedURL, actualURL);

        // Verificar que se hayan llamado los métodos getProperty correctamente
        verify(mockProperties, times(13)).getProperty(anyString());
    }
}
*/