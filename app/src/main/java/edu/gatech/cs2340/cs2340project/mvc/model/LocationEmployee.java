package edu.gatech.cs2340.cs2340project.mvc.model;

import edu.gatech.cs2340.cs2340project.domain.model.User;

public class LocationEmployee extends User {

    public LocationEmployee() {
        //super("jim", 1, "password", "email", new LocationEmployee);
    }

    public LocationEmployee(String name, String id, String password, String email, User.AccountType userType) {
        super(name, id, password, email, userType);
    }
    public void addInventory() {}
    public void removeInventory() {}


    /**
     * calculates the salary the employee receives
     * @param hourlyRate pay per hour
     * @param hours number of hours worked
     * @param employed true if employed false otherwise
     * @return the total salary, 0 if unemployed
     */
    public double salary(double hourlyRate, double hours, boolean employed) {
        if (employed == false) {
            return 0;
        } else {
            if (hours <= 40) {
                return hours * hourlyRate;
            } else {
                double value = hourlyRate * 1.5 * hours;
                return value;
            }
        }
    }
}
