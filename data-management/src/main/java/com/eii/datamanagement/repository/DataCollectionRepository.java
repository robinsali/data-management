package com.eii.datamanagement.repository;

import com.eii.datamanagement.model.DataCollection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataCollectionRepository {
    private final JdbcTemplate jdbcTemplate;

    public DataCollectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM eii_test.data_collections WHERE id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    public DataCollection findById(Long id) {
        try {
            String sql = "SELECT * FROM data_collections WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new DataCollectionMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int updateDataCollection(Long id, Long fileIdOrders, Long fileIdAssets, Long fileIdInventory) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("UPDATE eii_test.data_collections SET ");

        if (fileIdOrders != null) {
            sql.append("file_id_orders = ?, ");
            params.add(fileIdOrders);
        }
        if (fileIdAssets != null) {
            sql.append("file_id_assets = ?, ");
            params.add(fileIdAssets);
        }
        if (fileIdInventory != null) {
            sql.append("file_id_inventory = ?, ");
            params.add(fileIdInventory);
        }

        // Remove the trailing comma and space
        int lastCommaIndex = sql.lastIndexOf(",");
        if (lastCommaIndex != -1) {
            sql.delete(lastCommaIndex, sql.length());
        }

        sql.append(" WHERE id = ?");
        params.add(id);

        // Execute the update
        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    public Long getFileIdOrders(Long id) {
        String sql = "SELECT file_id_orders from eii_test.data_collections where id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, id);
    }

    public Long getFileIdAssets(Long id) {
        String sql = "SELECT file_id_assets from eii_test.data_collections where id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, id);
    }

    public Long getFileIdInventory(Long id) {
        String sql = "SELECT file_id_inventory from eii_test.data_collections where id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, id);
    }

    private static final class DataCollectionMapper implements RowMapper<DataCollection> {
        @Override
        public DataCollection mapRow(ResultSet rs, int rowNum) throws SQLException {
            DataCollection dc = new DataCollection(rs.getInt("id"), rs.getInt("file_id_orders"), rs.getInt("file_id_assets"), rs.getInt("file_id_inventory"), rs.getString("status"), rs.getString("tag"), rs.getString("note"));
            dc.setId(rs.getLong("id"));
            dc.setCreatedOn(rs.getTimestamp("created_on"));
            dc.setUpdatedOn(rs.getTimestamp("updated_on"));
            dc.setFileIdOrders(rs.getLong("file_id_orders"));
            dc.setFileIdAssets(rs.getLong("file_id_assets"));
            dc.setFileIdInventory(rs.getLong("file_id_inventory"));
            dc.setStatus(rs.getString("status"));
            dc.setTag(rs.getString("tag"));
            dc.setNote(rs.getString("note"));
            return dc;
        }
    }


        public List<DataCollection> findAll() {
            return jdbcTemplate.query("SELECT * FROM eii_test.data_collections dc WHERE dc.id NOT IN (SELECT dd.data_collection_id FROM eii_test.data_deleted dd)", new DataCollectionMapper());
        }

    public int addDataCollection(DataCollection dataCollection) {
        return jdbcTemplate.update(
                "INSERT INTO eii_test.data_collections (updated_on, file_id_orders, file_id_assets, file_id_inventory, status, tag, note) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new Timestamp(System.currentTimeMillis()),
                dataCollection.getFileIdOrders(),
                dataCollection.getFileIdAssets(),
                dataCollection.getFileIdInventory(),
                dataCollection.getStatus(),
                dataCollection.getTag(),
                dataCollection.getNote()
        );
    }




}
