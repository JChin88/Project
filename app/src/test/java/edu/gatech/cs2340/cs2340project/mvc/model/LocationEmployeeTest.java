package edu.gatech.cs2340.cs2340project.mvc.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationEmployeeTest {
    double hours;
    double hourlyRate;
    double outcome;
    boolean employed = true;
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
}