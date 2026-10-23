public class PartTimeEmployee extends Employee {
    private final double hourlyRate, hoursWorked;
    public PartTimeEmployee(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name, 0);
        if (hourlyRate < 0 || hoursWorked < 0) throw new IllegalArgumentException("อัตราค่าจ้างและชั่วโมงต้องไม่ติดลบ");
        this.hourlyRate=hourlyRate; this.hoursWorked=hoursWorked;
    }
    public double getHourlyRate() { return hourlyRate; }
    public double getHoursWorked() { return hoursWorked; }
    @Override public double calculateSalary() { return hourlyRate * hoursWorked; }
    @Override public String getPosition() { return "พนักงาน Part-time"; }
}
