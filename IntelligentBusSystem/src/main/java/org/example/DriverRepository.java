package org.example;

import java.util.ArrayList;

public class DriverRepository {
    private ArrayList<Driver> drivers = new ArrayList<>();

    public boolean add(Driver driver) {
        for (Driver d : drivers) {
            if (d.getDriverID().equals(driver.getDriverID())) {
                return false;
            }
        }
        drivers.add(driver);
        return true;
    }

    public Driver retrieve(String driverID) {
        for (Driver driver : drivers) {
            if (driver.getDriverID().equals(driverID)) {
                return driver;
            }
        }
        return null;
    }

    public boolean update(String driverID, Driver updatedDriver) {
        for (int i = 0; i < drivers.size(); i++) {
            Driver existing = drivers.get(i);
            if (existing.getDriverID().equals(driverID)) {
                if (!existing.getDriverID().equals(updatedDriver.getDriverID())) {
                    throw new IllegalArgumentException("Driver ID cannot be changed during an update.");
                }
                if (!existing.getName().equals(updatedDriver.getName())) {
                    throw new IllegalArgumentException("Driver name cannot be changed during an update.");
                }
                if (existing.getExperienceYears() > 10 && !existing.getLicenseType().equals(updatedDriver.getLicenseType())) {
                    throw new IllegalArgumentException("Cannot change license type for a driver with more than 10 years of experience.");
                }
                drivers.set(i, updatedDriver);
                return true;
            }
        }
        return false;
    }

    public int count() {
        return drivers.size();
    }
}
