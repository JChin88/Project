package edu.gatech.cs2340.cs2340project.domain.model;

public class User {

    public enum AccountType {
        User ("User"), Admin ("Admin"), Manager ("Manager");

        private final String role;

        AccountType(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    protected String userID;
    protected String name;
    protected String email;
    protected AccountType userType;
    public User() {
        // Empty constructor needed for firestore
    }

    public User(String userID, String name, String email, AccountType userType) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountType getUserType() {
        return userType;
    }

    public void setUserType(AccountType userType) {
        this.userType = userType;
    }
}
