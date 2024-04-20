package com.eii.datamanagement.model;


import java.sql.Timestamp;

public class DataFiles {
    private Long id;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private String fileType;
    private String validationStatus;

    public DataFiles(Long id, Timestamp createdOn, Timestamp updatedOn, String fileType, String validationStatus) {
        this.id = id;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.fileType = fileType;
        this.validationStatus = validationStatus;
    }

    public DataFiles(Timestamp createdOn, Timestamp updatedOn, String fileType, String validationStatus) {
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.fileType = fileType;
        this.validationStatus = validationStatus;
    }

    public DataFiles() {
    }

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
        return new Timestamp(System.currentTimeMillis());
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    @Override
    public String toString() {
        return "DataFiles{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", fileType='" + fileType + '\'' +
                ", validationStatus='" + validationStatus + '\'' +
                '}';
    }
}
