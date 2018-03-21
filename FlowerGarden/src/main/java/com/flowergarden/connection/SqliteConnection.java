package com.flowergarden.connection;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private String sqliteUrl = "jdbc:sqlite:flowergarden.db";

    private static Logger log = Logger.getLogger(SqliteBouquetDao.class.getName());
    private static SqliteConnection sqliteConnection = new SqliteConnection("jdbc:sqlite:flowergarden.db");
    private Connection connection = null;

    private SqliteConnection(String sqliteUrl) {
        this.sqliteUrl = sqliteUrl;
        this.connect();
    }

    public static SqliteConnection getSqliteConnection() {
        return sqliteConnection;
    }

    private void connect() {
        try {
            this.connection = DriverManager.getConnection(this.sqliteUrl);
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
