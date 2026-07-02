/**
 * Abstract Factory class — this IS the "Factory Method" pattern's defining
 * feature. Each concrete subclass below overrides createDocument() to
 * decide which exact Document subtype gets built.
 *
 * This is different from a simple "static factory with a switch statement":
 * here, the decision of WHICH class to instantiate is pushed down into
 * separate subclasses, following the Open/Closed Principle — adding a new
 * document type means adding a new factory subclass, with ZERO changes to
 * existing factory code.
 */
public abstract class DocumentFactory {

    // The "factory method" itself — subclasses must implement this.
    public abstract Document createDocument();

    // A shared workflow that uses the factory method. This demonstrates
    // why the pattern is useful: this method works for ANY document type,
    // because it only depends on createDocument(), not on any concrete class.
    public Document openNewDocument() {
        Document doc = createDocument();
        doc.open();
        return doc;
    }
}