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
    // Generate a valid driver
    private Driver createExampleDriver(String birthdate, int experience, String licenseType) {
        return new Driver("463#$456AZ", "Test Driver", experience, licenseType, "1|Example St|City|State|Country", birthdate);
    }

    @Test
    // Test case 1 : Valid
    @DisplayName("Bus Test Case 1 : Check BusID Valid")
    void testValidBusID() {
        assertDoesNotThrow(() -> new Bus("12345678", 35, 60.0, "Hybrid"));
    }
    
}