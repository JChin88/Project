package edu.gatech.cs2340.cs2340project.presentation.view.activities.util;

import java.util.regex.Pattern;

/**
 * Check input for test
 */
class CheckInput {

    public static final String EMAIL_EMPTY = "Email is empty. Please insert an email";
    public static final String EMAIL_FORMAT = "Please enter a valid email";
    public static final String PASSWORD_EMPTY = "Password is empty. Please insert a password";
    public static final String PASSWORD_SHORT = "Minimum length of password should be 6";
    public static final String VALID_INPUT = "Please enter a valid email";


    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    /**
     * Check valid input for test cases only
     * @param email user email
     * @param password user password
     * @return message to indicate login state
     */
    public static String isInputValid(String email, String password) {
        if (email.isEmpty()) {
            return EMAIL_EMPTY;
        }

        if (!EMAIL_ADDRESS_PATTERN.matcher(email).matches()) {
            return EMAIL_FORMAT;
        }

        if (password.isEmpty()) {
            return PASSWORD_EMPTY;
        }

        if (password.length() < 6) {
            return PASSWORD_SHORT;
        }

        return VALID_INPUT;
    }
}
