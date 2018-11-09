package edu.gatech.cs2340.cs2340project.domain.model;

public enum UserRights {
//    GUESS (false, false, false, false, false, false, false),
    USER (false, false, false, false, false, false, false),
    LOCATION_EMPLOYEE (true, false, false, false, false, false, false),
    MANAGER (true, true, true, true, true, false, false),
    ADMIN (true, true, true, true, true, true, true);

    private boolean isCanUpdateInventories;
    private boolean isCanAddLocation;
    private boolean isCanRemoveLocation;
    private boolean isCanAddUser;
    private boolean isCanRemoveUser;
    private boolean isCanUnlockUser;
    private boolean isCanLockUser;
    private String manageLocation;

    private UserRights(boolean isCanUpdateInventories, boolean isCanAddLocation,
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

    public boolean isCanUpdateInventories() {
        return isCanUpdateInventories;
    }

    public boolean isCanAddLocation() {
        return isCanAddLocation;
    }

    public boolean isCanRemoveLocation() {
        return isCanRemoveLocation;
    }

    public boolean isCanAddUser() {
        return isCanAddUser;
    }

    public boolean isCanRemoveUser() {
        return isCanRemoveUser;
    }

    public boolean isCanUnlockUser() {
        return isCanUnlockUser;
    }

    public boolean isCanLockUser() {
        return isCanLockUser;
    }
}
