package edu.gatech.cs2340.cs2340project.presentation.view.activities.util;

import org.junit.Test;

import static edu.gatech.cs2340.cs2340project.presentation.view.activities.util.CheckInput.EMAIL_EMPTY;
import static edu.gatech.cs2340.cs2340project.presentation.view.activities.util.CheckInput.EMAIL_FORMAT;
import static edu.gatech.cs2340.cs2340project.presentation.view.activities.util.CheckInput.PASSWORD_EMPTY;
import static edu.gatech.cs2340.cs2340project.presentation.view.activities.util.CheckInput.PASSWORD_SHORT;
import static edu.gatech.cs2340.cs2340project.presentation.view.activities.util.CheckInput.VALID_INPUT;
import static org.junit.Assert.*;

/**
 * Check input valid or not
 */
public class CheckInputTest {

    /**
     * Check email emptry
     */
    @Test
    public void checkEmailEmpty() {
        String emailEmpty = CheckInput.isInputValid("", "password");
        assertEquals(emailEmpty, EMAIL_EMPTY);
    }

    /**
     * check email format
     */
    @Test
    public void checkEmailFormat() {
        String emailFormat = CheckInput.isInputValid("henry", "password");
        assertEquals(emailFormat, EMAIL_FORMAT);
    }

    /**
     * check password empty
     */
    @Test
    public void checkPasswordEmpty() {
        String passwordEmpty = CheckInput.isInputValid("henry@gmail.com", "");
        assertEquals(passwordEmpty, PASSWORD_EMPTY);
    }

    /**
     * check password length
     */
    @Test
    public void checkPasswordLength() {
        String passwordShort = CheckInput.isInputValid("henry@gmail.com", "pass");
        assertEquals(passwordShort, PASSWORD_SHORT);
    }

    /**
     * check valid
     */
    @Test
    public void checkValid() {
        String valid = CheckInput.isInputValid("henry@gmail.com", "password");
        assertEquals(valid, VALID_INPUT);
    }
}