package edu.gatech.cs2340.cs2340project.model;

public class User {

    enum AccountType {
        User, Admin, Manager
    }

    protected String _id;
    protected String _password;
    protected String _email;
    protected AccountType _userType;
    protected boolean _isUserLock = false;


    public User(String id, String password, String email, AccountType userType) {
        _id = id;
        _password = password;
        _email = email;
        _userType = userType;
    }

    public User() {
        this("user", "password", "user@gatech.edu", AccountType.User);
    }

    public String getID() {return _id;}

    //
    public String getPassword() {return _password;}
    protected void setPassword(String password) {
        _password = password;
    }

    //User Email
    public String getEmail() { return _email;}
    protected void setEmail(String email) {
        _email = email;
    }

    //
    public AccountType getUserType() { return _userType;}
    protected void setUserType(AccountType userTyle) {
        _userType = userTyle;
    }

    public boolean isUserLock() {
        return _isUserLock;
    }
    protected void setUserState(boolean state) {
        _isUserLock = state;
    }

}
