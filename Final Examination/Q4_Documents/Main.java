public class Main {
    public static void main(String[] args){
        Document[] docs={new Memo("MEM-001","Staff Meeting","HR","All Staff","Meeting on Friday at 10:00 in Room 301"),new Invoice("INV-001","Office Supplies","Finance","ABC Co., Ltd.","A4 Paper",50,120),new Invoice("INV-002","Laptop Purchase","Finance","XYZ Co., Ltd.","Laptop",3,25000)};
        System.out.println("===== Office Document System =====");double approved=0;
        for(Document d:docs){d.printHeader();d.printContent();if(d instanceof Approvable){Approvable a=(Approvable)d;a.approve("Somchai");System.out.println("Status: "+a.getStatus());if(a.getStatus().startsWith("APPROVED")&&d instanceof Payable)approved+=((Payable)d).getTotalAmount();}System.out.println("----------------------------------------");}
        System.out.printf("Total Approved Invoice Amount: %,.2f%n",approved);
    }
}
