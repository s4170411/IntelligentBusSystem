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
    @DisplayName("Bus Test Case 1 : store and retrieve bus")
    void testBusStoreRetrieve() {
        Bus newBus = new Bus("94683370", 40, 80.0, "Diesel");
        repository.add(newBus);

        Bus retrieved = repository.retrieve("94683370");
        assertNotNull(retrieved);
        assertEquals(40, retrieved.getCapacity());
        assertEquals("Diesel", retrieved.getFuelType());
    }
    
}