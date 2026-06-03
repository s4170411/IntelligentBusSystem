package org.example;

import java.util.List;

public class Main {
    public static void main() {
        System.out.println("------------ Local Demonstration/Testing ---------------");
        Bus testBus = new Bus("12345678", 50, 100.0, "Diesel");
        System.out.println("Starting bus tests...")

        BusRepository busRepo = new BusRepository("busRepo.json");
        System.out.println("Adding test bus 1...");
        busRepo.add(testBus);

        System.out.println("busRepo.json : ");
        List<Bus> buses = busRepo.retrieve();
        for (Bus bus : buses) {
            System.out.println("Bus ID: " + bus.getBusID());
            System.out.println("Bus Capacity: " + bus.getCapacity());
            System.out.println("Bus Fuel Type: " + bus.getFuelType());
            System.out.println("Bus Fuel Level: " + bus.getFuelLevel());
            System.out.println("------------------------------");
        }

        System.out.println("Updating test bus 1 to a lower capacity...");
        Bus updatedTestBus = new Bus("12345678", 40, 100.0, "Diesel");
        testBus.updateBus(updatedTestBus)
        busRepo.update(updatedTestBus);

        System.out.println("busRepo.json : ");
        buses = busRepo.retrieve();
        for (Bus bus : buses) {
            System.out.println("Bus ID: " + bus.getBusID());
            System.out.println("Bus Capacity: " + bus.getCapacity());
            System.out.println("Bus Fuel Type: " + bus.getFuelType());
            System.out.println("Bus Fuel Level: " + bus.getFuelLevel());
            System.out.println("------------------------------");
        }
        //Driver Tests
        Driver testDriver = new Driver("23@#45pgAB", "John Smith", 12, "Heavy", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");
        System.out.println("Starting driver tests...");
        
        DriverRespoistory driverRepo = new DriverRepositiory("driverRepo.json");
        System.out.println("Adding test driver 1...");

        driverRepo.add(testDriver);
        
        System.out.println("driverRepo.json : ");
        Driver retrievedDriver = driverRepo.retrieve("23@#45pgAB");

        System.out.println("Driver ID: " + retrievedDriver.getDriverID());
        System.out.println("Driver Name: " + retrievedDriver.getName());
        System.out.println("Driver Experience Years: " + retrievedDriver.getExperienceYears());
        System.out.println("Driver LicenseType: " + retrievedDriver.getLicenseType());
        System.out.println("Driver Address: " + retrievedDriver.getAddress());
        System.out.println("Driver Birthdate: " + retrievedDriver.getBirthddate());

        System.out.println("Updating test driver 1 to an increased experience...");
        Driver updatedTestDriver = new Driver("23@#45pgAB", "John Smith", 13, "Heavy", "10|Morrison|Melbourne|Victoria|Australia", "10-08-2000");
        testDriver.updateDriver(updatedTestDriver);
        driverRepo.update("23@#45pgAB", updateTestDriver); 
        
        System.out.println("--------- Updated Driver ---------");
        System.out.println("driverRepo.json : ");
        Driver retrievedDriver1 = driverRepo.retrieve("23@#45pgAB");

        System.out.println("Driver ID: " + retrievedDriver1.getDriverID());
        System.out.println("Driver Name: " + retrievedDriver1.getName());
        System.out.println("Driver Experience Years: " + retrievedDriver1.getExperienceYears());
        System.out.println("Driver LicenseType: " + retrievedDriver1.getLicenseType());
        System.out.println("Driver Address: " + retrievedDriver1.getAddress());
        System.out.println("Driver Birthdate: " + retrievedDriver1.getBirthddate());
        
    }
}
