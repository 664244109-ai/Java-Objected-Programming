public abstract class Employee implements Payable {
    protected String id, name;
    protected double baseSalary;
    public Employee(String id, String name, double baseSalary) {
        if (baseSalary < 0) throw new IllegalArgumentException("เงินเดือนต้องไม่ติดลบ");
        this.id=id; this.name=name; this.baseSalary=baseSalary;
    }
    public abstract String getPosition();
    public String getId() { return id; }
    public String getName() { return name; }
    public void showInfo() { System.out.printf("[%s] %s (%s) เงินเดือน: %.2f บาท%n", id, name, getPosition(), calculateSalary()); }
}
