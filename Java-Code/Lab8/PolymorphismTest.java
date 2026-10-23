import java.util.ArrayList;
import java.util.List;
public class PolymorphismTest {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("พนักงานทั่วไป", 18000));
        employees.add(new SalesStaff("พนักงานขาย", 15000, 100000));
        employees.add(new Manager("ผู้จัดการ", 35000));
        employees.add(new TechnicalStaff("ช่างเทคนิค", 28000, 4));
        for (Employee e : employees) e.showBonus();
        TechnicalStaff tech = (TechnicalStaff) employees.get(3);
        System.out.printf("โบนัสเทคนิค 10%% จำกัด 2,000 บาท: %.2f บาท%n", tech.calculateBonus(0.10, 2000));
    }
}
