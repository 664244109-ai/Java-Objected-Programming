public class Main {
    public static void main(String[] args) {
        Employee[] employees={new Employee("E001","Somchai Jaidee",25000),new Manager("M001","Suda Rakdee",50000,10000),new Intern("I001","Anan Sukjai",20,500)};
        System.out.println("===== Employee Types ====="); double total=0;
        for(Employee e:employees){System.out.println(e.getInfo());total+=e.getMonthlyPay();}
        System.out.println("------------------------------");System.out.printf("Total Payroll: %,.2f%n",total);
        System.out.println("Is Manager an Employee? "+(employees[1] instanceof Employee));
    }
}
