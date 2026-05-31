package org.example;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public void update(String busID) {

        // Same reader as "add" method
        JSONParser parser = new JSONParser();
        JSONArray busList = new JSONArray();
        try (FileReader fileRead = new FileReader("busRepo.json")) {
            busList = (JSONArray) parser.parse(fileRead);
        } catch (Exception e) {
            throw new RuntimeException("No buses exist to update");
        }



    }

    public void retrieve(Bus bus) {

    }
    
    public void count (Bus bus) {

    }
}
