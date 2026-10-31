import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);LeaveAccount leave=new LeaveAccount("Somchai",10);Inventory inv=new Inventory(new Product[]{new Product("P001","Notebook",20),new Product("P002","Pen",100),new Product("P003","Stapler",5)});
        System.out.println("===== Leave & Inventory System =====");System.out.println("--- Part A: Leave Request ---");
        while(true){System.out.printf("Employee: %s (Leave balance: %d days)%n",leave.getName(),leave.getBalance());System.out.print("Enter leave days: ");String raw=sc.nextLine();int days;try{days=Integer.parseInt(raw);}catch(NumberFormatException e){System.out.println("Error: Please enter a whole number.");continue;}try{leave.requestLeave(days);System.out.printf("Leave approved: %d days. Remaining balance: %d days.%n",days,leave.getBalance());break;}catch(Exception e){System.out.println("Error: "+e.getMessage());}}
        System.out.println("--- Part B: Inventory Withdrawal ---");
        while(true){System.out.print("Enter product code (0 to exit): ");String code=sc.nextLine();if(code.equals("0"))break;System.out.print("Enter quantity: ");String raw=sc.nextLine();try{int qty=Integer.parseInt(raw);Product p=inv.findProduct(code);p.withdraw(qty);System.out.printf("Success: Withdrew %d x %s. Remaining stock: %d%n",qty,p.getName(),p.getStock());}catch(NumberFormatException e){System.out.println("Error: Quantity must be a whole number.");}catch(ProductNotFoundException|InsufficientStockException e){System.out.println("Error: "+e.getMessage());}catch(IllegalArgumentException e){System.out.println("Error: "+e.getMessage());}finally{System.out.println("[Transaction finished]");}}
        System.out.println("===== Final Stock =====");for(Product p:inv.getProducts())System.out.printf("%s %s: %d%n",p.getCode(),p.getName(),p.getStock());
    }
}
