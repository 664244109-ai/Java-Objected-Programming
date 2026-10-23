import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class Invoice extends Document implements Payable, Printable {
    private final double amount;
    private boolean paid;
    public Invoice(String title, String createdBy, double amount) {
        super(title, createdBy);
        if (amount < 0) throw new IllegalArgumentException("จำนวนเงินต้องไม่ติดลบ");
        this.amount=amount;
    }
    @Override public void printContent() {
        showHeader(); System.out.printf("จำนวนเงิน: %.2f บาท | สถานะ: %s%n", amount, paid ? "ชำระแล้ว" : "ยังไม่ชำระ");
        System.out.println("------------------------------");
    }
    @Override public double getAmount() { return amount; }
    @Override public void markAsPaid() { paid=true; }
    @Override public void printToFile(Path path) throws IOException {
        String text = "เอกสาร: " + title + "\nผู้จัดทำ: " + createdBy + String.format("\nจำนวนเงิน: %.2f บาท\nสถานะ: %s%n", amount, paid ? "ชำระแล้ว" : "ยังไม่ชำระ");
        Files.writeString(path, text);
    }
}
