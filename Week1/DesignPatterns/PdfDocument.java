
public class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a PDF document (.pdf)...");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document using PDF format.");
    }
}
