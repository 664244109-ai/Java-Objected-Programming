import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);Employee[] es={new Employee("Somchai",20000),new SalesEmployee("Malee",18000,200000),new Manager("Suda",50000)};char[] grades=new char[es.length];
        System.out.println("===== Bonus Calculation System =====");
        for(int i=0;i<es.length;i++){System.out.printf("Enter grade for %s (%s) [A/B/C/D]: ",es[i].getName(),es[i].getType());String s=sc.nextLine();grades[i]=s.isEmpty()?'D':Character.toUpperCase(s.charAt(0));}
        System.out.println("----- Bonus Report -----");double total=0;
        for(int i=0;i<es.length;i++){double base=es[i].calculateBonus(),fin=es[i].calculateBonus(grades[i]);System.out.printf("%s | %s | Base Bonus: %,.2f | Grade: %c | Final Bonus: %,.2f%n",es[i].getName(),es[i].getType(),base,grades[i],fin);total+=fin;}
        System.out.println("------------------------------");System.out.printf("Total Bonus: %,.2f%n",total);
    }
}
