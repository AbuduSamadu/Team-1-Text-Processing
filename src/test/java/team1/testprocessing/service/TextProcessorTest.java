package team1.testprocessing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {

    private RegexService regexService;
    private TextProcessor textProcessor;

    @BeforeEach
    void setUp() {
        regexService = mock(RegexService.class);
        textProcessor = new TextProcessor(regexService);
    }

    @Test
    void testHandleOtherRegexPatterns() {
        String text = "apple, orange, banana";
        String regex = "\\w+";

        when(regexService.handleOtherRegexPatterns(text, regex)).thenReturn("apple,orange,banana,");

        String result = textProcessor.handleOtherRegexPatterns(text, regex);

        assertEquals("apple,orange,banana,", result);
    }

    @Test
    void testSearch_validRegex() {
        String text = "apple 123 banana 456";
        String regex = "\\d+";

        when(regexService.findAllMatches(text, regex)).thenReturn(new String[]{"123", "456"});

        String result = textProcessor.search(text, regex);

        assertEquals("123\n456", result);
    }

    @Test
    void testReplace_validRegex() {
        String text = "apple 123 banana 456";
        String regex = "\\d+";
        String replacement = "number";

        when(regexService.replaceAllMatches(text, regex, replacement)).thenReturn("apple number banana number");

        String result = textProcessor.replace(text, regex, replacement);

        assertEquals("apple number banana number", result);
    }

    @Test
    void testExactMatch_matchFound() {
        String text = "apple123";
        String regex = "\\w+\\d+";

        when(regexService.isExactMatch(text, regex)).thenReturn("apple123");

        String result = textProcessor.exactMatch(text, regex);

        assertEquals("apple123", result);
    }

    @Test
    void testExactMatch_noMatchFound() {
        String text = "apple";
        String regex = "\\w+\\d+";

        when(regexService.isExactMatch(text, regex)).thenReturn("The text does not match the regex pattern.");

        String result = textProcessor.exactMatch(text, regex);

        assertEquals("The text does not match the regex pattern.", result);
    }
}
