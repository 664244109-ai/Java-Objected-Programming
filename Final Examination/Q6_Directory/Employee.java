public class Employee {
    private String empId,name,department;private double salary;
    public Employee(String empId,String name,String department,double salary){this.empId=empId;this.name=name;this.department=department;this.salary=salary;}
    public String getEmpId(){return empId;}public String getName(){return name;}public String getDepartment(){return department;}public double getSalary(){return salary;}
    @Override public String toString(){return String.format("%s | %s | %s | %,.2f",empId,name,department,salary);}
}
