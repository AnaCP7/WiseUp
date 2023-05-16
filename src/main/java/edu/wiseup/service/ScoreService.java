package edu.wiseup.service;

import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.manager.ScoreManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ScoreService {

    private MySQLConnector connector;

    private ScoreManager manager;

    public ScoreService(MySQLConnector connector, ScoreManager manager){
        this.connector = connector;
        this.manager = manager;
    }

    public List<Score> findAllScores()
            throws
            SQLException,
            ClassNotFoundException {

        try (Connection con = connector.getMySQLConnection()) {
            return manager.findAll(con);
        }
    }

    public Score findScoreById(int id)
            throws
            SQLException,
            ClassNotFoundException {

        try (Connection con = connector.getMySQLConnection()) {
            return manager.findById(con, id);
        }
    }
}