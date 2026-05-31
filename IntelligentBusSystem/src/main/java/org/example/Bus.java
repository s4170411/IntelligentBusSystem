package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Bus {
    private String busID;
    private int capacity;
    private double fuelLevel;
    private String fuelType; // Diesel, Hybrid, Electricity

    // s4170411 -> Last updated 27th May
    public Bus(String busID, int capacity, double fuelLevel, String fuelType) {
        setBusID(busID);
        setCapacity(capacity);
        this.fuelLevel = fuelLevel;
        setFuelType(fuelType);
    }

    // Getters

    public String getBusID() {
        return busID;
    }
    public int getCapacity() {
        return capacity;
    }
    public double getFuelLevel() {
        return fuelLevel;
    }
    public String getFuelType() {
        return fuelType;
    }
    // Capacity setter

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be non-negative number");
        }
        this.capacity = capacity;
    }
    // Fuel type setter
    public void setFuelType(String fuelType) {
        if (!fuelType.matches("^(Diesel|Hybrid|Electricity)$")) {
            throw new IllegalArgumentException("Not a fuel type.");
        }
        this.fuelType = fuelType;
    }

    // Bus Conditions

    //B1 : Unique and = 8 char long
    public void setBusID(String busID) {
        // regex check to ensure 8 chars
        if (busID == null || !busID.matches("^\\d{8}$")) {
            throw new IllegalArgumentException("Bus ID must be exactly 8 numbers and not null");
        }
        // Learnt that "else" is not needed as JVM terminates as "return" after "throw"
        this.busID = busID;

    }

    // B2 : Capacity, allow decrease during operations, not increase
    // To stick to proper OOP, take a new temporary bus (updatedBus), and use it to update an existing bus
    public void updateBus(Bus updatedBus) {
        this.capacity = updatedBus.getCapacity();
        this.fuelLevel = updatedBus.getFuelLevel();
        this.setFuelType(updatedBus.getFuelType());
    }

    // B3, B4, B5
    public void checkDriverBusRestrictions(Driver driver){
        if (driver == null) {
            throw new IllegalArgumentException("Driver must exist.");
        }

        // B3 : Age restriction
        // Could just expect the format, substring last four chars, for completeness’s sake though, run it through a datetime parser
        String dateTimeStrBirthdate = driver.getBirthdate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");

        LocalDate driverBirthDate = LocalDate.parse(dateTimeStrBirthdate, formatter);

        // Get today
        LocalDate currentDate = LocalDate.now();

        long age = ChronoUnit.YEARS.between(driverBirthDate, currentDate);
        
        if (age > 50 && this.capacity >= 50) {
            throw new IllegalArgumentException("Drivers over 50 cannot drive a bus with a capacity of 50 or more");
        }

        // B4 : Electric, experience >= 5
        if (this.fuelType.equals("Electricity") && driver.getExperienceYears() < 5) {
            throw new IllegalArgumentException("Drivers must have at least 5 years of experience to drive an electric bus");
        }

        // B5 : Heavy or public transport are permitted to operate electric/hybrid
        if (this.fuelType.equals("Electricity") || this.fuelType.equals("Hybrid")) {
            String license = driver.getLicenseType();
            if (!license.equals("Heavy") && !license.equals("PublicTransport")) {
                throw new IllegalArgumentException("A Heavy or Public Transport license is required for Electric/Hybrid buses");
            }
        }

        
    }
}
