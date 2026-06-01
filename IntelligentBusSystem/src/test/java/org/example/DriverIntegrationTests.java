package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DriverIntegrationTests {
    private DriverRepository repository;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        Path jsonPath = tempDir.resolve("driverRepo.json");
        repository = new DriverRepository(jsonPath.toString());
    }

    @Test
    @DisplayName("Driver Test Case 1: Add and successfully retrieve a valid Driver")
    // Test case 1 : Store and retrieve a valid driver
    void testAddAndRetrieveDriver() {
        Driver newDriver = new Driver("23@#45pgAB", "John Vale", 5, "Heavy", "10|King St|Melbourne|VIC|Australia", "10-10-1995");
        repository.add(newDriver);

        Driver retrieved = repository.retrieve(newDriver.getDriverID());
        assertNotNull(retrieved);
        assertEquals("John Vale", retrieved.getName());
    }

    @Test
    @DisplayName("Test Case 2: Reject invalid driver record")
    // Test case 2 : Reject an invalid driver record
    void testRejectInvalidDriver() {
  
        assertThrows(IllegalArgumentException.class, () -> {
            Driver invalidDriver = new Driver("1234", "John Vale", 5, "Heavy", "10|King St|Melbourne|VIC|Australia", "10-10-1995");
            repository.add(invalidDriver);
        });
    }

    @Test
    @DisplayName("Test Case 3: Updates are retained correctly")
    // Test case 3 : Updates retain changes
    void testUpdateDriverPersistence() {
        Driver originalDriver = new Driver("23@#45pgAB", "John Vale", 5, "Heavy", "12|King|Melbourne|Victoria|Australia", "10-10-1995");
        repository.add(originalDriver);

        Driver updatedDriver = new Driver("23@#45pgAB", "John Vale", 5, "Heavy", "20|Hallow|Melbourne|Victoria|Australia", "10-10-1995");

        repository.update("23@#45pgAB", updatedDriver);

        Driver retrieved = repository.retrieve("23@#45pgAB");
        assertEquals("20|Hallow|Melbourne|Victoria|Australia", retrieved.getAddress());
    }

    @Test
    @DisplayName("Test Case 4: Driver record counts are updated correctly")
    // Test case 4 : Count is updated properly
    void testDriverRecordCount() {
        assertEquals(0, repository.count());

        repository.add(new Driver("23@#45pgAB", "John Vale", 5, "Heavy", "10|King St|Melbourne|VIC|Australia", "10-10-1995"));

        assertEquals(1, repository.count());
    }
    
}