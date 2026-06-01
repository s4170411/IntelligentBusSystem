package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BusTests {
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
    @DisplayName("Bus Test Case 1 : Check BusID Valid")
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

    // Test case 3 : BusID All digits
    @Test
    @DisplayName("Bus Test Case 3 : Check BusID is all digits")
    void testAlphaNumericBusID() {
        assertThrows(IllegalArgumentException.class, () -> new Bus("8392a210", 50, 100.0, "Diesel"));
    }
    
    // Test case 4 : Capacity valid 
    @Test
    @DisplayName("Bus Test Case 4: Check valid busCapacity during update")
    void testValidDecreaseCapacity() {
        Bus updatedBus = new Bus("11112222", 45, 100.0, "Diesel");
        assertDoesNotThrow(() -> exampleBus.updateBus(updatedBus));
        assertEquals(45, exampleBus.getCapacity());
    }

    // Test case 5 : Capacity invalid
    @Test
    @DisplayName("Bus Test Case 5: Check invalid busCapacity during update")
    void testInvalidDecreaseCapacity() {
        Bus updatedBus = new Bus("11112222", 60, 100.0, "Diesel");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> exampleBus.updateBus(updatedBus));
        assertTrue(exception.getMessage().contains("Capacity cannot be increased"));
    }

    // Test case 6 : Check capacity negative number 
    @Test
    @DisplayName("Bus Test Case 6: Check negative busCapacity during update")
    void testNegativeCapacity() {
        assertThrows(IllegalArgumentException.class,
                () -> new Bus("11112222", -5, 100.0, "Diesel"));
    }

    @Test
    @DisplayName("Test case 7: Check for valid bus age restrictions (Age 35, Cap 55)")
    // Test case 7 : Check valid bus age restriction
    void testValidAgeRestriction() {
        Driver driverAge35 = createExampleDriver( "01-01-1991", 10, "Heavy");
        Bus largeBus = new Bus("33334444", 55, 100.0, "Diesel");
        assertDoesNotThrow(() -> largeBus.checkDriverBusRestrictions(driverAge35));
    }

    @Test
    @DisplayName("Test case 8: Check for invalid bus age restrictions (Age 55, Cap 67)")
    // Test case 8 : Check invalid bus age restriction
    void testInvalidAgeRestriction() {
        Driver driverAge55 = createExampleDriver("01-01-1971", 10, "Heavy");
        Bus largeBus = new Bus("33334444", 67, 100.0, "Diesel");
        assertThrows(IllegalArgumentException.class, () -> largeBus.checkDriverBusRestrictions(driverAge55));
    }

    @Test
    @DisplayName("Test case 9: Check for edge case bus age restrictions (Age 50, Cap 56)")
    // Test case 9 : Check edge case bus age restriction
    void testEdgeAgeRestriction() {
        Driver driverAge50 = createExampleDriver("01-01-1976", 10, "Heavy");
        Bus largeBus = new Bus("33334444", 56, 100.0, "Diesel");
        assertDoesNotThrow(() -> largeBus.checkDriverBusRestrictions(driverAge50));
    }

    @Test
    @DisplayName("Test case 10: Check for valid electric bus restrictions")
    // Test case 10 : Check electric valid driver experience restriction
    void testValidElectricRestriction() {
        Driver driverExperience6 = createExampleDriver("01-01-1990", 6, "Heavy");
        Bus electricBus = new Bus("33334444", 40, 100.0, "Electricity");
        assertDoesNotThrow(() -> electricBus.checkDriverBusRestrictions(driverExperience6));
    }

    @Test
    @DisplayName("Test case 11: Check for invalid electric bus restrictions")
    // Test case 11 : Check electric invalid driver experience restriction
    void testInvalidElectricRestriction() {
        Driver driverExperience1 = createExampleDriver("01-01-1990", 1, "Heavy");
        Bus electricBus = new Bus("33334444", 40, 100.0, "Electricity");
        assertThrows(IllegalArgumentException.class, () -> electricBus.checkDriverBusRestrictions(driverExperience1));
    }

    @Test
    @DisplayName("Test case 12: Check for valid electric bus edge case restrictions")
    // Test case 12 : Check electric valid driver experience edge case restriction
    void testEdgeCaseElectricRestriction() {
        Driver driverExperience5 = createExampleDriver("01-01-1990", 5, "Heavy");
        Bus electricBus = new Bus("33334444", 40, 100.0, "Electricity");
        assertDoesNotThrow(() -> electricBus.checkDriverBusRestrictions(driverExperience5));
    }

    @Test
    @DisplayName("Test case 13: Check valid license type restrictions for heavy, hybrid")
    // Test case 13 : Check valid license type restrictions for heavy, hybrid"
    void testHeavyLicenseHeavyHybrid() {
        Driver heavyDriver = createExampleDriver("01-01-1990", 10, "Heavy");
        Bus hybridBus = new Bus("77778888", 40, 100.0, "Hybrid");
        assertDoesNotThrow(() -> hybridBus.checkDriverBusRestrictions(heavyDriver));
    }

    @Test
    @DisplayName("Test case 14: Check invalid license type restrictions for light with electricity")
    // Test case 14 : Check invalid license type restrictions for light with electricity"
    void testHeavyLicenseLightElectric() {
        Driver lightDriver = createExampleDriver("01-01-1990", 10, "Light");
        Bus electricBus = new Bus("77778888", 40, 100.0, "Electricity");
        assertThrows(IllegalArgumentException.class, () -> electricBus.checkDriverBusRestrictions(lightDriver));
    }

    @Test
    @DisplayName("Test case 15: Check invalid license type restrictions for medium with hybrid")
    // Test case 15 : Check invalid license type restrictions for medium with hybrid"
    void testHeavyLicenseMediumHybrid() {
        Driver mediumDriver = createExampleDriver("01-01-1990", 10, "medium");
        Bus hybridBus = new Bus("77778888", 40, 100.0, "Hybrid");
        assertThrows(IllegalArgumentException.class, () -> hybridBus.checkDriverBusRestrictions(mediumDriver));
    }
    
    
}