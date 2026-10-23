public class SalesStaff extends Employee {
    private final double salesAmount;
    public SalesStaff(String name, double baseSalary, double salesAmount) {
        super(name, baseSalary);
        if (salesAmount < 0) throw new IllegalArgumentException("ยอดขายต้องไม่ติดลบ");
        this.salesAmount=salesAmount;
    }
    @Override public double calculateBonus() { return salesAmount * 0.02; }
}
