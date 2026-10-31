public class Invoice extends Document implements Approvable,Payable {
    private String customer,itemName,status="PENDING";private int quantity;private double unitPrice;
    public static final double APPROVAL_LIMIT=50000;
    public Invoice(String id,String title,String by,String customer,String item,int qty,double price){super(id,title,by);this.customer=customer;itemName=item;quantity=qty;unitPrice=price;}
    public String getDocumentType(){return "INVOICE";}
    public double getSubtotal(){return quantity*unitPrice;} public double getTotalAmount(){return getSubtotal()*(1+VAT_RATE);}
    public void printContent(){System.out.println("Customer: "+customer);System.out.printf("Item: %s x %d @ %,.2f%n",itemName,quantity,unitPrice);System.out.printf("Subtotal: %,.2f%nVAT 7%%: %,.2f%nTotal: %,.2f%n",getSubtotal(),getSubtotal()*VAT_RATE,getTotalAmount());}
    public boolean approve(String approver){if(getTotalAmount()>APPROVAL_LIMIT){status="REJECTED (total exceeds 50,000.00)";return false;}status="APPROVED by "+approver;return true;}
    public String getStatus(){return status;}
}
