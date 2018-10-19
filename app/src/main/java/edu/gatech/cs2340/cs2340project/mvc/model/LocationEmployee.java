package edu.gatech.cs2340.cs2340project.mvc.model;

import edu.gatech.cs2340.cs2340project.domain.model.User;

public class LocationEmployee extends User {

    public LocationEmployee(String name, String id, String password, String email, User.AccountType userType) {
        super(name, id, password, email, userType);
    }

    public void addInventory() {}
    public void removeInventory() {}

}
