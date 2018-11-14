package edu.gatech.cs2340.cs2340project.mvc.model;


public class LocationEmployee  {

    public LocationEmployee() {
        //super("jim", 1, "password", "email", new LocationEmployee);
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


    /**
     * Sums up the total number of donations of a person
     * Determines if they are a High donator or not
     * If the donation is greater than 20 then they are a
     * high Donator. if it is less then they are Low
     * @param donations pay per hour
     * @return True if they are a High donator, False if they are a low
     */
    public boolean totalDonation(int[] donations) {
        int total = 0;
        for (int i = 0; i < donations.length; i++) {
            if (donations[i] < 0) {
                throw new IllegalArgumentException("the donation is Negative");
            } else {
                total = donations[i] + total;
            }
        }

        if (total > 20) {
            return true;
        } else {
            return false;
        }
    }
}
