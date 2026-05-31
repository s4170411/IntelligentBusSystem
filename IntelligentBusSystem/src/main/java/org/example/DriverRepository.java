package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DriverRepository {

    private JSONArray loadDrivers() {
        File file = new File("driverRepo.json");
        if (!file.exists()) {
            return new JSONArray();
        }
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(file);
            return (JSONArray) parser.parse(reader);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private void saveDrivers(JSONArray jsonArray) {
        try {
            FileWriter file = new FileWriter("driverRepo.json");
            file.write(jsonArray.toJSONString());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean add(Driver driver) {
        JSONArray jsonArray = loadDrivers();
        for (Object obj : jsonArray) {
            JSONObject existing = (JSONObject) obj;
            if (existing.get("driverID").equals(driver.getDriverID())) {
                return false;
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("driverID", driver.getDriverID());
        jsonObject.put("name", driver.getName());
        jsonObject.put("experienceYears", driver.getExperienceYears());
        jsonObject.put("licenseType", driver.getLicenseType());
        jsonObject.put("address", driver.getAddress());
        jsonObject.put("birthdate", driver.getBirthdate());
        jsonArray.add(jsonObject);
        saveDrivers(jsonArray);
        return true;
    }

    public Driver retrieve(String driverID) {
        JSONArray jsonArray = loadDrivers();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            if (jsonObject.get("driverID").equals(driverID)) {
                return new Driver(
                    (String) jsonObject.get("driverID"),
                    (String) jsonObject.get("name"),
                    ((Long) jsonObject.get("experienceYears")).intValue(),
                    (String) jsonObject.get("licenseType"),
                    (String) jsonObject.get("address"),
                    (String) jsonObject.get("birthdate")
                );
            }
        }
        return null;
    }

    public boolean update(String driverID, Driver updatedDriver) {
        JSONArray jsonArray = loadDrivers();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject existing = (JSONObject) jsonArray.get(i);
            if (existing.get("driverID").equals(driverID)) {
                if (!existing.get("driverID").equals(updatedDriver.getDriverID())) {
                    throw new IllegalArgumentException("Driver ID cannot be changed during an update.");
                }
                if (!existing.get("name").equals(updatedDriver.getName())) {
                    throw new IllegalArgumentException("Driver name cannot be changed during an update.");
                }
                if (((Long) existing.get("experienceYears")).intValue() > 10 && !existing.get("licenseType").equals(updatedDriver.getLicenseType())) {
                    throw new IllegalArgumentException("Cannot change license type for a driver with more than 10 years of experience.");
                }
                JSONObject updatedObject = new JSONObject();
                updatedObject.put("driverID", updatedDriver.getDriverID());
                updatedObject.put("name", updatedDriver.getName());
                updatedObject.put("experienceYears", updatedDriver.getExperienceYears());
                updatedObject.put("licenseType", updatedDriver.getLicenseType());
                updatedObject.put("address", updatedDriver.getAddress());
                updatedObject.put("birthdate", updatedDriver.getBirthdate());
                jsonArray.set(i, updatedObject);
                saveDrivers(jsonArray);
                return true;
            }
        }
        return false;
    }

    public int count() {
        return loadDrivers().size();
    }
}
