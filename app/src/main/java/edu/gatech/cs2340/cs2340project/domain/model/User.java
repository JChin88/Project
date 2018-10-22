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

    protected String name;
    protected String id;
    protected String password;
    protected String email;
    protected AccountType userType;
    protected boolean isUserLock = false;

    public User() {
        this("Dummy", "user", "password",
                "user@gatech.edu", AccountType.User);
    }

    public User(String name, String userName, String email) {
        this(name, userName, "password", email, AccountType.User);
    }

    public User(String id, String password) {
        this("Dummy", id, password,
                "user@gatech.edu", AccountType.User);
    }

    public User(String name, String id, String password, String email, AccountType userType) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    // Getter/Setter for user name
    public String getName() {
        return name;
    }
    protected void setName(String name) {
        this.name = name;
    }

    // Getter/Setter for user id
    public String getID() {return id;}
    protected void setID(String userID) {
        this.id = userID;
    }

    // Getter/Setter for user password
    public String getPassword() {return password;}
    protected void setPassword(String password) {
        this.password = password;
    }

    // Getter/Setter for user email
    public String getEmail() { return email;}
    protected void setEmail(String email) {
        this.email = email;
    }

    // Getter/Setter for user type
    public AccountType getUserType() { return userType;}
    protected void setUserType(String role) {
        userType = AccountType.valueOf(role);
    }

    // Getter/setter for user state
    protected void setUserState(boolean state) {
        this.isUserLock = state;
    }
    protected boolean getUserState() { return isUserLock;}

    public String toString() {
        return name + " " + id + " " + password;
    }

}
