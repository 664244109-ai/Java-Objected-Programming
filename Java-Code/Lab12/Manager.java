public class Manager extends Employee {
    private final double allowance;
    public Manager(String id, String name, double baseSalary, double allowance) { super(id, name, baseSalary); this.allowance=allowance; }
    @Override public double calculateSalary() { return baseSalary + allowance; }
    @Override public String getPosition() { return "ผู้จัดการ"; }
}
