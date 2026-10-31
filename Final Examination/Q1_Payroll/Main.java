import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); Employee[] employees = new Employee[2];
        System.out.println("===== Payroll System =====");
        for (int i=0;i<2;i++) {
            System.out.println("[Employee " + (i+1) + "]");
            System.out.print("Employee ID: "); String id=sc.nextLine(); System.out.print("Name: "); String name=sc.nextLine();
            System.out.print("Department: "); String dept=sc.nextLine(); System.out.print("Base Salary: "); double salary=Double.parseDouble(sc.nextLine());
            int hours;
            while (true) {
                System.out.print("Overtime Hours: ");
                String input = sc.nextLine().trim();
                try {
                    hours = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a whole number.");
                }
            }
            employees[i]=new Employee(id,name,dept,salary,hours);
        }
        double total=0; for (Employee e:employees) { System.out.println(); e.printPayslip(); total+=e.calculateNetSalary(); }
        System.out.println("===== Summary ====="); System.out.println("Total employees created: " + Employee.getEmployeeCount()); System.out.printf("Total net salary: %,.2f%n",total);
        System.out.println("Test: setBaseSalary(-1000) on " + employees[0].getEmpId()); employees[0].setBaseSalary(-1000);
        System.out.printf("Base salary after test: %,.2f%n",employees[0].getBaseSalary());
    }
}
