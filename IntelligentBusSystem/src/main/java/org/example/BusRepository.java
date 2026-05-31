package org.example;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class BusRepository {
    // Add (), Update (), Retrieve (), Count () functions
    public void add(Bus bus) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("busID", bus.getBusID());
        jsonObject.put("capacity", bus.getCapacity());
        jsonObject.put("fuelType", bus.getFuelType());
        jsonObject.put("fuelLevel", bus.getFuelLevel());
       
        try {
            FileWriter file = new FileWriter("busRepo.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
