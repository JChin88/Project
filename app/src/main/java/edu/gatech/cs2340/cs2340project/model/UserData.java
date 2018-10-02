package edu.gatech.cs2340.cs2340project.model;

import java.util.HashMap;

public class UserData {
    private static HashMap<String, Integer> loginData = new HashMap<>();
    private static HashMap<String, User> userList = new HashMap<>();

    public static HashMap<String, Integer> getLoginData() {
        return loginData;
    }

    /**
     * Adds a new user to the database
     * @param newUser the user to be added
     * @return if the user was successfully added
     */
    public boolean addUser(User newUser){
        String id = newUser.getID();
        Integer passHash = Integer.valueOf(newUser.getID().hashCode());
        if (loginData.containsKey(id)) {
            loginData.put(id, passHash);
            userList.put(id, newUser);
            return true;
        }
        return false;
    }

    /**
     * find a user by id
     * @param id the user's ID
     * @return the user associated with the id
     */
    public User getUserList(String id) {
        return userList.get(id);
    }
}
