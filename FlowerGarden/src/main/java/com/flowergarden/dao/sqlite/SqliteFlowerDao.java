package com.flowergarden.dao.sqlite;

import com.flowergarden.connection.SqliteConnection;
import com.flowergarden.dao.FlowerDao;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.FlowersBuilder;
import com.flowergarden.flowers.exceptions.FlowerException;
import com.flowergarden.properties.FreshnessInteger;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqliteFlowerDao implements FlowerDao {

    private static Logger log = Logger.getLogger(SqliteFlowerDao.class.getName());

    /**
     * SQL request for flower table
     */

    private static final String GET_ALL_FLOWERS = "select * from flower";
    private static final String GET_PRICES_REQUEST = "select price from flower where flower.bouquet_id = ?";
    private static final String ADD_FLOWER_REQUEST = "";
    private static final String DELETE_FLOWER_REQUEST = "";
    private static final String ADD_FLOWER_TO_BOUQUET_REQUEST = "";

    /**
     * Flower table columns names
     */

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LENGTH = "lenght";
    private static final String FRESHNESS = "freshness";
    private static final String PRICE = "price";
    private static final String PETALS = "petals";
    private static final String SPIKE = "price";
    private static final String BOUQUET_ID = "bouquet_id";

    private SqliteConnection sqliteConnection = null;

    public SqliteFlowerDao() {
        this.sqliteConnection = new SqliteConnection();
    }

    @Override
    public ArrayList<Flower> getAllFlowers() {

        ArrayList<Flower> flowers = new ArrayList<>();

        try (Connection connection = sqliteConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_ALL_FLOWERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                try {
                    FlowersBuilder flowersBuilder = new FlowersBuilder();
                    Flower flower = flowersBuilder.setName(resultSet.getString(NAME))
                            .setPrice(resultSet.getFloat(PRICE))
                            .setLength(resultSet.getInt(LENGTH))
                            .setFreshness(new FreshnessInteger(resultSet.getInt(FRESHNESS)))
                            .setPetals(resultSet.getInt(PETALS))
                            .setSpike(resultSet.getBoolean(SPIKE)).buildFlower();
                    flowers.add(flower);
                } catch (FlowerException e) {
                    log.error(e.getClass() + " : " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }

        return flowers;
    }

    @Override
    public ArrayList<Float> getFlowersPricesForBouquet(int bouquetIndex) {
        ArrayList<Float> prices = new ArrayList<>();
        try (Connection connection = sqliteConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_PRICES_REQUEST);
            statement.setInt(1, bouquetIndex);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                prices.add(resultSet.getFloat(PRICE));
            }
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }

        return prices;
    }

    /**
     * TODO: implementation flower addition
     */

    @Override
    public void addFlower(Flower flower) {

    }

    /**
     * TODO: implementation flower deletion
     */

    @Override
    public void deleteFlower(int flowerId) {

    }

    /**
     * TODO: implementation flower field edition for addition to bouquet
     */

    @Override
    public void addFlowerToBouquet(Flower flower) {

    }

    /**
     * TODO: implementation of flowers extraction for one bouquet
     */

    @Override
    public ArrayList<Flower> getBouquetFlowers(int bouquetId) {
        return null;
    }
}
