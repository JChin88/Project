package edu.gatech.cs2340.cs2340project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Admin extends User {
    private List<String> lockUnlockHistory;

    private Admin(String id, String password, String email, AccountType userType) {
        super(id, password, email, userType);
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
