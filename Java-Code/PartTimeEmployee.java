public class PartTimeEmployee extends Employee {
    private final double hourlyRate;
    private final double hoursWorked;
    public PartTimeEmployee(String name, double hourlyRate, double hoursWorked) {
        super(name, 0);
        if (hourlyRate < 0 || hoursWorked < 0) throw new IllegalArgumentException("อัตราค่าจ้างและชั่วโมงต้องไม่ติดลบ");
        this.hourlyRate=hourlyRate; this.hoursWorked=hoursWorked;
    }
    @Override public double calculateSalary() { return hourlyRate * hoursWorked; }
    @Override public void showInfo() {
        System.out.println("ชื่อ: " + name + " (พนักงาน Part-time)");
        System.out.printf("ชั่วโมงทำงาน: %.1f ชั่วโมง x %.2f บาท%nเงินเดือน: %.2f บาท%n------------------------------%n",
                hoursWorked, hourlyRate, calculateSalary());
    }
}
