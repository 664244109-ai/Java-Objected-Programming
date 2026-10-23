import java.nio.file.Path;
public class DocumentTest {
    public static void main(String[] args) throws Exception {
        Report report = new Report("รายงานประจำเดือน", "ฝ่าย HR", "พนักงาน 12 คน และรายการเอกสารครบถ้วน");
        report.printContent();
        Memo memo = new Memo("บันทึกแจ้งเตือน", "ฝ่าย HR", "แจ้งวันหยุดประจำเดือน");
        Invoice invoice = new Invoice("ใบแจ้งหนี้ #001", "ฝ่ายบัญชี", 15000);
        memo.printContent(); invoice.printContent();
        invoice.markAsPaid(); invoice.printContent();
        invoice.printToFile(Path.of("invoice.txt"));
    }
}
