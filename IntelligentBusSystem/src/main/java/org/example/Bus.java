package org.example;

public class Bus {
    private String busID;
    private int capacity;
    private double fuelLevel;
    private String fuelType; // Diesel, Hybrid, Electricity

    // s4170411 -> Last updated 27th May
    public Bus(String busID, int capacity, double fuelLevel, String fuelType) {
        setBusID(busID);
        this.capacity = capacity;
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
            throw new IllegalArgumentException("Bus ID must be 8 numbers and not null");
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
}
