package com.flowergarden.dao.sqlite;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.BouquetBuilder;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.connection.SqliteConnection;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.DaoFactory;
import org.apache.log4j.Logger;
import org.junit.*;
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
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SqliteBouquetTest {

    private static Logger log = Logger.getLogger(SqliteBouquetTest.class.getName());

    @Mock
    private SqliteConnection sqliteConnection;

    @InjectMocks
    private SqliteBouquetDao bouquetDao;

    @Before
    public void  init(){
        try{
            when(sqliteConnection.getConnection()).thenReturn(DriverManager.getConnection("jdbc:sqlite:"));
            Connection connection = sqliteConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("restore from flowergarden.test.db");
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @AfterClass
    public static void after(){
        try {
            SqliteConnection.getSqliteConnection().getConnection().close();
        } catch (SQLException e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }



    @Test
    public void getAssemblePriceTest(){

        float price = bouquetDao.getAssemblePrice(1);

        assertTrue(price == 12.7f);
    }

    @Test
    public void bouquetsAmountShouldBeRaisedByOne(){

        Bouquet bouquet = new BouquetBuilder().setAssemblePrice(90.0f)
                            .setName("test_bouquet").getBouquet();

        ArrayList<Bouquet> bouquetsBefore = bouquetDao.getAllBouquets();

        bouquetDao.addBouquet(bouquet);

        ArrayList<Bouquet> bouquetsAfter = bouquetDao.getAllBouquets();

        assertEquals(bouquetsBefore.size() + 1, bouquetsAfter.size());
    }


    @Test
    public void bouquetsAmountShouldBeDecreasedByOne(){

        Bouquet bouquet = new BouquetBuilder().setAssemblePrice(90.0f)
                .setName("test bouquet for removing").getBouquet();

        int initialBouquetsAmount = bouquetDao.getAllBouquets().size();

        bouquetDao.addBouquet(bouquet);

        bouquetDao.deleteBouquet(initialBouquetsAmount + 1);
        int bouquetsAmountAfterDeletion = bouquetDao.getAllBouquets().size();
        assertEquals(initialBouquetsAmount, bouquetsAmountAfterDeletion);
    }

    @Test
    public void firstMarriedBouquetAsPriceShouldBeUpdated(){

        float priceBefore = bouquetDao.getAssemblePrice(1);

        bouquetDao.updateBouquetAssemblePrice(1, 6f);

        float priceAfter = bouquetDao.getAssemblePrice(1);

        assertFalse(priceBefore == priceAfter);
        assertTrue(6f == priceAfter);

        bouquetDao.updateBouquetAssemblePrice(1, priceBefore);

        assertTrue(bouquetDao.getAssemblePrice(1) == priceBefore );
    }

    @Test
    public void getBouquetTest(){
        Bouquet bouquet = bouquetDao.getBouquet(1);
        assertEquals(bouquet.getClass().getSimpleName() , MarriedBouquet.class.getSimpleName());
    }

}
