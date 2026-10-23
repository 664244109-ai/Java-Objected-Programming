public class Employee {
    protected String name;
    protected double baseSalary;
    public Employee(String name, double baseSalary) { this.name=name; this.baseSalary=baseSalary; }
    public double calculateSalary() { return baseSalary; }
    public void showInfo() {
        System.out.println("ชื่อ: " + name);
        System.out.printf("เงินเดือน: %.2f บาท%n------------------------------%n", calculateSalary());
    }
}
