package com.flowergarden.dao.sqlite;

import com.flowergarden.dao.FlowerDao;
import com.flowergarden.flowers.*;
import com.flowergarden.flowers.exceptions.FlowerException;
import com.flowergarden.properties.FreshnessInteger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
@Qualifier("sqliteFlowerDao")
public class SqliteFlowerDao implements FlowerDao {

    /**
     * SQL request for flower table
     */

    private static final String GET_ALL_FLOWERS = "select * from flower";
    private static final String GET_PRICES_REQUEST = "select price from flower where flower.bouquet_id = ?";
    private static final String ADD_FLOWER_REQUEST = "INSERT INTO flower" +
            " (name, lenght, freshness, price, petals, spike, bouquet_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_FLOWER_TO_BOUQUET_REQUEST = "UPDATE flower SET bouquet_id = ? where id = ?";
    private static final String DELETE_FLOWER_REQUEST = "DELETE from flower where id = ?";
    private static final String GET_BOUQUETS_FLOWERS = "select * from flower where bouquet_id = ?";
    private static final String UPDATE_FLOWER_REQUEST = "update flower SET" +
            " lenght = ?, freshness = ?, price = ?, petals =  ?, spike = ?, bouquet_id = ? where id = ?";
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
    private static Logger log = Logger.getLogger(SqliteFlowerDao.class.getName());

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<Flower> getAllFlowers() {

        ArrayList<Flower> flowers = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_FLOWERS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                try {
                    FlowersBuilder flowersBuilder = new FlowersBuilder();
                    Flower flower = flowersBuilder.setFlowerId(resultSet.getInt(ID))
                            .setName(resultSet.getString(NAME))
                            .setPrice(resultSet.getFloat(PRICE))
                            .setLength(resultSet.getInt(LENGTH))
                            .setFreshness(new FreshnessInteger(resultSet.getInt(FRESHNESS)))
                            .setPetals(resultSet.getInt(PETALS))
                            .setSpike(resultSet.getBoolean(SPIKE))
                            .setBouquetId(resultSet.getInt(BOUQUET_ID)).buildFlower();
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

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRICES_REQUEST)) {

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

    private void addChamomile(Flower flower) {
        Chamomile chamomile = (Chamomile) flower;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_FLOWER_REQUEST)) {

            statement.setString(1, "chamomile");
            statement.setInt(2, chamomile.getLength());
            statement.setInt(3, (chamomile.getFreshness()).getFreshness());
            statement.setFloat(4, chamomile.getPrice());
            statement.setInt(5, chamomile.getPetals());
            statement.setNull(6, 0);
            statement.setInt(7, chamomile.getBouquetId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void addRose(Flower flower) {
        Rose rose = (Rose) flower;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_FLOWER_REQUEST)) {

            statement.setString(1, "rose");
            statement.setInt(2, rose.getLength());
            statement.setInt(3, (rose.getFreshness()).getFreshness());
            statement.setFloat(4, rose.getPrice());
            statement.setBoolean(6, rose.getSpike());
            statement.setInt(7, rose.getBouquetId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    private void addTulip(Flower flower) {
        Tulip tulip = (Tulip) flower;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_FLOWER_REQUEST)) {

            statement.setString(1, "tulip");
            statement.setInt(2, tulip.getLength());
            statement.setInt(3, (tulip.getFreshness()).getFreshness());
            statement.setFloat(4, tulip.getPrice());
            statement.setInt(7, tulip.getBouquetId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }

    }

    private void addGeneralFlower(Flower flower) {
        GeneralFlower tulip = (GeneralFlower) flower;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_FLOWER_REQUEST)) {
            statement.setString(1, "general flower");
            statement.setInt(2, tulip.getLength());
            statement.setInt(3, (tulip.getFreshness()).getFreshness());
            statement.setFloat(4, tulip.getPrice());
            statement.setInt(7, tulip.getBouquetId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    /**
     * flower addition
     */

    @Override
    public void addFlower(Flower flower) {
        if (flower instanceof Chamomile) {
            addChamomile(flower);
        } else if (flower instanceof Rose) {
            addRose(flower);
        } else if (flower instanceof Tulip) {
            addTulip(flower);
        } else if (flower instanceof GeneralFlower) {
            addGeneralFlower(flower);
        }
    }

    /**
     * TODO: implementation flower deletion
     */

    @Override
    public void deleteFlower(int flowerId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FLOWER_REQUEST)) {

            statement.setInt(1, flowerId);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    /**
     * flower field edition for addition to bouquet
     */

    @Override
    public void addFlowerToBouquet(int flowerId, int bouquetId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_FLOWER_TO_BOUQUET_REQUEST)) {

            statement.setInt(1, bouquetId);
            statement.setInt(2, flowerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    /**
     * flowers extraction for one bouquet
     */

    @Override
    public ArrayList<Flower> getBouquetFlowers(int bouquetId) {

        ArrayList<Flower> flowers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BOUQUETS_FLOWERS)) {

            statement.setInt(1, bouquetId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                try {
                    Flower flower = new FlowersBuilder().setFlowerId(resultSet.getInt(ID))
                            .setLength(resultSet.getInt(LENGTH))
                            .setFreshness(new FreshnessInteger(resultSet.getInt(FRESHNESS)))
                            .setPrice(resultSet.getFloat(PRICE))
                            .setName(resultSet.getString(NAME))
                            .setPetals(resultSet.getInt(PETALS))
                            .setSpike(resultSet.getBoolean(SPIKE))
                            .setBouquetId(resultSet.getInt(BOUQUET_ID)).buildFlower();

                    flowers.add(flower);

                } catch (FlowerException fe) {
                    log.error(fe.getClass() + " : " + fe.getMessage());
                }
            }

        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }

        return flowers;
    }

    @Override
    public void updateFlower(Flower flower) {

        if (((GeneralFlower) flower).getFlowerId() > 0) {

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(UPDATE_FLOWER_REQUEST)) {

                statement.setInt(1, flower.getLength());
                statement.setInt(2, ((GeneralFlower) flower).getFreshness().getFreshness());
                statement.setFloat(3, flower.getPrice());
                statement.setInt(6, ((GeneralFlower) flower).getBouquetId());
                statement.setInt(7, ((GeneralFlower) flower).getFlowerId());
                if (flower instanceof Rose) {
                    statement.setObject(4, null);
                    statement.setBoolean(5, ((Rose) flower).getSpike());
                } else if (flower instanceof Chamomile) {
                    statement.setInt(4, ((Chamomile) flower).getPetals());
                    statement.setObject(5, null);
                } else {
                    statement.setObject(4, null);
                    statement.setObject(5, null);
                }
                statement.executeUpdate();
            } catch (SQLException e) {
                log.error(e.getClass() + " : " + e.getMessage());
            }

        }
    }
}
