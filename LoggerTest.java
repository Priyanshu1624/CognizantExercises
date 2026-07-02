/**
 * Test class for Exercise 1 (Singleton Pattern).
 * Per handbook: "Create a test class to verify that only one instance of
 * Logger is created and used across the application."
 */
public class LoggerTest {

    public static void main(String[] args) {

        System.out.println("Module A requests the Logger...");
        Logger loggerInModuleA = Logger.getInstance();
        loggerInModuleA.log("User logged in successfully.");

        System.out.println("\nModule B requests the Logger...");
        Logger loggerInModuleB = Logger.getInstance();
        loggerInModuleB.log("Payment processed.");

        System.out.println("\nModule C requests the Logger...");
        Logger loggerInModuleC = Logger.getInstance();
        loggerInModuleC.log("Order shipped.");

        // Verification: are all three references pointing to the exact
        // same object in memory?
        boolean sameAandB = (loggerInModuleA == loggerInModuleB);
        boolean sameBandC = (loggerInModuleB == loggerInModuleC);

        System.out.println("\nVerification:");
        System.out.println("Module A and Module B share the same Logger instance? " + sameAandB);
        System.out.println("Module B and Module C share the same Logger instance? " + sameBandC);

        if (sameAandB && sameBandC) {
            System.out.println("PASS: Singleton pattern is working correctly — only one Logger instance exists.");
        } else {
            System.out.println("FAIL: Multiple Logger instances were created!");
        }
    }
}