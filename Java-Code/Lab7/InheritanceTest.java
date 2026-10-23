public class InheritanceTest {
    public static void main(String[] args) {
        Employee manager = new Manager("คุณสมศักดิ์", 30000, 5000);
        Employee intern = new Intern("นักศึกษาฝึกงาน", 15000);
        Employee partTime = new PartTimeEmployee("พนักงานชั่วคราว", 120, 80);
        manager.showInfo(); intern.showInfo(); partTime.showInfo();
    }
}
