package edu.gatech.cs2340.cs2340project.model;

public class User {

    public enum AccountType {
        User, Admin, Manager
    }

    protected String _name;
    protected String _id;
    protected String _password;
    protected String _email;
    protected AccountType _userType;
    protected boolean _isUserLock = false;

    public User() {
        this("Dummy", "user", "password",
                "user@gatech.edu", AccountType.User);
    }

    public User(String name, String id, String password, String email, AccountType userType) {
        _name = name;
        _id = id;
        _password = password;
        _email = email;
        _userType = userType;
    }

    // Getter/Setter for user name
    public String getName() {
        return _name;
    }
    protected void setName(String name) {
        _name = name;
    }

    // Getter/Setter for user id
    public String getID() {return _id;}
    protected void setID(String userID) {
        _id = userID;
    }

    // Getter/Setter for user password
    public String getPassword() {return _password;}
    protected void setPassword(String password) {
        _password = password;
    }

    // Getter/Setter for user email
    public String getEmail() { return _email;}
    protected void setEmail(String email) {
        _email = email;
    }

    // Getter/Setter for user type
    public AccountType getUserType() { return _userType;}
    protected void setUserType(AccountType userTyle) {
        _userType = userTyle;
    }

    // Getter/setter for user state
    public boolean isUserLock() {
        return _isUserLock;
    }
    protected void setUserState(boolean state) {
        _isUserLock = state;
    }

    public String toString() {
        return _name + " " + _id + " " + _password;
    }

}
