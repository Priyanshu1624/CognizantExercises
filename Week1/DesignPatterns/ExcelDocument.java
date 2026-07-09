
public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening an Excel document (.xlsx)...");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel document using XLSX format.");
    }
}
