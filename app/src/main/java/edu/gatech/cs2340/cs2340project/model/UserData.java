package edu.gatech.cs2340.cs2340project.model;

import java.util.HashMap;

public class UserData {
    private HashMap<String, Integer> loginData = new HashMap<>();
    private HashMap<String, User> userList = new HashMap<>();

    public HashMap<String, Integer> getLoginData() {
        return loginData;
    }

    /**
     * Adds a new user to the database
     * @param newUser the user to be added
     */
    public void addUser(User newUser) {
        String id = newUser.getID();
        Integer passHash = Integer.valueOf(newUser.getID().hashCode());
        loginData.put(id, passHash);
        userList.put(id, newUser);
    }

    /**
     * find a user by id
     * @param id the user's ID
     * @return the user associated with the id
     */
    public User getUserData(String id) {
        return userList.get(id);
    }
}
