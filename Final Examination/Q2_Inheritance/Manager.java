public class Manager extends Employee {
    private double positionAllowance;
    public Manager(String empId,String name,double baseSalary,double positionAllowance) { super(empId,name,baseSalary); this.positionAllowance=positionAllowance; }
    @Override public String getType() { return "Manager"; }
    @Override public double getMonthlyPay() { return super.getMonthlyPay()+positionAllowance; }
    @Override public String getInfo() { return super.getInfo()+String.format(" (Allowance: %,.2f)",positionAllowance); }
}
