package com.flowergarden.dao.sqlite;

import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.DaoFactory;
import org.junit.Test;
import org.junit.Rule;
import org.testcontainers.containers.GenericContainer;

import static junit.framework.TestCase.assertTrue;

public class SqliteBouquetTest {

    @Test
    public void getAssemblePriceTest(){
        BouquetDao bouquetDao = DaoFactory.getSqliteBouquetDao();

        float price = bouquetDao.getAssemblePrice(1);

        assertTrue(price == 12.7f);
    }
}
