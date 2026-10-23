public class Payroll {
    private String employeeId;
    private String name;
    private double baseSalary;
    private double bonus;

    public Payroll(String employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        setName(name);
        setBaseSalary(baseSalary);
        this.bonus = 0;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("แจ้งเตือน: ชื่อต้องไม่เป็นค่าว่าง");
            return;
        }
        this.name = name.trim();
    }

    public String getName() { return name; }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0) {
            System.out.println("แจ้งเตือน: เงินเดือนต้องไม่ติดลบ ระบบกำหนดเป็น 0");
            this.baseSalary = 0;
        } else {
            this.baseSalary = baseSalary;
        }
    }

    public double getBaseSalary() { return baseSalary; }

    public void setBonus(double bonus) {
        this.bonus = bonus < 0 ? 0 : bonus;
    }

    // สูตรตามตัวอย่างต้นฉบับ: ประกันสังคม 5% และภาษี 3% เมื่อฐานเงินเดือนเกิน 20,000 บาท
    public double calculateNetSalary() {
        double socialSecurity = baseSalary * 0.05;
        double tax = (baseSalary > 20000) ? baseSalary * 0.03 : 0;
        return baseSalary + bonus - socialSecurity - tax;
    }

    // แบบฝึกหัดต่อยอด: คำนวณภาษีแบบขั้นบันไดตามอัตราตัวอย่าง
    public double calculateProgressiveTax() {
        double remaining = baseSalary;
        double tax = 0;
        double[] brackets = {10000, 10000, 30000, Double.POSITIVE_INFINITY};
        double[] rates = {0.00, 0.05, 0.10, 0.15};
        for (int i = 0; i < brackets.length && remaining > 0; i++) {
            double taxable = Math.min(remaining, brackets[i]);
            tax += taxable * rates[i];
            remaining -= taxable;
        }
        return tax;
    }

    public void printPayslip() {
        double socialSecurity = baseSalary * 0.05;
        double tax = (baseSalary > 20000) ? baseSalary * 0.03 : 0;
        System.out.println("===== สลิปเงินเดือน =====");
        System.out.println("รหัสพนักงาน: " + employeeId);
        System.out.println("ชื่อ: " + name);
        System.out.printf("เงินเดือนพื้นฐาน: %.2f บาท%n", baseSalary);
        System.out.printf("โบนัส: %.2f บาท%n", bonus);
        System.out.printf("ประกันสังคม: %.2f บาท%n", socialSecurity);
        System.out.printf("ภาษี: %.2f บาท%n", tax);
        System.out.printf("เงินเดือนสุทธิ: %.2f บาท%n", calculateNetSalary());
        System.out.println("===========================");
    }
}
