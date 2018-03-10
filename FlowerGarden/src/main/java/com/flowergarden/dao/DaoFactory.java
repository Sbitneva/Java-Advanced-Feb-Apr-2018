package com.flowergarden.dao;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import com.flowergarden.dao.sqlite.SqliteFlowerDao;

public class DaoFactory {

    private static SqliteBouquetDao sqliteBouquetDao = new SqliteBouquetDao();
    private static SqliteFlowerDao sqliteFlowerDao = new SqliteFlowerDao();
    private static DaoFactory daoFactory = new DaoFactory();

    public static SqliteBouquetDao getSqliteBouquetDao() {
        return sqliteBouquetDao;
    }

    public static SqliteFlowerDao getSqliteFlowerDao() {
        return sqliteFlowerDao;
    }

    public static DaoFactory getDaoFactory() {
        return daoFactory;
    }
}
