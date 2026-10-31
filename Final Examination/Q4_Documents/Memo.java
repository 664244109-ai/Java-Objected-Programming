public class Memo extends Document implements Approvable {
    private String to,message,status="PENDING";
    public Memo(String id,String title,String by,String to,String message){super(id,title,by);this.to=to;this.message=message;}
    public String getDocumentType(){return "MEMO";}
    public void printContent(){System.out.println("To: "+to);System.out.println("Message: "+message);}
    public boolean approve(String approver){status="APPROVED by "+approver;return true;}
    public String getStatus(){return status;}
}
