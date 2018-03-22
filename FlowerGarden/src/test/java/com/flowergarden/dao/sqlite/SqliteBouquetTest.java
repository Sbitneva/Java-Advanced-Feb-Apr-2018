package com.flowergarden.dao.sqlite;

import com.flowergarden.TestConfiguration;
import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.BouquetBuilder;
import com.flowergarden.bouquet.MarriedBouquet;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class SqliteBouquetTest {

    private static Logger log = Logger.getLogger(SqliteBouquetTest.class.getName());

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqliteBouquetDao sqliteBouquetDao;

    @Before
    public void setUp() {

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("restore from flowergarden.test.db");
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @Test
    public void getAssemblePriceTest() {

        float price = sqliteBouquetDao.getAssemblePrice(1);

        assertTrue(price == 12.7f);
    }

    @Test
    public void bouquetsAmountShouldBeRaisedByOne() {

        Bouquet bouquet = new BouquetBuilder()
                .setAssemblePrice(90.0f)
                .setName("test_bouquet").getBouquet();

        ArrayList<Bouquet> bouquetsBefore = sqliteBouquetDao.getAllBouquets();

        sqliteBouquetDao.addBouquet(bouquet);

        ArrayList<Bouquet> bouquetsAfter = sqliteBouquetDao.getAllBouquets();

        assertEquals(bouquetsBefore.size() + 1, bouquetsAfter.size());
    }


    @Test
    public void bouquetsAmountShouldBeDecreasedByOne() {

        Bouquet bouquet = new BouquetBuilder().setAssemblePrice(90.0f)
                .setName("test bouquet for removing").getBouquet();

        int initialBouquetsAmount = sqliteBouquetDao.getAllBouquets().size();

        sqliteBouquetDao.addBouquet(bouquet);
        sqliteBouquetDao.deleteBouquet(initialBouquetsAmount + 1);

        int bouquetsAmountAfterDeletion = sqliteBouquetDao.getAllBouquets().size();

        assertEquals(initialBouquetsAmount, bouquetsAmountAfterDeletion);
    }

    @Test
    public void firstMarriedBouquetAsPriceShouldBeUpdated() {

        float priceBefore = sqliteBouquetDao.getAssemblePrice(1);

        sqliteBouquetDao.updateBouquetAssemblePrice(1, 6f);

        float priceAfter = sqliteBouquetDao.getAssemblePrice(1);

        assertFalse(priceBefore == priceAfter);
        assertTrue(6f == priceAfter);

        sqliteBouquetDao.updateBouquetAssemblePrice(1, priceBefore);

        assertTrue(sqliteBouquetDao.getAssemblePrice(1) == priceBefore);
    }

    @Test
    public void getBouquetTest() {
        Bouquet bouquet = sqliteBouquetDao.getBouquet(1);
        assertEquals(bouquet.getClass().getSimpleName(), MarriedBouquet.class.getSimpleName());
    }

}
