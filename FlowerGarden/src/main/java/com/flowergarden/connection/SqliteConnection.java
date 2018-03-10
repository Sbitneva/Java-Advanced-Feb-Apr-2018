package com.flowergarden.connection;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private static Logger log = Logger.getLogger(SqliteBouquetDao.class.getName());

    private static SqliteConnection sqliteConnection = new SqliteConnection();
    private final String SQLITE_URL = "jdbc:sqlite:flowergarden.db";
    private Connection connection = null;

    private SqliteConnection() {
        this.connect();
    }

    public static SqliteConnection getSqliteConnection() {
        return sqliteConnection;
    }

    private Connection connect() {

        try {
            connection = DriverManager.getConnection(SQLITE_URL);
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            sqliteConnection.getConnection().close();
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }
}
