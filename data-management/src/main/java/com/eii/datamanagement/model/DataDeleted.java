package com.eii.datamanagement.model;

import java.sql.Timestamp;

public class DataDeleted {
    private Long data_collection_id;
    private Timestamp createdOn;

    public DataDeleted(Long data_collection_id, Timestamp createdOn) {
        this.data_collection_id = data_collection_id;
        this.createdOn = createdOn;
    }

    public DataDeleted() {
    }

    public Long getData_collection_id() {
        return data_collection_id;
    }

    public void setData_collection_id(Long data_collection_id) {
        this.data_collection_id = data_collection_id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "DataDeleted{" +
                "data_collection_id=" + data_collection_id +
                ", createdOn=" + createdOn +
                '}';
    }
}
