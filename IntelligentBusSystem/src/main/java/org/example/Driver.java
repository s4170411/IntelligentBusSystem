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
        setAddress(address);
        setBirthdate(birthdate);
    }

    public String getDriverID() { 
        return driverID; 
    }
    // Validates the driver ID: 10 characters, first two digits between 2-9
    // At least two special characters in positions 3-8
    // Last two characters must be uppercase letters (A-Z)
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
        for (int i = 8; i <= 9; i++) {
            char c = driverID.charAt(i);
            if (c < 'A' || c > 'Z') {
                throw new IllegalArgumentException("The last two characters must be uppercase letters.");
            }
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
    // Validates the address format x|y|z|w|v and ensures no empty fields before setting it
    public void setAddress(String address) {
        String[] parts = address.split("\\|", -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Address must follow the format: Street Number|Street Name|City|State|Country.");
        }
        for (String part : parts) {
            if (part.trim().isEmpty()) {
                throw new IllegalArgumentException("Address must not have any empty fields.");
            }
        }
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }
    // Validates the birthdate is in format DD-MM-YYYY and ensures it represents a valid date before setting it
    public void setBirthdate(String birthdate) {
        if (!birthdate.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new IllegalArgumentException("Birthdate must follow the format: DD-MM-YYYY.");
        }
        int day = Integer.parseInt(birthdate.substring(0, 2));
        int month = Integer.parseInt(birthdate.substring(3, 5));
        int year = Integer.parseInt(birthdate.substring(6, 10));
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Birthdate day must be between 01 and 31.");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Birthdate month must be between 01 and 12.");
        }
        if (year < 1900 || year > 2008) {
            throw new IllegalArgumentException("Birthdate year must be between 1900 and 2008.");
        }
        this.birthdate = birthdate;
    }

    public void updateDriver(Driver updatedDriver) {
        if (!this.driverID.equals(updatedDriver.getDriverID())) {
            throw new IllegalArgumentException("Driver ID cannot be changed during an update.");
        }
        if (!this.name.equals(updatedDriver.getName())) {
            throw new IllegalArgumentException("Driver name cannot be changed during an update.");
        }
        if (this.experienceYears > 10 && !this.licenseType.equals(updatedDriver.getLicenseType())) {
            throw new IllegalArgumentException("Cannot change license type for a driver with more than 10 years of experience.");
        }
        this.experienceYears = updatedDriver.getExperienceYears();
        this.licenseType = updatedDriver.getLicenseType();
        setAddress(updatedDriver.getAddress());
        setBirthdate(updatedDriver.getBirthdate());
    }
}