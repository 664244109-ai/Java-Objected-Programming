public class LeaveAccount {
    private String name;private int balance;
    public LeaveAccount(String name,int balance){this.name=name;this.balance=balance;}
    public String getName(){return name;}public int getBalance(){return balance;}
    public void requestLeave(int days)throws InsufficientLeaveException{if(days<=0)throw new IllegalArgumentException("Leave days must be greater than 0.");if(days>balance)throw new InsufficientLeaveException("Insufficient leave balance. Requested "+days+" days, available "+balance+" days.");balance-=days;}
}
