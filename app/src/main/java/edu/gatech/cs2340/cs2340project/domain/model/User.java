package edu.gatech.cs2340.cs2340project.domain.model;

public class User {

    private String userID;
    private String name;
    private String email;
    private String manageLocationName;
    private UserRights userRights;

    public User() {
        // Empty constructor needed for firestore
    }

    public User(String userID, String name, String email, UserRights userRights) {
        this(userID, name, email, null, userRights);
    }

    public User(String userID, String name, String email,
                String manageLocationName, UserRights userRights) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.manageLocationName = manageLocationName;
        this.userRights = userRights;
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

    public String getManageLocationName() {
        return manageLocationName;
    }

    public void setManageLocationName(String manageLocationName) {
        this.manageLocationName = manageLocationName;
    }

    public UserRights getUserRights() {
        return userRights;
    }

    public void setUserRights(UserRights userRights) {
        this.userRights = userRights;
    }

    //
    public boolean isCanUpdateInventories() {
        return userRights.isCanUpdateInventories();
    }

    public boolean isCanAddLocation() {
        return userRights.isCanAddLocation();
    }

    public boolean isCanRemoveLocation() {
        return userRights.isCanRemoveLocation();
    }

    public boolean isCanAddUser() {
        return userRights.isCanAddUser();
    }

    public boolean isCanRemoveUser() {
        return userRights.isCanRemoveUser();
    }

    public boolean isCanUnlockUser() {
        return userRights.isCanUnlockUser();
    }

    public boolean isCanLockUser() {
        return userRights.isCanLockUser();
    }

}
