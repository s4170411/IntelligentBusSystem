package org.example;

public class Main {
    public static void main() {
        System.out.println("------------ Local Demonstration/Testing ---------------");
        Bus testBus = new Bus("12345678", 50, 100.0, "Diesel");

        BusRepository busRepo = new BusRepository();
        System.out.println("Adding test bus 1...");
        busRepo.add(testBus);

        System.out.println("busRepo.json : ");
        System.out.println(busRepo.retrieve());
    }
}
