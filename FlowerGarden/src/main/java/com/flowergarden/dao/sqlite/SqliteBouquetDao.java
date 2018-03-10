package com.flowergarden.dao.sqlite;

import com.flowergarden.bouquet.Bouquet;
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

    private static final String ADD_BOUQUET_REQUEST = "INSERT INTO bouquet VALUES (default, ?, ?)";
    private static final String GET_ASSEMBLE_PRICE_REQUEST = "select assemble_price from bouquet where bouquet.id = ?";
    private static final String GET_BOUQUET_REQUEST = "";
    private static final String GET_ALL_BOUQUETS = "";
    private static final String GET_ALL_BOUQUET_FLOWERS = "";

    /**
     * Flower table columns names
     */

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ASSEMBLE_PRICE = "assemble_price";

    private SqliteConnection sqliteConnection = null;

    public SqliteBouquetDao() {
        this.sqliteConnection = new SqliteConnection();
    }

    @Override
    public void addBouquet(String bouquetName) {

    }

    @Override
    public float getAssemblePrice(int bouquetId) {

        float assemblePrice = 0f;
        try (Connection connection = sqliteConnection.getConnection()){
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
        return null;
    }

    /**
     * TODO: implementation of all bouquets extraction
     */

    @Override
    public ArrayList<Bouquet> getAllBouquets() {
        return null;
    }

}
