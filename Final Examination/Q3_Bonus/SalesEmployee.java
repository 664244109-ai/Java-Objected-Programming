public class SalesEmployee extends Employee {
    private double salesAmount;
    public SalesEmployee(String name,double salary,double salesAmount){super(name,salary);this.salesAmount=salesAmount;}
    @Override public String getType(){return "Sales";}
    @Override public double calculateBonus(){return salary+salesAmount*0.05;}
}
