package com.flowergarden.dao;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import com.flowergarden.dao.sqlite.SqliteFlowerDao;

public class DaoFactory {

    public SqliteBouquetDao getSqliteBouquetDao() {
        return new SqliteBouquetDao();
    }

    public SqliteFlowerDao getSqliteFlowerDao() {
        return new SqliteFlowerDao();
    }

}
