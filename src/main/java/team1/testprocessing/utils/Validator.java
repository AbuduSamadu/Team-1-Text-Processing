package team1.testprocessing.utils;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validator {
    public Validator() {
    }

    public static boolean isValidRegex(String regex) {
        if (regex == null || regex.isEmpty()) {
            return false;
        }

        try {
            Pattern.compile(regex);
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    public void validateInputs(String text, String regex) {
        if (text == null || text.isEmpty()) {
            AlertUtility.showErrorAlert("Validation Error", "Invalid Input Text", "Input text cannot be null or empty.");
            return;
        }
        if (regex == null || regex.isEmpty()) {
            AlertUtility.showErrorAlert("Validation Error", "Invalid Regex Pattern", "Regex pattern cannot be null or empty.");
        }
    }

}
