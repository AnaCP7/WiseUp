package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Question;
import edu.wiseup.persistence.dao.Score;

import java.sql.Connection;
import java.util.List;

/**
 * La interfaz Findable define los métodos para buscar entidades en la base de datos.
 *
 * @param <T> El tipo de entidad que se desea buscar.
 */
public interface Findable<T> {
    /**
     * Obtiene una lista de todas las entidades del tipo especificado.
     *
     * @param connection La conexión a la base de datos.
     * @return Una lista de todas las entidades encontradas.
     */
    List<T> findAll(Connection connection);

    /**
     * Busca una entidad por su identificador.
     *
     * @param connection La conexión a la base de datos.
     * @param id         El identificador de la entidad.
     * @return La entidad encontrada, o null si no se encuentra ninguna entidad con el identificador especificado.
     */
    T findById(Connection connection, int id);
}
