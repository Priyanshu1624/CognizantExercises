
public class SearchDemo {


    public static Product linearSearch(Product[] products, String targetName) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(targetName)) {
                return p;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] sortedProducts, String targetName) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedProducts[mid].getProductName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                low = mid + 1;  
            } else {
                high = mid - 1;  
            }
        }
        return null;
    }

    public static void main(String[] args) {

        Product[] catalog = {
                new Product(101, "Wireless Mouse", "Electronics"),
                new Product(102, "Bluetooth Speaker", "Electronics"),
                new Product(103, "Yoga Mat", "Fitness"),
                new Product(104, "Running Shoes", "Fitness"),
                new Product(105, "Air Fryer", "Home Appliances")
        };

        System.out.println("--- Linear Search Demo (unsorted array) ---");
        Product foundLinear = linearSearch(catalog, "Yoga Mat");
        System.out.println("Search result: " + foundLinear);

        Product[] sortedCatalog = {
                new Product(105, "Air Fryer", "Home Appliances"),
                new Product(102, "Bluetooth Speaker", "Electronics"),
                new Product(104, "Running Shoes", "Fitness"),
                new Product(101, "Wireless Mouse", "Electronics"),
                new Product(103, "Yoga Mat", "Fitness")
        };

        System.out.println("\n--- Binary Search Demo (sorted array) ---");
        Product foundBinary = binarySearch(sortedCatalog, "Wireless Mouse");
        System.out.println("Search result: " + foundBinary);

        Product notFound = binarySearch(sortedCatalog, "Laptop Stand");
        System.out.println("Searching for 'Laptop Stand' -> " + notFound);

        System.out.println("\nAnalysis:");
        System.out.println("Linear search: O(n) - fine for small/unsorted catalogs.");
        System.out.println("Binary search: O(log n) - much faster once catalog grows large,");
        System.out.println("but only works because the data is pre-sorted by name.");
        System.out.println("For a large e-commerce catalog, keeping an index sorted and using");
        System.out.println("binary search (or a HashMap for O(1) exact lookups) scales far better.");
    }
}
