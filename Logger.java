/**
 * Exercise 1: Implementing the Singleton Pattern (per Cognizant DN5.0 handbook).
 *
 * Scenario (from handbook):
 * "You need to ensure that a logging utility class in your application has
 * only one instance throughout the application lifecycle to ensure
 * consistent logging."
 *
 * Requirements checklist (from handbook steps):
 *  - Logger class has a private static instance of itself.        [done below]
 *  - Constructor of Logger is private.                            [done below]
 *  - Public static method to get the instance.                    [done below]
 */
public class Logger {

    // Private static instance of itself — the single "slot" the whole
    // application shares.
    private static Logger instance;

    // Keeps a running record of everything logged, just to prove later
    // that every caller really is writing into the SAME object.
    private int logCount;

    // Private constructor — nobody outside this class can call "new Logger()".
    private Logger() {
        logCount = 0;
        System.out.println("Logger: a new instance was actually created.");
    }

    // Public static method to get the one and only instance.
    // synchronized => thread-safe: if two threads call this at the exact
    // same time, only one creates the instance; the other waits and then
    // receives the same object.
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // A normal logging method any part of the application can call.
    public void log(String message) {
        logCount++;
        System.out.println("[LOG #" + logCount + "] " + message);
    }
}