package com.eii.datamanagement.dto;

import java.sql.Timestamp;

public class DataCollectionDto {
    private Long id;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Long fileIdOrders;
    private Long fileIdAssets;
    private Long fileIdInventory;
    private String status;
    private String tag;
    private String note;

    // Constructors
    public DataCollectionDto() {
    }

    public DataCollectionDto(Long id, Timestamp createdOn, Timestamp updatedOn, Long fileIdOrders, Long fileIdAssets, Long fileIdInventory, String status, String tag, String note) {
        this.id = id;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.fileIdOrders = fileIdOrders;
        this.fileIdAssets = fileIdAssets;
        this.fileIdInventory = fileIdInventory;
        this.status = status;
        this.tag = tag;
        this.note = note;
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getFileIdOrders() {
        return fileIdOrders;
    }

    public void setFileIdOrders(Long fileIdOrders) {
        this.fileIdOrders = fileIdOrders;
    }

    public Long getFileIdAssets() {
        return fileIdAssets;
    }

    public void setFileIdAssets(Long fileIdAssets) {
        this.fileIdAssets = fileIdAssets;
    }

    public Long getFileIdInventory() {
        return fileIdInventory;
    }

    public void setFileIdInventory(Long fileIdInventory) {
        this.fileIdInventory = fileIdInventory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "DataCollectionDTO{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", fileIdOrders=" + fileIdOrders +
                ", fileIdAssets=" + fileIdAssets +
                ", fileIdInventory=" + fileIdInventory +
                ", status='" + status + '\'' +
                ", tag='" + tag + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
