package edu.wiseup.persistence.dao;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase Question representa una pregunta en el sistema.
 * Contiene campos para el identificador, texto de la pregunta, opciones de respuesta, respuesta correcta y categoría.
 * También incluye un constructor que permite crear un objeto Question a partir de un ResultSet obtenido de la base de datos.
 */
@Data
public class Question {
    // Campos de la pregunta
    private int id;                 // Identificador de la pregunta
    private String question;        // Texto de la pregunta
    private String image;           // Imagen asociada a la pregunta
    private String optionA;         // Opción A de respuesta
    private String optionB;         // Opción B de respuesta
    private String optionC;         // Opción C de respuesta
    private String optionD;         // Opción D de respuesta
    private char answer;            // Respuesta correcta
    private String category;        // Categoría de la pregunta

    /**
     * Constructor vacío de la clase Question.
     */
    public Question() {

    }

    /**
     * Constructor de la clase Question que toma el identificador y el texto de la pregunta como parámetros.
     * Este constructor está marcado como "TODO BORRARSE AL CORREGIR LOS TESTS" y probablemente se elimine en el futuro.
     *
     * @param i El identificador de la pregunta.
     * @param s El texto de la pregunta.
     */
    public Question(int i, String s) {

    }

    /**
     * Constructor de la clase Question que crea un objeto Question a partir de un ResultSet obtenido de la base de datos.
     *
     * @param result El ResultSet que contiene los datos de la pregunta.
     */
    public Question(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.question = result.getString("question");
            this.image = result.getString("image");
            this.optionA = result.getString("option_a");
            this.optionB = result.getString("option_b");
            this.optionC = result.getString("option_c");
            this.optionD = result.getString("option_d");
            this.answer = result.getString("answer").charAt(0);
            this.category = result.getString("category");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
