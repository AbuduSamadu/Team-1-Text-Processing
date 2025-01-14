package team1.testprocessing.service;

import team1.testprocessing.utils.AlertUtility;
import team1.testprocessing.utils.Validator;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexProcessor {
    private final Validator validator;

    public RegexProcessor(Validator validator) {
        this.validator = validator;
    }

    public String[] findAllMatches(String text, String regex) {
        validator.validateInputs(text, regex);
        Pattern pattern = Pattern.compile(regex , Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        return matcher.results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }

    public boolean matches(String text, String regex) {
        validator.validateInputs(text, regex);
        return Pattern.matches(regex, text);
    }

    public String replaceAll(String text, String regex, String replacement) {
        validator.validateInputs(text, regex);
        if (replacement == null) {
            AlertUtility.showWarningAlert("Error", "Replacement string cannot be null.", "Please provide a valid replacement string.");
            return null;
        }

        return text.replaceAll(regex, replacement);
    }

}
