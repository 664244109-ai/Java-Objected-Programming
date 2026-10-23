public class PayrollTest {
    public static void main(String[] args) {
        Payroll p1 = new Payroll("E001", "สมชาย ใจดี", 25000);
        p1.setBonus(2000);
        p1.printPayslip();
        System.out.printf("ภาษีแบบขั้นบันได (แบบฝึกหัดต่อยอด): %.2f บาท%n", p1.calculateProgressiveTax());

        Payroll p2 = new Payroll("E002", "สมหญิง รักงาน", -5000);
        p2.printPayslip();
        System.out.printf("ภาษีแบบขั้นบันไดของ p2: %.2f บาท%n", p2.calculateProgressiveTax());

        p1.setName("   "); // ทดสอบป้องกันชื่อว่าง
        System.out.println("ชื่อหลังทดสอบ setName: " + p1.getName());
    }
}
