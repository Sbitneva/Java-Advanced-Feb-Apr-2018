package com.flowergarden.dao.sqlite;

import com.flowergarden.TestConfiguration;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.FlowersBuilder;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.exceptions.FlowerException;
import com.flowergarden.properties.FreshnessInteger;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class SqliteFlowerTest {

    private static Logger log = Logger.getLogger(SqliteFlowerTest.class.getName());

    @Mock
    @Autowired
    private DataSource dataSource;

    @InjectMocks
    @Autowired
    SqliteFlowerDao sqliteFlowerDao;

    @Before
    public void init() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("restore from flowergarden.test.db");
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @Test
    public void getFlowersPricesForBouquetTest(){
        ArrayList<Float> flowerPrices = sqliteFlowerDao.getFlowersPricesForBouquet(1);
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

            int flowersAmountBefore = sqliteFlowerDao.getAllFlowers().size();

            sqliteFlowerDao.addFlower(chamomile);
            sqliteFlowerDao.addFlower(rose);
            sqliteFlowerDao.addFlower(tulip);
            sqliteFlowerDao.addFlower(generalFlower);

            int flowersAmountAfter = sqliteFlowerDao.getAllFlowers().size();

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

            int flowersAmountBefore = sqliteFlowerDao.getAllFlowers().size();

            sqliteFlowerDao.addFlower(tulip);

            int flowerAmountAfterAddition = sqliteFlowerDao.getAllFlowers().size();

            assertEquals(flowersAmountBefore + 1, flowerAmountAfterAddition);

            sqliteFlowerDao.deleteFlower(flowerAmountAfterAddition);

            assertEquals(flowersAmountBefore, sqliteFlowerDao.getAllFlowers().size());

        } catch (FlowerException e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @Test
    public void addFlowerToBouquetTest(){

        ArrayList<Flower> flowers = sqliteFlowerDao.getAllFlowers();
        Flower flower = flowers.get(2);
        int flowerId = ((GeneralFlower) flower).getFlowerId();
        int bouquetIdBefore = ((GeneralFlower)flower).getBouquetId();
        sqliteFlowerDao.addFlowerToBouquet(flowerId, 0);
        assertNotEquals(bouquetIdBefore, 0);
        sqliteFlowerDao.addFlowerToBouquet(flowerId, bouquetIdBefore);
    }

    @Test
    public void getBouquetFlowersTest(){
        ArrayList<Flower> flowers = sqliteFlowerDao.getBouquetFlowers(1);
        assertEquals(flowers.size(), 6);
    }

    @Test
    public void updateAllTypesOfFlowers(){
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

            int flowersAmountBefore = sqliteFlowerDao.getAllFlowers().size();

            assertEquals(((Chamomile)chamomile).getPetals(), 20);
            assertEquals(((Chamomile)chamomile).getPetal(), true);

            sqliteFlowerDao.addFlower(chamomile);
            sqliteFlowerDao.addFlower(rose);
            sqliteFlowerDao.addFlower(tulip);
            sqliteFlowerDao.addFlower(generalFlower);

            int flowersAmountAfter = sqliteFlowerDao.getAllFlowers().size();

            assertEquals(flowersAmountBefore + 4, flowersAmountAfter);

            ArrayList<Flower> flowers = sqliteFlowerDao.getAllFlowers();

            Flower flower1 = flowers.get(flowers.size() - 1);
            Flower flower2 = flowers.get(flowers.size() - 2);
            Flower flower3 = flowers.get(flowers.size() - 3);
            Flower flower4 = flowers.get(flowers.size() - 4);

            ((GeneralFlower)flower1).setLength(99);
            ((GeneralFlower)flower2).setLength(99);
            ((GeneralFlower)flower3).setLength(99);
            ((GeneralFlower)flower4).setLength(99);

            sqliteFlowerDao.updateFlower(flower1);
            sqliteFlowerDao.updateFlower(flower2);
            sqliteFlowerDao.updateFlower(flower3);
            sqliteFlowerDao.updateFlower(flower4);

            flowers = sqliteFlowerDao.getAllFlowers();

            for(Flower flower : flowers) {
                if(((GeneralFlower)flower).getFlowerId() == ((GeneralFlower)flower1).getFlowerId()){
                    assertEquals(flower.getLength(), 99);
                }
                if(((GeneralFlower)flower).getFlowerId() == ((GeneralFlower)flower2).getFlowerId()){
                    assertEquals(flower.getLength(), 99);
                }
                if(((GeneralFlower)flower).getFlowerId() == ((GeneralFlower)flower3).getFlowerId()){
                    assertEquals(flower.getLength(), 99);
                }
                if(((GeneralFlower)flower).getFlowerId() == ((GeneralFlower)flower4).getFlowerId()){
                    assertEquals(flower.getLength(), 99);
                }
            }

        } catch (FlowerException e){
            log.error(e.getClass() + " : " + e.getMessage());
        }

    }


}
