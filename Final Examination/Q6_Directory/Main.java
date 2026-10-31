import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);EmployeeDirectory dir=new EmployeeDirectory();System.out.println("===== Employee Directory =====");System.out.print("Enter number of employees: ");int n=Integer.parseInt(sc.nextLine());
        for(int i=1;i<=n;i++){System.out.printf("Employee %d (id,name,department,salary): ",i);String[] p=sc.nextLine().split(",");if(p.length!=4)continue;String id=p[0].trim(),name=p[1].trim(),dept=p[2].trim();double salary=Double.parseDouble(p[3].trim());if(!dir.addEmployee(new Employee(id,name,dept,salary)))System.out.println("Duplicate ID "+id+" - skipped.");}
        System.out.println("\n----- All Employees (sorted by name) -----");EmployeeDirectory.printList(dir.getSortedByName());
        System.out.println("\n----- Department Summary -----");for(Map.Entry<String,List<Employee>> x:dir.groupByDepartment().entrySet()){double total=0;for(Employee e:x.getValue())total+=e.getSalary();System.out.printf("%s: %d employee(s), Total: %,.2f, Average: %,.2f%n",x.getKey(),x.getValue().size(),total,total/x.getValue().size());}
        Pair<String,Double> highest=dir.findHighestPaid();System.out.printf("Highest Paid: %s (%,.2f)%n",highest.getKey(),highest.getValue());System.out.print("Enter department to search: ");String dept=sc.nextLine();List<Employee> found=dir.searchByDepartment(dept);if(found.isEmpty())System.out.println("No employees found in department: "+dept);else{String display=found.get(0).getDepartment();System.out.printf("----- Employees in %s (%d) -----%n",display,found.size());EmployeeDirectory.printList(found);}
    }
}
