package com.eii.datamanagement.controller;

import com.eii.datamanagement.dto.DataCollectionDto;
import com.eii.datamanagement.model.DataCollection;
import com.eii.datamanagement.service.DataCollectionService;
import com.eii.datamanagement.exception.DataManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-collections")
public class DataCollectionController {

    @Autowired
    private DataCollectionService service;

    @GetMapping
    public ResponseEntity<List<DataCollection>> getAllDataCollections() {
        try {
            List<DataCollection> dataCollections = service.getAllDataCollections();
            return ResponseEntity.ok(dataCollections);
        } catch (DataManagementException e) {
            return ResponseEntity.status(e.getErrorCode()).body(null);
        } catch (Exception e) {
            // For unexpected exceptions
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addDataCollection(@RequestBody DataCollection dataCollection) {
        try {
            service.addDataCollection(dataCollection);
            return ResponseEntity.ok("Data Inserted to Data Collection successfully");
        } catch (DataManagementException e) {
            return ResponseEntity.status(e.getErrorCode()).body("Error while inserting into Data Collection: " + e.getUserMessage());
        } catch (Exception e) {
            // For unexpected exceptions
            return ResponseEntity.internalServerError().body("An error occurred while adding the data collection." + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDataCollection(@PathVariable Long id,
                                                  @RequestParam(required = false) Long fileIdOrders,
                                                  @RequestParam(required = false) Long fileIdAssets,
                                                  @RequestParam(required = false) Long fileIdInventory) {
        try {
            service.updateDataCollection(id, fileIdOrders, fileIdAssets, fileIdInventory);
            return ResponseEntity.ok("Data Collection updated successfully");
        } catch (DataManagementException e) {
            return ResponseEntity.status(e.getErrorCode()).body("Error updating Data Collection: " + e.getUserMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while updating the data collection." + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDataCollection(@PathVariable Long id) {
        try {
            service.deleteDataCollection(id);
            return ResponseEntity.ok("Data Deleted from Data Collection successfully");
        } catch (DataManagementException e) {
            return ResponseEntity.status(e.getErrorCode()).body("Error while Deleting Data Collection: " + e.getUserMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while deleting the data collection." + e.getMessage());
        }
    }
}
