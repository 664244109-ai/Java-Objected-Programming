public class TechnicalStaff extends Employee {
    private final int completedProjects;
    private static final double BONUS_PER_PROJECT = 1500.0;
    public TechnicalStaff(String name, double baseSalary, int completedProjects) {
        super(name, baseSalary);
        if (completedProjects < 0) throw new IllegalArgumentException("จำนวนโครงการต้องไม่ติดลบ");
        this.completedProjects=completedProjects;
    }
    @Override public double calculateBonus() { return completedProjects * BONUS_PER_PROJECT; }
    // รับเพดานโบนัส; คำนวณเปอร์เซ็นต์จากฐานเงินเดือนแล้วจำกัดไม่ให้เกินเพดาน
    @Override public double calculateBonus(double percentage) { return calculateBonus(percentage, Double.MAX_VALUE); }
    public double calculateBonus(double percentage, double capAmount) {
        if (percentage < 0 || capAmount < 0) throw new IllegalArgumentException("ค่าโบนัสต้องไม่ติดลบ");
        return Math.min(baseSalary * percentage, capAmount);
    }
}
