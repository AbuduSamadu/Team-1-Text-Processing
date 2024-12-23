package team1.testprocessing.service;

import team1.testprocessing.utils.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexService {
    private final RegexProcessor regexProcessor;

    public RegexService(RegexProcessor regexProcessor) {
        this.regexProcessor = regexProcessor;
    }

    public String handleOtherRegexPatterns(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        StringBuilder builder = new StringBuilder();
        while (matcher.find())
            if (!matcher.group().isBlank())
                builder.append(matcher.group()).append(",");

        return builder.toString();
    }

    public boolean validateRegex(String regex) {
        return !Validator.isValidRegex(regex);
    }

    public String[] findAllMatches(String text, String regex) {
        if (validateRegex(regex)) {
            throw new IllegalArgumentException("Invalid regex pattern.");
        }
        return regexProcessor.findAllMatches(text, regex);
    }

    public String isExactMatch(String text, String regex) {
        if (validateRegex(regex)) {
            throw new IllegalArgumentException("Invalid regex pattern.");
        }
        String[] matches = regexProcessor.findAllMatches(text, regex);
        return matches.length > 0 ? String.join(", ", matches) : "The text does not match the regex pattern.";
    }

    public String replaceAllMatches(String text, String regex, String replacement) {
        if (validateRegex(regex)) {
            throw new IllegalArgumentException("Invalid regex pattern.");
        }
        return regexProcessor.replaceAll(text, regex, replacement);
    }
}
