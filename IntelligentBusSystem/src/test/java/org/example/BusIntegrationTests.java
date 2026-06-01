package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class BusIntegrationTests {
    private BusRepository repository;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        Path jsonPath = tempDir.resolve("busRepo.json");
        repository = new BusRepository(jsonPath.toString());
    }

    @Test
    @DisplayName("Bus Test Case 1 : Store and retrieve bus")
    // Test case 1 : Store the bus, and retrieve it
    void testBusStoreRetrieve() {
        Bus newBus = new Bus("94683370", 40, 80.0, "Diesel");
        repository.add(newBus);

        Bus retrieved = repository.retrieve("94683370");
        assertNotNull(retrieved);
        assertEquals(40, retrieved.getCapacity());
        assertEquals("Diesel", retrieved.getFuelType());
    }
    @Test
    @DisplayName("Bus Test Case 2: Reject invalid bus record")
    // Test case 2 : Reject and invalid bus record
    void testRejectInvalidBus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Bus invalidBus = new Bus("123", 40, 80.0, "Diesel");
            repository.add(invalidBus);
        });
    }
    @Test
    @DisplayName("Bus Test Case 3: Updates are persisted correctly")
    // Test case 3 : 
    void testUpdateBusPersistence() {
        Bus originalBus = new Bus("94683370", 40, 80.0, "Diesel");
        repository.add(originalBus);

        Bus updatedBus = new Bus("94683370", 40, 40.0, "Diesel");
        repository.update(updatedBus);

        Bus retrieved = repository.retrieve("94683370");
        assertEquals(40.0, retrieved.getFuelLevel());
    }

    @Test
    @DisplayName("Bus Test Case 4: Bus record counts are updated correctly")
    void testBusRecordCount() {
        assertEquals(0, repository.retrieve().size());

        repository.add(new Bus("94683370", 40, 80.0, "Diesel"));

        assertEquals(1, repository.retrieve().size());
    }
    
}