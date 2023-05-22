package edu.wiseup.persistence.manager;

import edu.wiseup.persistence.dao.Question;
import edu.wiseup.persistence.dao.Score;

import java.sql.Connection;
import java.util.List;

public interface Findable<T> {
    List<T> findAll(Connection connection);
    T findById(Connection connection, int id);
}
