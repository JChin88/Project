package edu.gatech.cs2340.cs2340project.domain.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * @author Hoa V Luu
 */
public class User extends BaseObservable {

    private String userID;
    private String name;
    private String email;
    private String manageLocationName;
    private UserRights userRights;

    /**
     * default constructor
     */
    public User() {
        // Empty constructor needed for firestore
    }

    /**
     * constructor
     * @param userID user id
     * @param name user name
     * @param email user email
     * @param userRights user rights
     */
    public User(String userID, String name, String email, UserRights userRights) {
        this(userID, name, email, null, userRights);
    }

    /**
     *
     * @param userID user id
     * @param name user name
     * @param email user email
     * @param manageLocationName location manage user
     * @param userRights user rights
     */
    public User(String userID, String name, String email,
                String manageLocationName, UserRights userRights) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.manageLocationName = manageLocationName;
        this.userRights = userRights;
    }

    /**
     *
     * @return user id
     */
    @Bindable
    public String getUserID() {
        return userID;
    }

    /**
     *
     * @param userID user id want to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     *
     * @return user name
     */
    @Bindable
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name wanted to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return user email
     */
    @Bindable
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email email wanted to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return manage location that user manage
     */
    @Bindable
    public String getManageLocationName() {
        return manageLocationName;
    }

    /**
     *
     * @param manageLocationName manage location want to set
     */
    public void setManageLocationName(String manageLocationName) {
        this.manageLocationName = manageLocationName;
    }

    /**
     *
     * @return user rights
     */
    @Bindable
    public UserRights getUserRights() {
        return userRights;
    }

    /**
     *
     * @param userRights user rights wanted to set
     */
    public void setUserRights(UserRights userRights) {
        this.userRights = userRights;
    }

    /**
     *
     * @return can user update inventories
     */
    @Bindable
    public boolean isCanUpdateInventories() {
        return userRights.isCanUpdateInventories();
    }

    /**
     *
     * @return can user add location
     */
    @Bindable
    public boolean isCanAddLocation() {
        return userRights.isCanAddLocation();
    }

    /**
     *
     * @return can user remove location
     */
    @Bindable
    public boolean isCanRemoveLocation() {
        return userRights.isCanRemoveLocation();
    }

    /**
     *
     * @return can user add user
     */
    @Bindable
    public boolean isCanAddUser() {
        return userRights.isCanAddUser();
    }

    /**
     *
     * @return can user remove user
     */
    @Bindable
    public boolean isCanRemoveUser() {
        return userRights.isCanRemoveUser();
    }

    /**
     *
     * @return can user unlock user
     */
    @Bindable
    public boolean isCanUnlockUser() {
        return userRights.isCanUnlockUser();
    }

    /**
     *
     * @return can user lock user
     */
    @Bindable
    public boolean isCanLockUser() {
        return userRights.isCanLockUser();
    }

}
