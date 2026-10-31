public class Intern extends Employee {
    private int workingDays; private double dailyRate;
    public Intern(String empId,String name,int workingDays,double dailyRate) { super(empId,name,0); this.workingDays=workingDays; this.dailyRate=dailyRate; }
    @Override public String getType() { return "Intern"; }
    @Override public double getMonthlyPay() { return workingDays*dailyRate; }
    @Override public String getInfo() { return super.getInfo()+String.format(" (%d days x %,.2f)",workingDays,dailyRate); }
}
