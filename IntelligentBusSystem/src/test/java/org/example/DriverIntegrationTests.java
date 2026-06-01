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

    

}