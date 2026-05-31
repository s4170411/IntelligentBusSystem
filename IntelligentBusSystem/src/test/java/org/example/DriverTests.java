package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTests {

    private Driver exampleDriver;

    @BeforeEach
    void setUp() {
        exampleDriver = new Driver("23@#45pgAB", "John Smith", 12, "Heavy", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");;
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

    @Test
    @DisplayName("Driver Test Case 7 : Birthdate valid format")
    // Test case 7 : Birthdate valid format
    void testValidDriverBirthdate() {
        assertDoesNotThrow(() -> new Driver("23@#45pgAB", "Test Name", 5, "Light", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000"));
    }

    @Test
    @DisplayName("Driver Test Case 8 : Possible birthday, allowed to drive/alive")
    // Test case 8 : Possible birthday, allowed to drive/alive
    void testInvalidDriverBirthdate() {
        assertThrows(IllegalArgumentException.class, () -> new Driver("23@#45pgAB", "Test Name", 5, "Light", "10|Morrison|Melbourne|Victoria|Australia", "10-09-2026"));
    }

    @Test
    @DisplayName("Driver Test Case 9 : Invalid Birthday Separator")
    // Test case 9 : Invalid Birthday Separator
    void testInvalidDriverBirthdateSeparator() {
        assertThrows(IllegalArgumentException.class, () -> new Driver("23@#45pgAB", "Test Name", 5, "Light", "10,Morrison,Melbourne,Victoria,Australia", "10/08/2000"));
    }

    @Test
    @DisplayName("Driver Test Case 10 : Check if driver has more than 10 years experience, DOES NOT allow license type to be updated")
    // Test case 10 : Driver has more than 10 years experience, should not be allowed to update license type
    void testLicenseTypeRetrictChange() {
        Driver updatedDriver = new Driver("23@#45ABCD", "John Smith", 12, "Light", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");
        assertThrows(IllegalArgumentException.class, () -> exampleDriver.updateDriver(updatedDriver));
    }

    @Test
    @DisplayName("Driver Test Case 11 : Check if driver has less than 10 years experience, DOES allow license type to be updated")
    // Test case 11 : Driver has less than 10 years experience, is allowed to update license type
    void testLicenseTypeAllowChange() {
        Driver juniorDriver = new Driver("23@#45ABCD", "John Smith", 8, "Light", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");
        Driver updatedDriver = new Driver("23@#45ABCD", "John Smith", 8, "Medium", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");
        assertDoesNotThrow(() -> juniorDriver.updateDriver(updatedDriver));
        assertEquals("Medium", juniorDriver.getLicenseType());
    }

    @Test
    @DisplayName("Driver Test Case 12 : Check if driver has 10 years experience, DOES allow license type to be updated")
    // Test case 12 : Driver has 10 years experience, is allowed to update license type
    void testLicenseTypeAllowChangeDecade() {
        Driver tenYearDriver = new Driver("23@#45ABCD", "John Smith", 10, "Light", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");
        Driver updatedDriver = new Driver("23@#45ABCD", "John Smith", 10, "Medium", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");
        assertDoesNotThrow(() -> tenYearDriver.updateDriver(updatedDriver));
        assertEquals("Heavy", tenYearDriver.getLicenseType());
    }
    @Test
    @DisplayName("Test Case 13: Check that driverID cannot be modified during update operation")
    void testCase13_ImmutableDriverID() {
        Driver updatedDriver = new Driver("34@#67EFGH", "John Smith", 12, "Heavy", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");
        assertThrows(IllegalArgumentException.class, () -> exampleDriver.updateDriver(updatedDriver));
    }

}