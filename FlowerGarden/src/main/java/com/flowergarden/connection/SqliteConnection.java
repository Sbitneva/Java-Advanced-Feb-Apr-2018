package com.flowergarden.connection;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private static Logger log = Logger.getLogger(SqliteBouquetDao.class.getName());

    private final String SQLITE_URL = "jdbc:sqlite:flowergarden.db";
    private Connection connection = null;

    public SqliteConnection() {
        this.connect();
    }


    private void connect() {
        try {
            this.connection = DriverManager.getConnection(SQLITE_URL);
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
