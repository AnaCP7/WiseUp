package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase UserDAO representa un usuario en el sistema.
 * Contiene campos para el identificador del usuario, nombre de usuario y contraseña.
 * También incluye un constructor que permite crear un objeto UserDAO a partir de un ResultSet obtenido de la base de datos.
 */
@Data
public class UserDAO {
    // Campos del usuario
    private int id;                 // Identificador del usuario
    private String username;        // Nombre de usuario
    private String password;        // Contraseña

    /**
     * Constructor vacío de la clase UserDAO.
     */
    public UserDAO() {

    }

    /**
     * Constructor de la clase UserDAO que toma el identificador y el nombre de usuario como parámetros.
     *
     * @param id       El identificador del usuario.
     * @param username El nombre de usuario.
     */
    public UserDAO(int id, String username) {

    }

    /**
     * Constructor de la clase UserDAO que crea un objeto UserDAO a partir de un ResultSet obtenido de la base de datos.
     *
     * @param result El ResultSet que contiene los datos del usuario.
     */
    public UserDAO(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.username = result.getString("username");
            this.password = result.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
