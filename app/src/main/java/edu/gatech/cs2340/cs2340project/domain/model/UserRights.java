package edu.gatech.cs2340.cs2340project.domain.model;

/**
 * @author Hoa V Luu
 */
public enum UserRights {
//    GUESS (false, false, false, false, false, false, false),
    USER (false, false, false, false, false, false, false),
    LOCATION_EMPLOYEE (true, false, false, false, false, false, false),
    MANAGER (true, true, true, true, true, false, false),
    ADMIN (true, true, true, true, true, true, true);

    private final boolean isCanUpdateInventories;
    private final boolean isCanAddLocation;
    private final boolean isCanRemoveLocation;
    private final boolean isCanAddUser;
    private final boolean isCanRemoveUser;
    private final boolean isCanUnlockUser;
    private final boolean isCanLockUser;
    private String manageLocation;

    UserRights(boolean isCanUpdateInventories, boolean isCanAddLocation,
                       boolean isCanRemoveLocation, boolean isCanAddUser, boolean isCanRemoveUser,
                       boolean isCanUnlockUser, boolean isCanLockUser) {
        this.isCanUpdateInventories = isCanUpdateInventories;
        this.isCanAddLocation = isCanAddLocation;
        this.isCanRemoveLocation = isCanRemoveLocation;
        this.isCanAddUser = isCanAddUser;
        this.isCanRemoveUser = isCanRemoveUser;
        this.isCanUnlockUser = isCanUnlockUser;
        this.isCanLockUser = isCanLockUser;
    }

    /**
     * get user right for update inventories
     * @return user right for update inventories
     */
    public boolean isCanUpdateInventories() {
        return isCanUpdateInventories;
    }

    /**
     * get user right for add location
     * @return user right for add location
     */
    public boolean isCanAddLocation() {
        return isCanAddLocation;
    }

    /**
     * get user right for remove location
     * @return user right for remove location
     */
    public boolean isCanRemoveLocation() {
        return isCanRemoveLocation;
    }

    /**
     * get user right for add user
     * @return user right for add user
     */
    public boolean isCanAddUser() {
        return isCanAddUser;
    }

    /**
     * get user right for remove user
     * @return user right for remove user
     */
    public boolean isCanRemoveUser() {
        return isCanRemoveUser;
    }

    /**
     * get user right for unlock user
     * @return user right for unlock user
     */
    public boolean isCanUnlockUser() {
        return isCanUnlockUser;
    }

    /**
     * get user right for lock user
     * @return user right for lock user
     */
    public boolean isCanLockUser() {
        return isCanLockUser;
    }
}
