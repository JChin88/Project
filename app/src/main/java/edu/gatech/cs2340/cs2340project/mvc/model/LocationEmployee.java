package edu.gatech.cs2340.cs2340project.mvc.model;


class LocationEmployee  {
    private static final int MAX_HOUSE = 40;
    private static final double OVERTIME_RATIO = 1.5;
    private static final int BOUND_DONATION = 20;


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
        if (!employed) {
            return 0;
        } else {
            if (hours <= MAX_HOUSE) {
                return hours * hourlyRate;
            } else {
                return hourlyRate * OVERTIME_RATIO * hours;
            }
        }
    }


    /**
     * Sums up the total number of donations of a person
     * Determines if they are a High donator or not.
     * If the donation total is greater than 20 then they are a
     * high Donator. if it is less than 20 they are a Low donator
     * @param donations pay per hour
     * @return True if they are a High donator, False if they are a low
     */
    public boolean totalDonation(int[] donations) {
        int total = 0;
        for (int donation : donations) {
            if (donation < 0) {
                throw new IllegalArgumentException("the donation is Negative");
            } else {
                total = donation + total;
            }
        }

        return total > BOUND_DONATION;
    }
}
