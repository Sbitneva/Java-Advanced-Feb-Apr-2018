package com.flowergarden.dao.sqlite;

import com.flowergarden.connection.SqliteConnection;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.DaoFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SqliteBouquetTest {

    private static Logger log = Logger.getLogger(SqliteBouquetTest.class.getName());

    @Mock
    SqliteConnection sqliteConnection;

    @InjectMocks
    SqliteFlowerDao sqliteFlowerDao;

    Connection connection;

    @Before
    public void init(){
        try{
            when(sqliteConnection.getConnection()).thenReturn(DriverManager.getConnection("jdbc:sqlite:"));
            connection = sqliteConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("restore from flowergarden.db");
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @After
    public void after(){
        try {
            connection.close();
        } catch (SQLException e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @Test
    public void getAssemblePriceTest(){

            BouquetDao bouquetDao = new DaoFactory().getSqliteBouquetDao();

            float price = bouquetDao.getAssemblePrice(1);

            assertTrue(price == 12.7f);
    }
}
