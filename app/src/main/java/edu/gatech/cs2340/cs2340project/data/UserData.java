package edu.gatech.cs2340.cs2340project.data;

import java.util.HashMap;

import edu.gatech.cs2340.cs2340project.domain.model.User;

public class UserData {
    private static HashMap<String, Integer> loginData = new HashMap<>();
    private static HashMap<String, User> userList = new HashMap<>();

    public static HashMap<String, Integer> getLoginData() {
        return loginData;
    }
    public static HashMap<String, User> getUserList() { return userList;}

    /**
     * Adds a new user to the database
     * @param newUser the user to be added
     * @return if the user was successfully added
     */
    public static boolean addUser(User newUser){
        String id = newUser.getID();
        Integer passHash = Integer.valueOf(newUser.getPassword().hashCode());
        if (!loginData.containsKey(id)) {
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
    public static User getUser(String id) {
        return userList.get(id);
    }
}
