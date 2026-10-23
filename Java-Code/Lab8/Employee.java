public class Employee {
    protected String name;
    protected double baseSalary;
    public Employee(String name, double baseSalary) { this.name=name; this.baseSalary=baseSalary; }
    public double calculateBonus() { return baseSalary * 0.05; }
    public double calculateBonus(double percentage) {
        if (percentage < 0) throw new IllegalArgumentException("เปอร์เซ็นต์ต้องไม่ติดลบ");
        return baseSalary * percentage;
    }
    public void showBonus() { System.out.printf("%s ได้รับโบนัส: %.2f บาท%n", name, calculateBonus()); }
}
