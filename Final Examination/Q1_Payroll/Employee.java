public class Employee {
    private String empId, name, department;
    private double baseSalary;
    private int overtimeHours;
    private static int employeeCount = 0;

    public Employee(String empId, String name, String department, double baseSalary, int overtimeHours) {
        this.empId = empId; this.name = name; this.department = department;
        setBaseSalary(baseSalary); setOvertimeHours(overtimeHours); employeeCount++;
    }
    public String getEmpId() { return empId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getBaseSalary() { return baseSalary; }
    public int getOvertimeHours() { return overtimeHours; }
    public static int getEmployeeCount() { return employeeCount; }
    public void setBaseSalary(double value) { if (value < 0) System.out.println("Error: Base salary cannot be negative."); else baseSalary = value; }
    public void setOvertimeHours(int value) { if (value < 0) System.out.println("Error: Overtime hours cannot be negative."); else overtimeHours = value; }
    public double calculateOvertimePay() { return baseSalary / 30.0 / 8.0 * 1.5 * overtimeHours; }
    public double calculateSocialSecurity() { return Math.min(baseSalary * 0.05, 750.0); }
    public double calculateNetSalary() { return baseSalary + calculateOvertimePay() - calculateSocialSecurity(); }
    public void printPayslip() {
        System.out.println("----- Payslip -----");
        System.out.println("Employee ID: " + empId); System.out.println("Name: " + name); System.out.println("Department: " + department);
        System.out.printf("Base Salary: %,.2f%nOvertime Pay: %,.2f%nSocial Security: %,.2f%nNet Salary: %,.2f%n", baseSalary, calculateOvertimePay(), calculateSocialSecurity(), calculateNetSalary());
    }
}
