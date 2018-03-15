package com.flowergarden.connection;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private static Logger log = Logger.getLogger(SqliteBouquetDao.class.getName());

    private final static String SQLITE_URL = "jdbc:sqlite:flowergarden.db";
    private Connection connection = null;

    private SqliteConnection() {
        this.connect();
    }

    private static SqliteConnection sqliteConnection = new SqliteConnection();

    private void connect() {
        try {
            this.connection = DriverManager.getConnection(SQLITE_URL);
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    public static SqliteConnection getSqliteConnection() {
        return sqliteConnection;
    }

    public  Connection getConnection() {
        return connection;
    }

}
