public class Employee {
    protected String name; protected double salary;
    public Employee(String name,double salary){this.name=name;this.salary=salary;}
    public String getName(){return name;}
    public String getType(){return "Employee";}
    public double calculateBonus(){return salary;}
    public double calculateBonus(char grade){char g=Character.toUpperCase(grade);double factor=g=='A'?1.5:g=='B'?1.2:g=='C'?1.0:0.0;return calculateBonus()*factor;}
}
