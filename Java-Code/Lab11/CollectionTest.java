import java.util.*;
import java.util.stream.Collectors;
public class CollectionTest {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("E001", "สมชาย", 25000));
        employees.add(new Employee("E002", "สมหญิง", 32000));
        employees.add(new Employee("E003", "สมปอง", 18000));
        Map<String, Employee> byId = new HashMap<>();
        Map<String, Integer> departmentCount = new HashMap<>();
        departmentCount.put("ฝ่ายขาย", 5); departmentCount.put("บัญชี", 3); departmentCount.put("ไอที", 4);
        for (Employee e : employees) byId.put(e.getId(), e);
        System.out.println("พนักงานรหัส E002: " + byId.get("E002"));
        System.out.println("พนักงานเงินเดือนมากกว่า 20,000 บาท:");
        employees.stream().filter(e -> e.getSalary() > 20000).forEach(System.out::println);
        List<Employee> sorted = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
        System.out.println("เรียงตามเงินเดือนมากไปน้อย:"); sorted.forEach(System.out::println);
        System.out.println("จำนวนพนักงานแต่ละแผนก: " + departmentCount);
    }
}
