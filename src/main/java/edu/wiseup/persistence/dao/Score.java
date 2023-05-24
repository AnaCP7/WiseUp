package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

/**
 * La clase Score representa un puntaje en el sistema.
 * Contiene campos para el identificador del puntaje, el usuario asociado, el puntaje obtenido y la fecha y hora del puntaje.
 * También incluye un constructor que permite crear un objeto Score a partir de un ResultSet obtenido de la base de datos.
 */
@Data
public class Score {
    // Campos del puntaje
    private int id;                 // Identificador del puntaje
    private UserDAO user;           // Usuario asociado al puntaje
    private int score;              // Puntaje obtenido
    private Instant date;           // Fecha y hora del puntaje

    /**
     * Constructor vacío de la clase Score.
     */
    public Score() {

    }

    /**
     * Constructor de la clase Score que crea un objeto Score a partir de un ResultSet obtenido de la base de datos.
     *
     * @param result El ResultSet que contiene los datos del puntaje.
     */
    public Score(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.score = result.getInt("score");
            String strDate = result.getString("date");

            if (strDate != null) {
                // Se formatea y convierte la cadena de fecha en un objeto Instant
                this.date = Instant.parse(strDate.substring(0, 10) + "T" + strDate.substring(11) + ".00Z");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
