package edu.gatech.cs2340.cs2340project.model;

public class User {
    private String id;
    private String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public User() {
        this("user", "password");
    }

    public String getID() {return this.id;}
    public String getPassword() {return this.password;}


}
