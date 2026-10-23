import java.time.LocalDate;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee e1 = new Employee("E001", "สมชาย ใจดี", "บัญชี", 25000, LocalDate.of(2020, 3, 15));
        Employee e2 = new Employee("E002", "สมหญิง รักงาน", "ฝ่ายขาย", 28000, LocalDate.of(2023, 8, 1));
        e1.showInfo();
        e2.showInfo();
    }
}
