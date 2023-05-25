package edu.wiseup.persistence.connector;

/**
 * MySQLConnector es una clase que se utiliza para establecer una conexión con una base de datos MySQL.
 * Carga las propiedades de configuración desde el archivo "config.properties" y proporciona un método para obtener una conexión a la base de datos.
 */

import lombok.Getter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {

    @Getter
    private Properties prop = new Properties();

    /**
     * Constructor de MySQLConnector.
     * Carga las propiedades desde el archivo "config.properties" ubicado en el classpath del proyecto.
     */
    public MySQLConnector() {
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una conexión a la base de datos MySQL.
     *
     * @return La conexión establecida.
     * @throws ClassNotFoundException Si no se encuentra el controlador de la base de datos.
     * @throws SQLException           Si ocurre un error al establecer la conexión.
     */
    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        try {
            // Carga dinámicamente el controlador MySQL
            Class.forName(prop.getProperty(MySQLConstants.DRIVER));
            return DriverManager.getConnection(getURL());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Construye la URL de conexión a la base de datos MySQL utilizando las propiedades cargadas.
     *
     * @return La URL de conexión a la base de datos.
     */
    public String getURL() {
        // Ejemplo de URL: jdbc:mysql://localhost:3306/world?user=sa&password=12345678&useSSL=false;
        return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
                .append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
                .append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
                .append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
                .append(prop.getProperty(MySQLConstants.USER)).append("&password=")
                .append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
                .append(prop.getProperty(MySQLConstants.URL_SSL)).append(("&allowPublicKeyRetrieval="))
                .append(prop.getProperty(MySQLConstants.ALLOW_PUBLIC_KEY_RETRIEVAL)).append(("&useJDBCCompliantTimezoneShift="))
                .append(prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT)).append(("&useLegacyDatetimeCode="))
                .append(prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE)).append(("&serverTimezone="))
                .append(prop.getProperty(MySQLConstants.SERVER_TIMEZONE)).toString();
    }
/*
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLConnector connector = new MySQLConnector();
        Connection connection = connector.getMySQLConnection();
        System.out.println(connection.getCatalog());
        connection.close();
    }
*/

}
