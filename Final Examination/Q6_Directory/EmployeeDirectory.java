import java.util.*;
public class EmployeeDirectory {
    private List<Employee> employees=new ArrayList<>();private Set<String> ids=new HashSet<>();
    public boolean addEmployee(Employee e){if(!ids.add(e.getEmpId()))return false;employees.add(e);return true;}
    public List<Employee> getSortedByName(){List<Employee> copy=new ArrayList<>(employees);copy.sort(Comparator.comparing(Employee::getName,String.CASE_INSENSITIVE_ORDER));return copy;}
    public Map<String,List<Employee>> groupByDepartment(){Map<String,List<Employee>> map=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);for(Employee e:employees)map.computeIfAbsent(e.getDepartment(),k->new ArrayList<>()).add(e);return map;}
    public Pair<String,Double> findHighestPaid(){if(employees.isEmpty())return new Pair<>("",0.0);Employee best=employees.get(0);for(Employee e:employees)if(e.getSalary()>best.getSalary())best=e;return new Pair<>(best.getName(),best.getSalary());}
    public List<Employee> searchByDepartment(String dept){List<Employee> found=new ArrayList<>();for(Employee e:employees)if(e.getDepartment().equalsIgnoreCase(dept))found.add(e);return found;}
    public static <T> void printList(List<T> list){for(T item:list)System.out.println(item);}
}
