package com.flowergarden.dao.sqlite;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.connection.SqliteConnection;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.flowers.Flower;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqliteBouquetDao implements BouquetDao {

    private static Logger log = Logger.getLogger(SqliteBouquetDao.class.getName());
    private final String ADD_BOUQUET_REQUEST = "INSERT INTO bouquet VALUES (default, ?, ?)";
    private final String GET_ASSEMBLE_PRICE_REQUEST = "select assemble_price from bouquet where bouquet.id = ?";
    private final String GET_BOUQUET_REQUEST = "";
    private final String GET_ALL_BOUQUETS = "";
    private final String GET_ALL_BOUQUET_FLOWERS = "";
    private SqliteConnection sqliteConnection = null;

    public SqliteBouquetDao() {
        this.sqliteConnection = SqliteConnection.getSqliteConnection();
    }

    @Override
    public void addBouquet(String bouquetName) {

    }

    @Override
    public float getAssemblePrice(int bouquetId) {

        float assemblePrice = 0f;

        try {
            Connection connection = sqliteConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ASSEMBLE_PRICE_REQUEST);
            statement.setInt(1, bouquetId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                assemblePrice = resultSet.getFloat(1);
            }
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }

        return assemblePrice;
    }

    @Override
    public Bouquet getBouquet(int id) {
        return null;
    }

    @Override
    public ArrayList<Bouquet> getAllBouquets() {
        return null;
    }

    @Override
    public ArrayList<Flower> getBouquetFlowers(int bouquetId) {
        return null;
    }
}
