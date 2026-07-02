/**
 * Concrete factory that builds ExcelDocument instances.
 */
public class ExcelDocumentFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}