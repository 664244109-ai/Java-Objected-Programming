import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Employee {
    private final String employeeId;
    private final String name;
    private final String department;
    private final double salary;
    private final LocalDate startDate;

    public Employee(String employeeId, String name, String department, double salary, LocalDate startDate) {
        if (salary < 0) throw new IllegalArgumentException("เงินเดือนต้องไม่ติดลบ");
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.startDate = startDate;
    }

    public int calculateYearsOfService() {
        return Period.between(startDate, LocalDate.now()).getYears();
    }

    public void showInfo() {
        System.out.println("รหัสพนักงาน: " + employeeId);
        System.out.println("ชื่อ: " + name);
        System.out.println("แผนก: " + department);
        System.out.printf("เงินเดือน: %.2f บาท%n", salary);
        System.out.println("วันเริ่มงาน: " + startDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("อายุงาน: " + calculateYearsOfService() + " ปี");
        System.out.println("------------------------------");
    }
}
