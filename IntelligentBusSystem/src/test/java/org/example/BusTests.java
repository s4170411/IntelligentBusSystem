package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusTests {
    private Bus exampleBus;

    @BeforeEach
    void setUp() {
        exampleBus = new Bus("11112222", 50, 100.0, "Diesel");
    }
    // Generate a valid driver
    private Driver createExampleDriver(String birthdate, int experience, String licenseType) {
        return new Driver("54!DASJDD", "Test Driver", experience, licenseType, "1|Example St|City|State|Country", birthdate);
    }
    @Test
    // Test case 1 : Valid
    void testValidBusID() {
        assertDoesNotThrow(() -> new Bus("12345678", 35, 60.0, "Hybrid"));
    }
    // Test case 2 : 8 Characters
    @Test
    @DisplayName("Bus Test Case 2 : Check BusID 8 Characters")
    void testShortBusID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Bus("4839482", 50, 100.0, "Diesel"));
        assertTrue(exception.getMessage().contains("exactly 8 numbers"));

    }


}