public class ProductSearchDemo {

    /**
     * Linear Search — checks each element in order. No sorting required.
     */
    public static int linearSearch(Product[] products, int targetId) {
        int comparisons = 0;
        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductId() == targetId) {
                System.out.println("[Linear Search] Comparisons made: " + comparisons);
                return i;
            }
        }
        System.out.println("[Linear Search] Comparisons made: " + comparisons);
        return -1;
    }

    /**
     * Binary Search — REQUIRES the array to already be sorted by productId.
     */
    public static int binarySearch(Product[] products, int targetId) {
        int low = 0;
        int high = products.length - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int midId = products[mid].getProductId();

            if (midId == targetId) {
                System.out.println("[Binary Search] Comparisons made: " + comparisons);
                return mid;
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("[Binary Search] Comparisons made: " + comparisons);
        return -1;
    }

    public static void main(String[] args) {

        // Sorted by productId — required for Binary Search to work correctly.
        Product[] catalog = {
                new Product(1, "USB Cable", "Accessories"),
                new Product(2, "Wireless Mouse", "Accessories"),
                new Product(3, "Mechanical Keyboard", "Accessories"),
                new Product(4, "HDMI Adapter", "Accessories"),
                new Product(5, "Laptop Stand", "Office"),
                new Product(6, "Webcam", "Electronics"),
                new Product(7, "Bluetooth Speaker", "Electronics"),
                new Product(8, "Power Bank", "Electronics"),
                new Product(9, "Monitor", "Electronics"),
                new Product(10, "Router", "Networking")
        };

        int targetId = 8;

        System.out.println("Searching for productId: " + targetId + "\n");

        int linearIndex = linearSearch(catalog, targetId);
        if (linearIndex != -1) {
            System.out.println("Found via Linear Search: " + catalog[linearIndex]);
        }

        System.out.println();

        int binaryIndex = binarySearch(catalog, targetId);
        if (binaryIndex != -1) {
            System.out.println("Found via Binary Search: " + catalog[binaryIndex]);
        }

        System.out.println("\n--- Searching for a non-existent productId (99) ---");
        linearSearch(catalog, 99);
        binarySearch(catalog, 99);
    }
}