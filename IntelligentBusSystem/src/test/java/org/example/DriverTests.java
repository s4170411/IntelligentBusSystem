package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTests {

    private Driver exampleDriver;

    @BeforeEach
    void setUp() {
        exampleDriver = new Driver("23@#45pgAB", "John Smith", 12, "Heavy", "10|RMIT|Melbourne|Victoria|Australia", "01-01-2000");;
    }


    @Test
    @DisplayName("Driver Test Case 1 : Valid Driver ID")
    // Test case 1 : Valid
    void testValidDriverID() {
        assertDoesNotThrow(() -> new Driver("23@#45pgAB", "Test Name", 5, "Light", "10|Street|City|State|Country", "10-10-1999"));
    }

    @Test
    @DisplayName("Driver Test Case 2 : Short Driver ID")
    // Test case 2 : Short ID
    void testShortDriverID() {
        assertThrows(IllegalArgumentException.class, () -> new Driver("23@#45A", "Test Name", 5, "Light", "10|Street|City|State|Country", "10-10-1999"));
    }

    @Test
    @DisplayName("Driver Test Case 3 : Lowercase last 2 characters Driver ID")
    // Test case 3 : Lowercase last 2 characters Driver ID
    void testLowercaseDriverID() {
        assertThrows(IllegalArgumentException.class, () -> new Driver("23@#45ab", "Test Name", 5, "Light", "10|Street|City|State|Country", "10-10-1999"));
    }

    @Test
    @DisplayName("Driver Test Case 4 : Valid Driver Address")
        // Test case 4 : Check for valid driver address
    void testValidDriverAddress() {
        assertDoesNotThrow(() -> new Driver("23@#45pgAB", "Test Name", 5, "Light", "10|Morrison|Melbourne|Victoria|Australia", "10-10-1999"));
    }

    @Test
    @DisplayName("Driver Test Case 5 : Invalid Sections Address")
    // Test case 5 : Invalid Sections Address
    void testInvalidDriverAddressSections() {
        assertThrows(IllegalArgumentException.class, () -> new Driver("23@#45pgAB", "Test Name", 5, "Light", "10|Melbourne|Australia", "10-10-1999"));
    }

    @Test
    @DisplayName("Driver Test Case 6 : Invalid Sections Separator")
    // Test case 6 : Invalid Sections Separator
    void testInvalidDriverAddressSeparator() {
        assertThrows(IllegalArgumentException.class, () -> new Driver("23@#45pgAB", "Test Name", 5, "Light", "10,Morrison,Melbourne,Victoria,Australia", "10-10-1999"));
    }


}