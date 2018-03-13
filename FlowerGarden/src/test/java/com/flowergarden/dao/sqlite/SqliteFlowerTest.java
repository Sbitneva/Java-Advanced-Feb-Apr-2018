package com.flowergarden.dao.sqlite;

import com.flowergarden.connection.SqliteConnection;
import com.flowergarden.dao.DaoFactory;
import com.flowergarden.dao.FlowerDao;
import com.flowergarden.flowers.Flower;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SqliteFlowerTest {

    private static Logger log = Logger.getLogger(SqliteFlowerTest.class.getName());

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
            statement.executeUpdate("restore from flowergarden.test.db");
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


    private FlowerDao flowerDao = new DaoFactory().getSqliteFlowerDao();

    @Test
    public void getFlowersPricesForBouquetTest(){
        ArrayList<Float> flowerPrices = flowerDao.getFlowersPricesForBouquet(1);
        float price = 0;
        for(Float flowerPrice : flowerPrices) {
            price += flowerPrice;
        }

        assertEquals(6, flowerPrices.size());
        assertTrue(82.3f == price);
    }

    @Test
    public void getAllFlowersTest(){
        ArrayList<Flower> flowers = flowerDao.getAllFlowers();
        assertEquals(6, flowers.size());
    }
}
