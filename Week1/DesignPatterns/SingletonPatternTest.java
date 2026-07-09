
public class SingletonPatternTest {

    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("First message from logger1");
        logger2.log("Second message from logger2");

        System.out.println("logger1 == logger2 ? " + (logger1 == logger2));
        System.out.println("logger1 hashCode: " + logger1.hashCode());
        System.out.println("logger2 hashCode: " + logger2.hashCode());
        System.out.println("Total logs recorded (shared state): " + logger1.getLogCount());

        if (logger1 == logger2 && logger1.getLogCount() == 2) {
            System.out.println("PASS: Singleton verified - only one instance exists.");
        } else {
            System.out.println("FAIL: More than one instance detected!");
        }
    }
}
