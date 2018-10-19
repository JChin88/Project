package edu.gatech.cs2340.cs2340project.mvc.model;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.User;

public class Admin extends User {
    private List<String> lockUnlockHistory;

    private Admin(String name, String id, String password, String email, User.AccountType userType) {
        super(name, id, password, email, userType);
        this.lockUnlockHistory = new ArrayList<>();
    }

    /**
     * methods lock, unlock, add, and remove are all stubbed
     * for future implementation
     */
    public void lock(){};
    public void unlock(){};
    public void add(){};
    public void remove(){};




}
