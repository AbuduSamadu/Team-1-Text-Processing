package team1.testprocessing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RegexServiceTest {

    private RegexProcessor regexProcessor;
    private RegexService regexService;

    @BeforeEach
    void setUp() {
        regexProcessor = mock(RegexProcessor.class);
        regexService = new RegexService(regexProcessor);
    }

    @Test
    void testHandleOtherRegexPatterns() {
        String text = "apple, orange, banana";
        String regex = "\\w+";

        when(regexProcessor.findAllMatches(text, regex)).thenReturn(new String[]{"apple", "orange", "banana"});

        String result = regexService.handleOtherRegexPatterns(text, regex);

        assertEquals("apple,orange,banana,", result);
    }

    @Test
    void testValidateRegex_validRegex() {
        String validRegex = "\\d+";

        boolean result = regexService.validateRegex(validRegex);

        assertFalse(result);
    }

    @Test
    void testValidateRegex_invalidRegex() {
        String invalidRegex = "[a-z";

        boolean result = regexService.validateRegex(invalidRegex);

        assertTrue(result);
    }

    @Test
    void testFindAllMatches_validRegex() {
        String text = "apple 123 banana 456";
        String regex = "\\d+";

        when(regexProcessor.findAllMatches(text, regex)).thenReturn(new String[]{"123", "456"});

        String[] result = regexService.findAllMatches(text, regex);

        assertArrayEquals(new String[]{"123", "456"}, result);
    }

    @Test
    void testFindAllMatches_invalidRegex() {
        String text = "apple 123 banana 456";
        String invalidRegex = "[a-z";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            regexService.findAllMatches(text, invalidRegex);
        });

        assertEquals("Invalid regex pattern.", exception.getMessage());
    }

    @Test
    void testIsExactMatch_matchFound() {
        String text = "apple123";
        String regex = "\\w+\\d+";

        when(regexProcessor.findAllMatches(text, regex)).thenReturn(new String[]{"apple123"});

        String result = regexService.isExactMatch(text, regex);

        assertEquals("apple123", result);
    }

    @Test
    void testIsExactMatch_noMatchFound() {
        String text = "apple";
        String regex = "\\w+\\d+";

        when(regexProcessor.findAllMatches(text, regex)).thenReturn(new String[]{});

        String result = regexService.isExactMatch(text, regex);

        assertEquals("The text does not match the regex pattern.", result);
    }

    @Test
    void testReplaceAllMatches() {
        String text = "apple 123 banana 456";
        String regex = "\\d+";
        String replacement = "number";

        when(regexProcessor.replaceAll(text, regex, replacement)).thenReturn("apple number banana number");

        String result = regexService.replaceAllMatches(text, regex, replacement);

        assertEquals("apple number banana number", result);
    }

    @Test
    void testReplaceAllMatches_invalidRegex() {
        String text = "apple 123 banana 456";
        String invalidRegex = "[a-z";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            regexService.replaceAllMatches(text, invalidRegex, "number");
        });

        assertEquals("Invalid regex pattern.", exception.getMessage());
    }
}
