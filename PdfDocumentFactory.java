/**
 * Concrete factory that builds PdfDocument instances.
 */
public class PdfDocumentFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}