package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.dao.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * La clase UserManager es responsable de gestionar las operaciones de búsqueda y gestión de usuarios en la base de datos.
 * Implementa la interfaz Findable para proporcionar métodos de búsqueda de usuarios.
 */
public class UserManager implements Findable<UserDAO> {
    /**
     * Recupera todos los usuarios de la base de datos.
     *
     * @param con La conexión a la base de datos.
     * @return Una lista de todos los usuarios encontrados.
     */
    @Override
    public List<UserDAO> findAll(Connection con) {
        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM user");

            List<UserDAO> users = new ArrayList<>();

            while (result.next()) {
                users.add(new UserDAO(result));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Recupera una lista de usuarios por sus identificadores.
     *
     * @param con La conexión a la base de datos.
     * @param ids Un conjunto de identificadores de usuario.
     * @return Una lista de usuarios encontrados cuyos identificadores están en el conjunto especificado.
     */
    public List<UserDAO> findAllByIds(Connection con, Set<Integer> ids) {
        String sql = String.format(
                "SELECT * "
                        + "FROM score a, user b "
                        + "WHERE a.id_user = b.id "
                        + "AND a.id IN (%s)",
                ids.stream().map(data -> "\"" + data + "\"").collect(Collectors.joining(", ")));

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);

            List<UserDAO> users = new ArrayList<>();

            while (result.next()) {
                Score score = new Score(result);
                UserDAO user = new UserDAO(result);
                score.setUser(user);
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Busca un usuario por su identificador.
     *
     * @param con La conexión a la base de datos.
     * @param id  El identificador del usuario.
     * @return El usuario encontrado, o null si no se encuentra ningún usuario con el identificador especificado.
     */
    @Override
    public UserDAO findById(Connection con, int id) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            UserDAO user = null;

            while (result.next()) {
                user = new UserDAO(result);
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param con      La conexión a la base de datos.
     * @param username El nombre de usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra ningún usuario con el nombre de usuario especificado.
     */
    public UserDAO findByUsername(Connection con, String username) {
        String sql = "SELECT * FROM user WHERE username LIKE '" + username + "'";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();

            UserDAO user = null;
            while (result.next()) {
                user = new UserDAO(result);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Agrega un nuevo usuario a la base de datos.
     *
     * @param con      La conexión a la base de datos.
     * @param username El nombre de usuario del nuevo usuario.
     * @param password La contraseña del nuevo usuario.
     */
    public void addUser(Connection con, String username, String password) {
        try (PreparedStatement stm = con.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?);")) {
            stm.setString(1, username);
            stm.setString(2, password);

            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
