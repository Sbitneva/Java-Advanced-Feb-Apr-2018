package com.flowergarden.dao.sqlite;

import com.flowergarden.connection.SqliteConnection;
import com.flowergarden.dao.DaoFactory;
import com.flowergarden.dao.FlowerDao;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.FlowersBuilder;
import com.flowergarden.flowers.exceptions.FlowerException;
import com.flowergarden.properties.FreshnessInteger;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
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

    private FlowerDao flowerDao = new DaoFactory().getSqliteFlowerDao();

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

    /*@AfterClass
    public static void after(){
        try {
            SqliteConnection.getSqliteConnection().getConnection().close();
        } catch (SQLException e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }*/

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
    public void allTypesOfFlowersShouldBeAdded(){
        try {
            Flower chamomile = new FlowersBuilder().setName("chamomile")
                    .setLength(5)
                    .setFreshness(new FreshnessInteger(2))
                    .setPrice(12.5f)
                    .setPetals(20)
                    .setBouquetId(0).buildFlower();

            Flower rose = new FlowersBuilder().setName("rose")
                    .setLength(5)
                    .setFreshness(new FreshnessInteger(2))
                    .setPrice(12.5f)
                    .setSpike(true)
                    .setBouquetId(0).buildFlower();

            Flower tulip = new FlowersBuilder().setName("tulip")
                    .setLength(5)
                    .setFreshness(new FreshnessInteger(2))
                    .setPrice(12.5f)
                    .setBouquetId(0).buildFlower();

            Flower generalFlower = new FlowersBuilder().setName("lily")
                    .setLength(5)
                    .setFreshness(new FreshnessInteger(2))
                    .setPrice(12.5f)
                    .setBouquetId(0).buildFlower();

            int flowersAmountBefore = flowerDao.getAllFlowers().size();

            flowerDao.addFlower(chamomile);
            flowerDao.addFlower(rose);
            flowerDao.addFlower(tulip);
            flowerDao.addFlower(generalFlower);

            int flowersAmountAfter = flowerDao.getAllFlowers().size();

            assertEquals(flowersAmountBefore + 4, flowersAmountAfter);

        } catch (FlowerException e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @Test
    public void deleteFlowerTest(){
        try {
            Flower tulip = new FlowersBuilder().setName("tulip")
                    .setLength(5)
                    .setFreshness(new FreshnessInteger(2))
                    .setPrice(12.5f)
                    .setBouquetId(0).buildFlower();

            int flowersAmountBefore = flowerDao.getAllFlowers().size();

            flowerDao.addFlower(tulip);

            int flowerAmountAfterAddition = flowerDao.getAllFlowers().size();

            assertEquals(flowersAmountBefore + 1, flowerAmountAfterAddition);

            flowerDao.deleteFlower(flowerAmountAfterAddition);

            assertEquals(flowersAmountBefore, flowerDao.getAllFlowers().size());

        } catch (FlowerException e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

}
