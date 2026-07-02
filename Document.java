/**
 * Exercise 2: Implementing the Factory Method Pattern (Cognizant DN5.0).
 *
 * Scenario: "You are developing a document management system that needs to
 * create different types of documents (e.g., Word, PDF, Excel)."
 *
 * Document is the common interface every document type must implement.
 */
public interface Document {
    void open();
}