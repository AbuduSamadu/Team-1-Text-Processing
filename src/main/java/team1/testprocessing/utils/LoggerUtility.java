package team1.testprocessing.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtility {
    private static final Logger logger = Logger.getLogger(LoggerUtility.class.getName());

    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }
    public static void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }

    public static void logSevere(String message) {
        logger.log(Level.SEVERE, message);
    }
    public static void logError(Exception e) {
        logger.log(Level.SEVERE, "An error occurred: ", e);
    }

}
