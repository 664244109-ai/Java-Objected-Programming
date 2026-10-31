public class Employee {
    protected String empId, name; protected double baseSalary;
    public Employee(String empId,String name,double baseSalary) { this.empId=empId; this.name=name; this.baseSalary=baseSalary; }
    public String getType() { return "Employee"; }
    public double getMonthlyPay() { return baseSalary; }
    public String getInfo() { return String.format("[%s] %s | %s | Monthly Pay: %,.2f",getType(),empId,name,getMonthlyPay()); }
}
