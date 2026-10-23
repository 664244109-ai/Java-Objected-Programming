public class Employee {
    private final String id, name;
    private final double salary;
    public Employee(String id, String name, double salary) { this.id=id; this.name=name; this.salary=salary; }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    @Override public String toString() { return id + " | " + name + " | " + salary + " บาท"; }
}
