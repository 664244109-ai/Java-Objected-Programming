public class Memo extends Document {
    private final String message;
    public Memo(String title, String createdBy, String message) { super(title, createdBy); this.message=message; }
    @Override public void printContent() { showHeader(); System.out.println("ข้อความ: " + message); System.out.println("------------------------------"); }
}
