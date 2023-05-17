package edu.wiseup.persistence.connector;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MySQLConnectorTest {

    @Mock
    private Properties mockProperties;

    private MySQLConnector mySQLConnector;

    @Test
    void testGetURL() {
        MockitoAnnotations.openMocks(this);

        mySQLConnector = new MySQLConnector();
        mySQLConnector.setProp(mockProperties);

        // Configuración del mock
        when(mockProperties.getProperty(anyString())).thenReturn("mock-value");

        // Ejecutamos el método que queremos probar
        String actualURL = mySQLConnector.getURL();

        // Verificamos el resultado
        assertEquals("expected-url", actualURL);  // Actualiza con la URL esperada

        // Verificamos que se hayan llamado los métodos necesarios
        verify(mockProperties).getProperty("jdbc.mysql.url.prefix");
        verify(mockProperties).getProperty("jdbc.mysql.url.host");
        verify(mockProperties).getProperty("jdbc.mysql.url.port");
        verify(mockProperties).getProperty("jdbc.mysql.url.schema");
        verify(mockProperties).getProperty("jdbc.mysql.user");
        verify(mockProperties).getProperty("jdbc.mysql.passwd");
        verify(mockProperties).getProperty("jdbc.mysql.url.ssl");
        verify(mockProperties).getProperty("jdbc.mysql.url.allowPublicKeyRetrieval");
        verify(mockProperties).getProperty("jdbc.mysql.url.useJDBCCompliantTimezoneShift");
        verify(mockProperties).getProperty("jdbc.mysql.url.useLegacyDatetimeCode");
        verify(mockProperties).getProperty("jdbc.mysql.url.serverTimezone");
    }
}
