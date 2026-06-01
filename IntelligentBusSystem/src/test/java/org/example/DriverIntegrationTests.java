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
}