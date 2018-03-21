package com.flowergarden.dao.sqlite;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class SqliteFlowerTest {

    private static Logger log = Logger.getLogger(SqliteFlowerTest.class.getName());
    /*

    @Mock
    SqliteConnection sqliteConnection;

    @InjectMocks
    SqliteFlowerDao flowerDao;

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

    @Test
    public void addFlowerToBouquetTest(){

        ArrayList<Flower> flowers = flowerDao.getAllFlowers();
        Flower flower = flowers.get(2);
        int flowerId = ((GeneralFlower) flower).getFlowerId();
        int bouquetIdBefore = ((GeneralFlower)flower).getBouquetId();
        flowerDao.addFlowerToBouquet(flowerId, 0);
        assertNotEquals(bouquetIdBefore, 0);
        flowerDao.addFlowerToBouquet(flowerId, bouquetIdBefore);
    }

    @Test
    public void getBouquetFlowersTest(){
        ArrayList<Flower> flowers = flowerDao.getBouquetFlowers(1);
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

            int flowersAmountBefore = flowerDao.getAllFlowers().size();

            flowerDao.addFlower(chamomile);
            flowerDao.addFlower(rose);
            flowerDao.addFlower(tulip);
            flowerDao.addFlower(generalFlower);

            int flowersAmountAfter = flowerDao.getAllFlowers().size();

            assertEquals(flowersAmountBefore + 4, flowersAmountAfter);


            ArrayList<Flower> flowers = flowerDao.getAllFlowers();

            Flower flower1 = flowers.get(flowers.size() - 1);
            Flower flower2 = flowers.get(flowers.size() - 2);
            Flower flower3 = flowers.get(flowers.size() - 3);
            Flower flower4 = flowers.get(flowers.size() - 4);

            ((GeneralFlower)flower1).setLength(99);
            ((GeneralFlower)flower2).setLength(99);
            ((GeneralFlower)flower3).setLength(99);
            ((GeneralFlower)flower4).setLength(99);

            flowerDao.updateFlower(flower1);
            flowerDao.updateFlower(flower2);
            flowerDao.updateFlower(flower3);
            flowerDao.updateFlower(flower4);

            flowers = flowerDao.getAllFlowers();

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
    */

}
