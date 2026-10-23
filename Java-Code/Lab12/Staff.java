public class Staff extends Employee {
    public Staff(String id, String name, double baseSalary) { super(id, name, baseSalary); }
    @Override public double calculateSalary() { return baseSalary; }
    @Override public String getPosition() { return "พนักงานทั่วไป"; }
}
