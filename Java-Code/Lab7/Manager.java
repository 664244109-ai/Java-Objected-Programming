public class Manager extends Employee {
    private final double positionAllowance;
    public Manager(String name, double baseSalary, double positionAllowance) {
        super(name, baseSalary);
        this.positionAllowance = positionAllowance;
    }
    @Override public double calculateSalary() { return baseSalary + positionAllowance; }
    @Override public void showInfo() {
        System.out.println("ชื่อ: " + name + " (ผู้จัดการ)");
        System.out.printf("เงินเดือนพื้นฐาน: %.2f บาท%nค่าตำแหน่ง: %.2f บาท%nเงินเดือนรวม: %.2f บาท%n------------------------------%n",
                baseSalary, positionAllowance, calculateSalary());
    }
}
