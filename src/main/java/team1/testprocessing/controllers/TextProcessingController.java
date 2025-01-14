package team1.testprocessing.controllers;

import team1.testprocessing.service.RegexProcessor;
import team1.testprocessing.service.RegexService;
import team1.testprocessing.service.TextProcessor;
import team1.testprocessing.utils.Validator;

public class TextProcessingController {
    private final TextProcessor textProcessor;
    private final RegexProcessor regexProcessor;

    public TextProcessingController() {
        Validator validator = new Validator();
        RegexService regexService = new RegexService(new RegexProcessor(validator));
        this.textProcessor = new TextProcessor(regexService);
        this.regexProcessor = new RegexProcessor(validator);
    }

    public String handleOtherRegexPatterns(String text, String regex) {
        if (!Validator.isValidRegex(regex))
            return "Invalid regex pattern.";

        return textProcessor.handleOtherRegexPatterns(text, regex);
    }

    public String handleSearch(String text, String regex) {
        if (!Validator.isValidRegex(regex)) {
            return "Invalid regex pattern.";
        }
        String[] matches = textProcessor.search(text, regex).split("\n");
        int count = matches.length;
        return "Search: " + regex + ", Occurrences: " + count;
    }

    public String handleReplace(String text, String regex, String replacement) {
        if (!Validator.isValidRegex(regex)) {
            return "Invalid regex pattern.";
        }
        try {
            return textProcessor.replace(text, regex, replacement);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String handleExactMatch(String text, String regex) {
        if (!Validator.isValidRegex(regex)) {
            return "Invalid regex pattern.";
        }
        return textProcessor.exactMatch(text, regex);
    }
}
