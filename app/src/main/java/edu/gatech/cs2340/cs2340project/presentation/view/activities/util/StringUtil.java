package edu.gatech.cs2340.cs2340project.presentation.view.activities.util;

import android.databinding.InverseMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    @InverseMethod("convertStringToDouble")
    public static String convertDoubleToString(Double value) {
        return String.valueOf(value);
    }

    @InverseMethod("convertDoubleToString")
    public static Double convertStringToDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return (double) -1;
        }
    }

}
