package com.eii.datamanagement.repository;

import com.eii.datamanagement.dto.DataCollectionDto;
import com.eii.datamanagement.model.DataCollection;
import com.eii.datamanagement.model.DataFiles;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class DataFilesRepository {

    private final JdbcTemplate jdbcTemplate;

    public DataFilesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final class DataFileMapper implements RowMapper<DataFiles> {
        @Override
        public DataFiles mapRow(ResultSet rs, int rowNum) throws SQLException {
            DataFiles dataFile = new DataFiles();
            dataFile.setId(rs.getLong("id"));
            dataFile.setCreatedOn(rs.getTimestamp("created_on"));
            dataFile.setUpdatedOn(rs.getTimestamp("updated_on"));
            dataFile.setFileType(rs.getString("file_type"));
            dataFile.setValidationStatus(rs.getString("validation_status"));
            return dataFile;
        }
    }


    public List<DataFiles> getValidDataFiles(DataCollection dataCollection) {
        String sql = "SELECT * FROM eii_test.data_files WHERE " +
                "(id = ? OR id = ? OR id = ?) AND validation_status = 'valid'";
        return jdbcTemplate.query(
                sql,
                new DataFileMapper(),
                dataCollection.getFileIdOrders(),
                dataCollection.getFileIdAssets(),
                dataCollection.getFileIdInventory()
        );
    }

//    public int getValidDataFiles(DataCollectionDto dataCollection) {
//        String sql = "SELECT * FROM data_files";
//        return jdbcTemplate.query (
//                "SELECT df FROM eii_test.data_files df WHERE (id = ?1 OR id = ?2 OR id = ?3) AND df.validationStatus = 'valid'",
//                dataCollection.getFileIdOrders(),
//                dataCollection.getFileIdAssets(),
//                dataCollection.getFileIdInventory()
//        );
//    }
}




