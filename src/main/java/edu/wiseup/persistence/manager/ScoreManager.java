package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.dao.UserDAO;

import java.sql.*;
import java.time.Instant;
import java.util.*;

/**
 * La clase ScoreManager es responsable de gestionar las operaciones de búsqueda y gestión de puntajes en la base de datos.
 * Implementa la interfaz Findable para proporcionar métodos de búsqueda de puntajes.
 */
public class ScoreManager implements Findable<Score> {

    /**
     * Recupera todos los puntajes de la base de datos.
     *
     * @param con La conexión a la base de datos.
     * @return Una lista de todos los puntajes encontrados.
     */
    @Override
    public List<Score> findAll(Connection con) {
        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM question");

            List<Score> scores = new ArrayList<>();

            UserManager uman = new UserManager();
            Score score;

            while (result.next()) {
                score = new Score(result);
                score.setUser(uman.findById(con, result.getInt("id_user")));
                scores.add(score);
            }

            return scores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Busca un puntaje por su identificador.
     *
     * @param con La conexión a la base de datos.
     * @param id  El identificador del puntaje.
     * @return El puntaje encontrado, o null si no se encuentra ningún puntaje con el identificador especificado.
     */
    @Override
    public Score findById(Connection con, int id) {
        String sql =
                "SELECT * "
                        + "FROM score a, user b "
                        + "where a.id = ? "
                        + "and a.id_user = b.id";

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            Score score = null;

            while (result.next()) {
                score = new Score(result);
                UserDAO user = new UserDAO(result);
                score.setUser(user);
            }

            return score;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Agrega un puntaje a la base de datos.
     *
     * @param con     La conexión a la base de datos.
     * @param idUser  El identificador del usuario asociado al puntaje.
     * @param score   El puntaje a agregar.
     * @param date    La fecha del puntaje.
     */
    public void addScore(Connection con, int idUser, int score, Instant date) {
        String sql = "INSERT INTO score (id_user, score, date) VALUES (?, ?, ?);";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idUser);
            stm.setInt(2, score);
            stm.setTimestamp(3, Timestamp.from(date));

            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera todos los puntajes de la base de datos ordenados por puntaje descendente y fecha ascendente.
     *
     * @param con La conexión a la base de datos.
     * @return Una lista de puntajes encontrados, ordenados por puntaje descendente y fecha ascendente.
     */
    public List<Score> findAllSorted(Connection con) {
        String sql = "SELECT * FROM score ORDER BY score DESC, date ASC";

        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery(sql);

            List<Score> scores = new ArrayList<>();

            UserManager uman = new UserManager();
            Score score;

            while (result.next()) {
                score = new Score(result);
                score.setUser(uman.findById(con, result.getInt("id_user")));
                scores.add(score);
            }

            return scores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
