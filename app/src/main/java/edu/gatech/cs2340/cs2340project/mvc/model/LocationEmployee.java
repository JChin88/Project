package edu.gatech.cs2340.cs2340project.mvc.model;

public class LocationEmployee extends User {

    public LocationEmployee(String name, String id, String password, String email, AccountType userType) {
        super(name, id, password, email, userType);
    }

    public void addInventory() {}
    public void removeInventory() {}

}
