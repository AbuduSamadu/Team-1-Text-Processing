package team1.testprocessing.controllers;

import org.jetbrains.annotations.NotNull;
import team1.testprocessing.service.RegexProcessor;
import team1.testprocessing.utils.Validator;

import java.util.Arrays;

public class RegexProcessingController {
    private final RegexProcessor regexProcessor;

    public RegexProcessingController() {
        this.regexProcessor = new RegexProcessor(new Validator());
    }

    @NotNull
    static String getString(String text, String regex, RegexProcessor regexProcessor) {
        if (!Validator.isValidRegex(regex)) {
            return "Invalid regex pattern.";
        }

        try {
            String[] matches = regexProcessor.findAllMatches(text, regex);
            return matches.length > 0 ? "Matches found: " + Arrays.toString(matches) : "No matches found.";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String handleRegexValidation(String text, String regex) {
        return Validator.isValidRegex(regex) ? "Valid regex pattern." : "Invalid regex pattern.";
    }

    public String[] findAllMatches(String text, String regex) {
        return regexProcessor.findAllMatches(text, regex);
    }

    public boolean matches(String text, String regex) {
        return regexProcessor.matches(text, regex);
    }

    public String replaceAll(String text, String regex, String replacement) {
        return regexProcessor.replaceAll(text, regex, replacement);
    }

}
