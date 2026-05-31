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
        throw new IllegalArgumentException(driverID + " does not exist in the repository.");
    }

    public boolean update(String driverID, Driver updatedDriver) {
        JSONArray jsonArray = loadDrivers();
        boolean driverFound = false;
        for (Object obj : jsonArray) {
            JSONObject jsonDriver = (JSONObject) obj;
            if (jsonDriver.get("driverID").equals(driverID)) {
                Driver currentDriver = new Driver(
                    (String) jsonDriver.get("driverID"),
                    (String) jsonDriver.get("name"),
                    ((Long) jsonDriver.get("experienceYears")).intValue(),
                    (String) jsonDriver.get("licenseType"),
                    (String) jsonDriver.get("address"),
                    (String) jsonDriver.get("birthdate")
                );
                currentDriver.updateDriver(updatedDriver);
                jsonDriver.put("experienceYears", currentDriver.getExperienceYears());
                jsonDriver.put("licenseType", currentDriver.getLicenseType());
                jsonDriver.put("address", currentDriver.getAddress());
                jsonDriver.put("birthdate", currentDriver.getBirthdate());
                driverFound = true;
                break;
            }
        }
        if (driverFound) {
            saveDrivers(jsonArray);
            return true;
        }
        return false;
    }

    public int count() {
        return loadDrivers().size();
    }
}
