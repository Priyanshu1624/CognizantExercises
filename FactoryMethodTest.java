/**
 * Test class for Exercise 2 (Factory Method Pattern).
 * Per handbook: "Create a test class to demonstrate the creation of
 * different document types using the factory method."
 */
public class FactoryMethodTest {

    public static void main(String[] args) {

        System.out.println("--- Creating a Word document ---");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.openNewDocument();
        System.out.println("Actual class created: " + wordDoc.getClass().getSimpleName());

        System.out.println("\n--- Creating a PDF document ---");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.openNewDocument();
        System.out.println("Actual class created: " + pdfDoc.getClass().getSimpleName());

        System.out.println("\n--- Creating an Excel document ---");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.openNewDocument();
        System.out.println("Actual class created: " + excelDoc.getClass().getSimpleName());
    }
}