package edu.gatech.cs2340.cs2340project.mvc.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LocationEmployeeTest {
    double hours;
    double hourlyRate;
    double outcome;
    boolean employed = true;
    boolean highDonator;
    LocationEmployee jim = new LocationEmployee();
    @Test
    public void salary() {
        //test for unemployed (employed == false)
        employed = false;
        hours = 10;
        hourlyRate = 10;
        outcome = jim.salary(hourlyRate, hours, employed);
        assertEquals(0, outcome, .1);

        
        //test for hours less than 40 and employed is true
        employed = true;
        hours = 10;
        hourlyRate = 10;
        outcome = jim.salary(hourlyRate, hours, employed);
        assertEquals(100, outcome, .1);

        //test for hours greater than 40 and employed is true
        employed = true;
        hours = 50;
        outcome = jim.salary(hourlyRate, hours, employed);
        assertEquals(750, outcome, .1);

    }


    @Test
    public void totalDonation() {
        //tests if a negetive donation amount is given (negetive donation amount)
        int[] donations1 = {-1 , 2, 4, 5, 6};
        try {
            highDonator = jim.totalDonation(donations1);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            assertThat(anIllegalArgumentException.getMessage(), is("the donation is Negative"));
        }

        //tests if the donator is not a high donator (low Donator < 20)
        int[] donations2 = {1, 2, 4, 5, 6};
        highDonator = jim.totalDonation(donations2);
        assertFalse(highDonator);

        //tests if the donator is a high donator (high Donator total > 20)
        int[] donations3 = {1, 2, 10, 5, 6};
        highDonator = jim.totalDonation(donations3);
        assertTrue(highDonator);
    }
}