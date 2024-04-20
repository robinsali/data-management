package com.eii.datamanagement.service;

import com.eii.datamanagement.model.DataCollection;
import com.eii.datamanagement.model.DataDeleted;
import com.eii.datamanagement.model.DataFiles;
import com.eii.datamanagement.repository.DataCollectionRepository;
import com.eii.datamanagement.repository.DataDeletedRepository;
import com.eii.datamanagement.repository.DataFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DataCollectionService {
    private final DataCollectionRepository dataCollectionRepository;

    private final DataFilesRepository dataFilesRepository;
    private final DataDeletedRepository dataDeletedRepository;

    @Autowired
    public DataCollectionService(DataCollectionRepository dataCollectionRepository, DataFilesRepository dataFilesRepository, DataDeletedRepository dataDeletedRepository) {
        this.dataCollectionRepository = dataCollectionRepository;
        this.dataFilesRepository = dataFilesRepository;
        this.dataDeletedRepository = dataDeletedRepository;
    }

    public List<DataCollection> getAllDataCollections() {
        return dataCollectionRepository.findAll();
    }

    public int addDataCollection(DataCollection dataCollection) {
        Long fileIdOrders = dataCollection.getFileIdOrders();
        Long fileIdAssets = dataCollection.getFileIdAssets();
        Long fileIdInventory = dataCollection.getFileIdInventory();

        // Check if the file IDs are unique
        ensureUniqueIds(List.of(fileIdOrders, fileIdAssets, fileIdInventory));
        List<DataFiles> validDataFiles = validateDataFiles(dataCollection);
        // Check file types are unique
        ensureUniqueFileTypes(validDataFiles);

        validateDataCollection(dataCollection);

        return dataCollectionRepository.addDataCollection(dataCollection);
    }

    public int deleteDataCollection(Long id) {
        boolean exists = dataCollectionRepository.existsById(id);
        if(!exists){
            throw new RuntimeException("Data Collection with id " +id+ " is not present");
        }

        DataDeleted dataDeleted = new DataDeleted();
        dataDeleted.setData_collection_id(id);

        boolean existsDelete = dataDeletedRepository.existsById(id);
        if (existsDelete){
            throw new RuntimeException("Data Collection with id " +id+ " is already deleted");
        }

        return dataDeletedRepository.addData(dataDeleted);
    }

    @Transactional
    public void updateDataCollection(Long id, Long fileIdOrders, Long fileIdAssets, Long fileIdInventory) {
        DataCollection existing = dataCollectionRepository.findById(id);
        if (existing == null) {
            throw new RuntimeException("Data Collection with id " + id + " does not exist");
        }

        System.out.println("First" + existing);
        boolean isUpdated = false;
        if (fileIdOrders != null && !fileIdOrders.equals(dataCollectionRepository.getFileIdOrders(id))) {
            existing.setFileIdOrders(fileIdOrders);
            isUpdated = true;
        }
        if (fileIdAssets != null && !fileIdAssets.equals(dataCollectionRepository.getFileIdAssets(id))) {
            existing.setFileIdAssets(fileIdAssets);
            isUpdated = true;
        }
        if (fileIdInventory != null && !fileIdInventory.equals(dataCollectionRepository.getFileIdInventory(id))) {
            existing.setFileIdInventory(fileIdInventory);
            isUpdated = true;
        }


        if (!isUpdated) {
            throw new RuntimeException("No update necessary; provided data matches existing data.");
        }
        System.out.println("Second" + existing);

        // Check if the file IDs are unique
        ensureUniqueIds(List.of(existing.getFileIdOrders(), existing.getFileIdAssets(), existing.getFileIdInventory()));
        List<DataFiles> validDataFiles = validateDataFiles(existing);
        ensureUniqueFileTypes(validDataFiles);

        validateDataCollection(existing);
        dataCollectionRepository.updateDataCollection(id, existing.getFileIdOrders(), existing.getFileIdAssets(), existing.getFileIdInventory());


    }

    private void ensureUniqueIds(List<Long> ids) {
        Set<Long> fileIds = new HashSet<>(ids);
        if (fileIds.size() < ids.size()) {
            throw new RuntimeException("IDs are not unique");
        }
    }

    //check for valid columns
    private List<DataFiles> validateDataFiles(DataCollection dataCollection) {
        List<DataFiles> validDataFiles = dataFilesRepository.getValidDataFiles(dataCollection);
        if (validDataFiles.size() < 3) {
            throw new RuntimeException("One or more data files id you entered doesn't exist or is invalid");
        }
        return validDataFiles;
    }

    //check unique file time
    private void ensureUniqueFileTypes(List<DataFiles> dataFiles) {
        Set<String> fileTypes = new HashSet<>();
        for (DataFiles file : dataFiles) {
            boolean added = fileTypes.add(file.getFileType());
            if (!added) {
                throw new RuntimeException("Duplicate file types found, all file types must be unique.");
            }
        }
    }

    private void validateDataCollection(DataCollection dataCollection) {
        if (dataCollection.getFileIdOrders() == null) {
            throw new IllegalArgumentException("File ID for orders cannot be null.");
        }
        if (dataCollection.getFileIdAssets() == null) {
            throw new IllegalArgumentException("File ID for assets cannot be null.");
        }
        if (dataCollection.getFileIdInventory() == null) {
            throw new IllegalArgumentException("File ID for inventory cannot be null.");
        }
        if (dataCollection.getStatus() == null || dataCollection.getStatus().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty.");
        }

        if (dataCollection.getStatus().length() > 20) {
            throw new IllegalArgumentException("Status exceeds maximum length of 20 characters.");
        }
        if (dataCollection.getTag() != null && dataCollection.getTag().length() > 50) {
            throw new IllegalArgumentException("Tag exceeds maximum length of 50 characters.");
        }
        if (dataCollection.getNote() != null && dataCollection.getNote().length() > 1000) {
            throw new IllegalArgumentException("Note exceeds maximum length of 1000 characters.");
        }

    }



}
