import java.nio.file.Path;
public class OfficeSystemTest {
    public static void main(String[] args) {
        OfficeSystem office = new OfficeSystem();
        try {
            Path input = Path.of("employees.csv");
            if (java.nio.file.Files.exists(input)) office.importCsv(input);
            else {
                office.addEmployee(new Staff("E001", "สมชาย ใจดี", 20000));
                office.addEmployee(new Manager("E002", "สมหญิง บริหารเก่ง", 35000, 8000));
                office.addEmployee(new PartTimeEmployee("E003", "มะลิ", 120, 80));
            }
            Employee found = office.findById("E002");
            if (found != null) { System.out.println("ค้นหารหัส E002 พบ:"); found.showInfo(); }
            office.printReport();
            office.exportCsv(Path.of("employees_export.csv"));
            office.exportSummary(Path.of("payroll_summary.txt"));
        } catch (Exception e) {
            System.out.println("เกิดข้อผิดพลาดในการจัดการพนักงาน: " + e.getMessage());
        }
    }
}
