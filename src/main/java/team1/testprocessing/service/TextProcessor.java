package team1.testprocessing.service;

public class TextProcessor {
    private final RegexService regexService;

    public TextProcessor(RegexService regexService) {
        this.regexService = regexService;
    }

    public String handleOtherRegexPatterns(String text, String regex) {
        return regexService.handleOtherRegexPatterns(text, regex);
    }

    public String search(String text, String regex) {
        regexService.validateRegex(regex);
        return String.join("\n", regexService.findAllMatches(text, regex));
    }

    public String replace(String text, String regex, String replacement) {
        regexService.validateRegex(regex);
        return regexService.replaceAllMatches(text, regex, replacement);
    }

    public String exactMatch(String text, String regex) {
        regexService.validateRegex(regex);
        return regexService.isExactMatch(text, regex);
    }
}
