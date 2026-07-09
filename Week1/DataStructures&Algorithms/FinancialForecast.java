import java.util.HashMap;
import java.util.Map;


public class FinancialForecast {

  
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {

        if (years == 0) {
            return presentValue;
        }

        return calculateFutureValue(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

  
    public static double calculateFutureValueMemoized(double presentValue, double growthRate, int years,
                                                        Map<Integer, Double> cache) {
        if (years == 0) {
            return presentValue;
        }
        if (cache.containsKey(years)) {
            return cache.get(years);
        }
        double result = calculateFutureValueMemoized(presentValue, growthRate, years - 1, cache) * (1 + growthRate);
        cache.put(years, result);
        return result;
    }

    public static void main(String[] args) {
        double presentValue = 10000.0; // e.g. $10,000 initial investment
        double growthRate = 0.07;      // 7% annual growth
        int years = 10;

        double futureValue = calculateFutureValue(presentValue, growthRate, years);
        System.out.printf("Present Value: $%.2f%n", presentValue);
        System.out.printf("Growth Rate: %.1f%% per year%n", growthRate * 100);
        System.out.printf("Projected Value after %d years: $%.2f%n", years, futureValue);

        System.out.println("\n--- Memoized version (same result, cached) ---");
        Map<Integer, Double> cache = new HashMap<>();
        double futureValueMemo = calculateFutureValueMemoized(presentValue, growthRate, years, cache);
        System.out.printf("Projected Value (memoized) after %d years: $%.2f%n", years, futureValueMemo);

        System.out.println("\nAnalysis:");
        System.out.println("Time complexity: O(n) - the recursion makes exactly 'years' calls,");
        System.out.println("each doing O(1) work, so total work grows linearly with n.");
        System.out.println("Space complexity: O(n) - due to the call stack depth (n frames deep).");
        System.out.println("Optimization: this could trivially be rewritten as an iterative loop");
        System.out.println("to use O(1) space instead of O(n) stack space, since each step only");
        System.out.println("needs the previous year's value, not the whole call history.");
    }
}
