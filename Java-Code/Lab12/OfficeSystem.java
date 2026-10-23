import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class OfficeSystem {
    private final Map<String, Employee> employees = new LinkedHashMap<>();
    public void addEmployee(Employee e) {
        if (employees.containsKey(e.getId())) throw new IllegalArgumentException("รหัสพนักงานซ้ำ: " + e.getId());
        employees.put(e.getId(), e);
    }
    public Employee findById(String id) { return employees.get(id); }
    public double getTotalPayroll() { return employees.values().stream().mapToDouble(Employee::calculateSalary).sum(); }
    public void printReport() {
        System.out.println("========== รายงานเงินเดือนพนักงาน ==========");
        employees.values().forEach(Employee::showInfo);
        System.out.printf("รวมค่าใช้จ่ายเงินเดือนทั้งหมด: %.2f บาท%n", getTotalPayroll());
    }
    // CSV: type,id,name,baseSalary,allowance OR type,id,name,hourlyRate,hoursWorked
    public void importCsv(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (int i=0; i<lines.size(); i++) {
            String line=lines.get(i).trim();
            if (line.isEmpty() || line.startsWith("type,")) continue;
            String[] p=line.split(",", -1);
            try {
                switch (p[0].trim().toUpperCase(Locale.ROOT)) {
                    case "STAFF": addEmployee(new Staff(p[1].trim(), p[2].trim(), Double.parseDouble(p[3].trim()))); break;
                    case "MANAGER": addEmployee(new Manager(p[1].trim(), p[2].trim(), Double.parseDouble(p[3].trim()), Double.parseDouble(p[4].trim()))); break;
                    case "PARTTIME": addEmployee(new PartTimeEmployee(p[1].trim(), p[2].trim(), Double.parseDouble(p[3].trim()), Double.parseDouble(p[4].trim()))); break;
                    default: throw new IllegalArgumentException("ไม่รู้จักประเภท " + p[0]);
                }
            } catch (RuntimeException ex) { throw new IllegalArgumentException("ข้อมูล CSV แถว " + (i+1) + " ไม่ถูกต้อง: " + ex.getMessage(), ex); }
        }
    }
    public void exportCsv(Path path) throws IOException {
        List<String> rows = new ArrayList<>(); rows.add("type,id,name,value1,value2");
        for (Employee e : employees.values()) {
            if (e instanceof Manager) { Manager manager = (Manager) e; rows.add(String.join(",", "MANAGER", csv(e.id), csv(e.name), Double.toString(e.baseSalary), Double.toString(manager.calculateSalary()-e.baseSalary))); }
            else if (e instanceof PartTimeEmployee) { PartTimeEmployee partTime = (PartTimeEmployee) e; rows.add(String.join(",", "PARTTIME", csv(e.id), csv(e.name), Double.toString(partTime.getHourlyRate()), Double.toString(partTime.getHoursWorked()))); }
            else rows.add(String.join(",", "STAFF", csv(e.id), csv(e.name), Double.toString(e.baseSalary), ""));
        }
        Files.write(path, rows, StandardCharsets.UTF_8);
    }
    private String csv(String value) { return "\"" + value.replace("\"", "\"\"") + "\""; }
    public void exportSummary(Path path) throws IOException {
        String report = employees.values().stream().map(e -> String.format("%s,%s,%s,%.2f บาท", e.id, e.name, e.getPosition(), e.calculateSalary())).collect(Collectors.joining(System.lineSeparator()));
        report += System.lineSeparator() + String.format("รวมเงินเดือน,%.2f บาท%n", getTotalPayroll());
        Files.writeString(path, report, StandardCharsets.UTF_8);
    }
}
