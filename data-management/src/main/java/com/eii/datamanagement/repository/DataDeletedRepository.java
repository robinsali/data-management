package com.eii.datamanagement.repository;

import com.eii.datamanagement.dto.DataCollectionDto;
import com.eii.datamanagement.model.DataDeleted;
import com.eii.datamanagement.model.DataFiles;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Repository
public class DataDeletedRepository {
    private final JdbcTemplate jdbcTemplate;

    public DataDeletedRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM eii_test.data_deleted WHERE data_collection_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }


    private final class DataDeletedMapper implements RowMapper<DataDeleted> {
        @Override
        public DataDeleted mapRow(ResultSet rs, int rowNum) throws SQLException {
            DataDeleted dataDeleted = new DataDeleted();
            dataDeleted.setData_collection_id(rs.getLong("data_collection_id"));
            dataDeleted.setCreatedOn(rs.getTimestamp("created_on"));
            return dataDeleted;
        }
    }

    public int addData(DataDeleted dataDeleted) {
        return jdbcTemplate.update(
                "INSERT INTO eii_test.data_deleted (data_collection_id) VALUES (?)",
                dataDeleted.getData_collection_id()
        );
    }

}
