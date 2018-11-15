package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class LogTest {




    @Test
    public void isInputValid() {
        String validEmailInput = "test@gmail.com";
        String validPasswordInput = "password";
        boolean output;
        LoginActivity loginActivity = new LoginActivity();

        output = loginActivity.isInputValid(validEmailInput, validPasswordInput);
        assertEquals(true, output);

        //test for empty email
        String invalidEmailInput = "";
        output = loginActivity.isInputValid(invalidEmailInput, validPasswordInput);
        assertEquals(false, output);

        //no matches for email address found
        invalidEmailInput = "INVALID";
        output = loginActivity.isInputValid(invalidEmailInput, validPasswordInput);
        assertEquals(false, output);

        //empty password input
        String invalidPasswordInput = "";
        output = loginActivity.isInputValid(validEmailInput, invalidPasswordInput);
        assertEquals(false, output);

        //password is less than 6 characters long
        invalidPasswordInput = "short";
        output = loginActivity.isInputValid(validEmailInput, invalidPasswordInput);
        assertEquals(false, output);

        System.out.println("all tests passed");
    }

}