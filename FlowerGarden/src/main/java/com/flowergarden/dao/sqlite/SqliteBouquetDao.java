package com.flowergarden.dao.sqlite;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.BouquetBuilder;
import com.flowergarden.bouquet.CommonBouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.connection.SqliteConnection;
import com.flowergarden.dao.BouquetDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqliteBouquetDao implements BouquetDao {

    private static Logger log = Logger.getLogger(SqliteBouquetDao.class.getName());

    /**
     * SQL request for bouquet table
     */

    private static final String ADD_BOUQUET_REQUEST = "INSERT INTO bouquet  (name, assemble_price) VALUES(?, ?)";
    private static final String GET_ASSEMBLE_PRICE_REQUEST = "select assemble_price from bouquet where bouquet.id = ?";
    private static final String GET_BOUQUET_REQUEST = "SELECT * FROM bouquet where id = ?";
    private static final String GET_ALL_BOUQUETS = "SELECT * from bouquet";
    private static final String UPDATE_ASSEMBLE_PRICE = "UPDATE bouquet SET assemble_price = ? where id = ?";
    private static final String DELETE_BOUQUET = "DELETE from bouquet where id = ?";
    /**
     * Flower table columns names
     */

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ASSEMBLE_PRICE = "assemble_price";

    private SqliteConnection sqliteConnection = SqliteConnection.getSqliteConnection();

    public SqliteBouquetDao() {

    }

    @Override
    public void addBouquet(Bouquet bouquet) {

        try {
            Connection connection = sqliteConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(ADD_BOUQUET_REQUEST);

            if (bouquet.getClass().isInstance(MarriedBouquet.class)) {
                statement.setString(1, "married");
            } else {
                statement.setString(1, ((CommonBouquet) bouquet).getName());
            }
            statement.setFloat(2, ((MarriedBouquet) bouquet).getAssemblePrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @Override
    public float getAssemblePrice(int bouquetId) {

        float assemblePrice = 0f;

        try{
            Connection connection = sqliteConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(GET_ASSEMBLE_PRICE_REQUEST);
            statement.setInt(1, bouquetId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                assemblePrice = resultSet.getFloat(ASSEMBLE_PRICE);
            }
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }

        return assemblePrice;
    }

    /**
     * TODO: implementation of bouquet extraction
     */

    @Override
    public Bouquet getBouquet(int id) {

        try {
            Connection connection = sqliteConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(GET_BOUQUET_REQUEST);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new BouquetBuilder().setId(resultSet.getInt(ID))
                        .setAssemblePrice(resultSet.getFloat(ASSEMBLE_PRICE))
                        .setName(resultSet.getString(NAME)).getBouquet();

            }
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
        return null;
    }

    /**
     * TODO: implementation of all bouquets extraction
     */

    @Override
    public ArrayList<Bouquet> getAllBouquets() {

        ArrayList<Bouquet> bouquets = new ArrayList<>();

        try {
            Connection connection = sqliteConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(GET_ALL_BOUQUETS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Bouquet bouquet = new BouquetBuilder().setId(resultSet.getInt(ID))
                        .setAssemblePrice(resultSet.getFloat(ASSEMBLE_PRICE))
                        .setName(resultSet.getString(NAME)).getBouquet();
                bouquets.add(bouquet);
            }

        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
        return bouquets;
    }

    @Override
    public void updateBouquetAssemblePrice(int bouquetId, float assemblePrice) {

        try {
            Connection connection = sqliteConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(UPDATE_ASSEMBLE_PRICE);
            statement.setFloat(1, assemblePrice);
            statement.setInt(2, bouquetId);

            statement.executeUpdate();

        }catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @Override
    public void deleteBouquet(int id) {

        try {
            Connection connection = sqliteConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(DELETE_BOUQUET);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }
}
