package PFT.util;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public final class Validator {
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^[a-zA-Z0-9 _-]+$");

    public static boolean isValidDate(String date) {
        if (date == null || date.trim().isEmpty()) return false;

        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static boolean isValidSum(double value){
        return value > 0;
    }

    public static boolean isValidDescription(String description){
        if (description == null) return false;
        if (description.trim().isEmpty()) return false;
        return DESCRIPTION_PATTERN.matcher(description.trim()).matches();
    }



}
