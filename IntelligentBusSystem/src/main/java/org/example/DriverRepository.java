package org.example;

import java.util.ArrayList;

public class DriverRepository {
    private ArrayList<Driver> drivers = new ArrayList<>();

    public void add(Driver driver) {
        drivers.add(driver);
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
            if (drivers.get(i).getDriverID().equals(driverID)) {
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
