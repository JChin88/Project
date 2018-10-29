package edu.gatech.cs2340.cs2340project.mvc.model;

import edu.gatech.cs2340.cs2340project.domain.model.User;

public class LocationEmployee extends User {

    public LocationEmployee(String userID, String name, String email, User.AccountType userType) {
        super(userID, name, email, userType);
    }

    public void addInventory() {}
    public void removeInventory() {}

}
