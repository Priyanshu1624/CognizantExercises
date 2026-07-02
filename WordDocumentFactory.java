/**
 * Concrete factory that builds WordDocument instances.
 */
public class WordDocumentFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}