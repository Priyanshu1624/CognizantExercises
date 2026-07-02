import java.util.HashMap;
import java.util.Map;


public class FinancialForecastDemo {

   
    public static double growthFactorRecursive(double growthRate, int yearsAhead) {
        // Base case: zero years ahead means no growth applied yet.
        if (yearsAhead == 0) {
            return 1.0;
        }
        // Recursive case: each call does ONE multiplication, then calls
        // itself with one fewer year remaining.
        return growthFactorRecursive(growthRate, yearsAhead - 1) * (1 + growthRate);
    }

    public static double forecastValue(double presentValue, double growthRate, int yearsAhead) {
        return presentValue * growthFactorRecursive(growthRate, yearsAhead);
    }

    
    private static final Map<Integer, Double> memoCache = new HashMap<>();

    public static double growthFactorMemoized(double growthRate, int yearsAhead) {
        if (yearsAhead == 0) {
            return 1.0;
        }
        // If we've already computed this exact sub-problem, reuse it.
        if (memoCache.containsKey(yearsAhead)) {
            return memoCache.get(yearsAhead);
        }
        double result = growthFactorMemoized(growthRate, yearsAhead - 1) * (1 + growthRate);
        memoCache.put(yearsAhead, result);
        return result;
    }

    public static void main(String[] args) {

        double presentValue = 100000.0; // starting revenue, e.g. in rupees
        double growthRate = 0.08;       // 8% annual growth, based on past data

        System.out.println("Present value: " + presentValue);
        System.out.println("Assumed annual growth rate (from past data): " + (growthRate * 100) + "%\n");

        System.out.println("--- Forecast using plain recursion ---");
        for (int year = 1; year <= 5; year++) {
            double forecast = forecastValue(presentValue, growthRate, year);
            System.out.printf("Year %d forecast: %.2f%n", year, forecast);
        }

        System.out.println("\n--- Forecast using memoized recursion (optimized) ---");
        for (int year = 1; year <= 5; year++) {
            double factor = growthFactorMemoized(growthRate, year);
            double forecast = presentValue * factor;
            System.out.printf("Year %d forecast: %.2f%n", year, forecast);
        }

        System.out.println("\nBoth versions produce identical results — the memoized");
        System.out.println("version simply avoids recomputing the same sub-problems.");
    }
}