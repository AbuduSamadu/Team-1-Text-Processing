package team1.testprocessing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team1.testprocessing.utils.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegexProcessorTest {

    @Mock
    private RegexProcessor regexProcessor;
    private Validator mockValidator;

    @BeforeEach
    void setUp() {
        mockValidator = mock(Validator.class);
        regexProcessor = new RegexProcessor(mockValidator);
    }

    @Test
    void testFindAllMatches_Success() {
        String text = "This is a test. Testing regex functionality.";
        String regex = "test";

        String[] expectedMatches = {"test", "Test"};

        when(mockValidator.validateInputs(text, regex)).thenReturn(true);

        String[] actualMatches = regexProcessor.findAllMatches(text, regex);

        assertArrayEquals(expectedMatches, actualMatches);
    }

    @Test
    void testFindAllMatches_EmptyResult() {
        String text = "This is a test.";
        String regex = "xyz";

        when(mockValidator.validateInputs(text, regex)).thenReturn(true);

        String[] actualMatches = regexProcessor.findAllMatches(text, regex);

        assertEquals(0, actualMatches.length);
    }

    @Test
    void testMatches_Success() {
        String text = "test@example.com";
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Email regex

        when(mockValidator.validateInputs(text, regex)).thenReturn(true);

        boolean result = regexProcessor.matches(text, regex);

        assertTrue(result);
    }

    @Test
    void testMatches_Failure() {
        String text = "invalid-email";
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Email regex

        when(mockValidator.validateInputs(text, regex)).thenReturn(true);

        boolean result = regexProcessor.matches(text, regex);

        assertFalse(result);
    }

    @Test
    void testReplaceAll_Success() {
        String text = "Hello, world!";
        String regex = "world";
        String replacement = "universe";

        when(mockValidator.validateInputs(text, regex)).thenReturn(true);

        String result = regexProcessor.replaceAll(text, regex, replacement);

        assertEquals("Hello, universe!", result);
    }

    @Test
    void testReplaceAll_Failure_InvalidRegex() {
        String text = "Hello, world!";
        String regex = null;

        when(mockValidator.validateInputs(text, regex)).thenReturn(false);
    }
}
