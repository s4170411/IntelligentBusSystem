package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTests {
    private Bus exampleBus;

    @BeforeEach
    void setUp() {
        exampleBus = new Bus("11112222", 50, 100.0, "Diesel");
    }
    // Generate a valid driver
    private Driver createExampleDriver(String birthdate, int experience, String licenseType) {
        return new Driver("463#$456AZ", "Test Driver", experience, licenseType, "1|Example St|City|State|Country", birthdate);
    }

    @Test
    // Test case 1 : Valid
    void testValidBusID() {
        assertDoesNotThrow(() -> new Bus("12345678", 35, 60.0, "Hybrid"));
    }

    
    
}