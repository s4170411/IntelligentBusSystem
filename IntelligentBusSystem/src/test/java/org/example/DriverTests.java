package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTests {

    private Driver exampleDriver;

    @BeforeEach
    void setUp() {
        exampleDriver = new Driver("23@#45ABCD", "John Smith", 12, "Heavy", "10|RMIT|Melbourne|Victoria|Australia", "01-01-2000");;
    }


    @Test
    @DisplayName("Driver Test Case 1 : Valid Driver ID")
    // Test case 1 : Valid
    void testValidDriverID() {
        assertDoesNotThrow(() -> new Driver("23@#64psAB", "Test Name", 5, "Light", "10|Street|City|State|Country", "10-10-1999"));
    }

    
}