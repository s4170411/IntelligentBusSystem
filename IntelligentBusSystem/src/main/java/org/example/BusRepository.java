package org.example;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BusRepository {

    // Add (), Update (), Retrieve (), Count () functions

    // Grab list, add, re-write to JSON
    public void add(Bus bus) {
        JSONParser parser = new JSONParser();
        JSONArray busList = new JSONArray();
        try (FileReader fileRead = new FileReader("busRepo.json")) {
            busList = (JSONArray) parser.parse(fileRead);
        } catch (Exception e) {
            // Proceed with empty list
        }
        
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("busID", bus.getBusID());
        jsonObject.put("capacity", bus.getCapacity());
        jsonObject.put("fuelType", bus.getFuelType());
        jsonObject.put("fuelLevel", bus.getFuelLevel());

        busList.add(jsonObject);
        try(FileWriter file = new FileWriter("busRepo.json")) {
            file.write(busList.toJSONString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save bus to JSON", e);
        }

    }

    public void update(Bus updatedBus) {
        // Same reader as "add" method
        JSONParser parser = new JSONParser();
        JSONArray busList = new JSONArray();
        boolean busFound = false;

        try (FileReader fileRead = new FileReader("busRepo.json")) {
            busList = (JSONArray) parser.parse(fileRead);
        } catch (Exception e) {
            throw new RuntimeException("No buses exist to update");
        }

        for (Object obj : busList) {
            JSONObject jsonBus = (JSONObject) obj;

            if (jsonBus.get("busID").equals(updatedBus.getBusID())) {
                // Use Bus.java update method to catch test cases
                Bus currentBus = new Bus((String) jsonBus.get("busID"), ((Long) jsonBus.get("capacity")).intValue(), ((Number) jsonBus.get("fuelLevel")).doubleValue(), (String) jsonBus.get("fuelType"));
                currentBus.updateBus(updatedBus);

                jsonBus.put("capacity", currentBus.getCapacity());
                jsonBus.put("fuelType", currentBus.getFuelType());
                jsonBus.put("fuelLevel", currentBus.getFuelLevel());

                busFound = true;
                break;

            }
        }

        if (!busFound) {
            throw new IllegalArgumentException(updatedBus.getBusID() + " does not exist in JSON");
        }

        try (FileWriter file = new FileWriter("busRepo.json")){
            file.write(busList.toJSONString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save update to JSON", e);
        }
    }


    // Method overload, either returns all buses or specified ID
    public List<Bus> retrieve() {
        JSONParser parser = new JSONParser();
        JSONArray busArray = new JSONArray();
        List<Bus> busList = new ArrayList<>();
        try (FileReader fileRead = new FileReader("busRepo.json")) {
            busArray = (JSONArray) parser.parse(fileRead);
        } catch (Exception e) {
            // Proceed with empty list
        }
        for (Object obj : busArray) {
            JSONObject jsonBus = (JSONObject) obj;

            String busID = (String) jsonBus.get("busID");
            int capacity = ((Long) jsonBus.get("capacity")).intValue();
            double fuelLevel = ((Number) jsonBus.get("fuelLevel")).doubleValue();
            String fuelType = (String) jsonBus.get("fuelType");

            // Add the translated Java object to our list
            busList.add(new Bus(busID, capacity, fuelLevel, fuelType));
        }
        return busList;
    }
    public Bus retrieve(String busID) {
            
        JSONParser parser = new JSONParser();
        JSONArray busArray = new JSONArray();

        try (FileReader fileRead = new FileReader("busRepo.json")) {
            busArray = (JSONArray) parser.parse(fileRead);
        } catch (Exception e) {
            // Proceed with empty list
        }
        for (Object obj : busArray) {
            JSONObject jsonBus = (JSONObject) obj;
            if (jsonBus.get("busID").equals(busID)) {

                int capacity = ((Long) jsonBus.get("capacity")).intValue();
                double fuelLevel = ((Number) jsonBus.get("fuelLevel")).doubleValue();
                String fuelType = (String) jsonBus.get("fuelType");

                return new Bus(busID, capacity, fuelLevel, fuelType);
            }
        }
        throw new IllegalArgumentException(busID + " does not exist in JSON");


    }

    // return count of buses
    public int count () {
        JSONParser parser = new JSONParser();
        JSONArray busList = new JSONArray();
        try (FileReader fileRead = new FileReader("busRepo.json")) {
            busList = (JSONArray) parser.parse(fileRead);
        } catch (Exception e) {
            // Proceed with empty list
        }
        return busList.size();
    }
}   
