package org.example;

import java.util.List;

public class Main {
    public static void main() {
        System.out.println("------------ Local Demonstration/Testing ---------------");
        Bus testBus = new Bus("12345678", 50, 100.0, "Diesel");

        BusRepository busRepo = new BusRepository();
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
        busRepo.update(new Bus("12345678", 45, 100.0, "Diesel"));

        System.out.println("busRepo.json : ");
        buses = busRepo.retrieve();
        for (Bus bus : buses) {
            System.out.println("Bus ID: " + bus.getBusID());
            System.out.println("Bus Capacity: " + bus.getCapacity());
            System.out.println("Bus Fuel Type: " + bus.getFuelType());
            System.out.println("Bus Fuel Level: " + bus.getFuelLevel());
            System.out.println("------------------------------");
        }
    }
}
