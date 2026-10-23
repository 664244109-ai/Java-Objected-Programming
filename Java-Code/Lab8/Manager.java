public class Manager extends Employee {
    public Manager(String name, double baseSalary) { super(name, baseSalary); }
    @Override public double calculateBonus() { return baseSalary * 0.15; }
}
