package org.example;

public class Driver {
    private String driverID;
    private String name;
    private int experienceYears;
    private String licenseType; // Light, Medium, Heavy, PublicTransport
    private String address;
    private String birthdate;

    public Driver(String driverID, String name, int experienceYears, String licenseType, String address, String birthdate) {
        setDriverID(driverID);
        this.name = name;
        this.experienceYears = experienceYears;
        this.licenseType = licenseType;
        this.address = address;
        this.birthdate = birthdate;
    }

    public String getDriverID() { 
        return driverID; 
    }
    
    public void setDriverID(String driverID) {
        if (driverID == null || driverID.length() != 10) {
            throw new IllegalArgumentException("Driver ID must be exactly 10 characters long.");
        }
        for (int i = 0; i < 2; i++) {
            char c = driverID.charAt(i);
            if (c < '2' || c > '9') {
                throw new IllegalArgumentException("The first two characters must be digits between 2 and 9.");
            }
        }
        int specialCount = 0;
        for (int i = 2; i <= 7; i++) {
            if (!Character.isLetterOrDigit(driverID.charAt(i))) {
                specialCount++;
            }
        }
        if (specialCount < 2) {
            throw new IllegalArgumentException("Driver ID must contain at least two special characters between positions 3 and 8.");
        }
        this.driverID = driverID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getLicenseType() {
        return licenseType;
    }
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}