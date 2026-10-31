public class Manager extends Employee {
    public Manager(String name,double salary){super(name,salary);}
    @Override public String getType(){return "Manager";}
    @Override public double calculateBonus(){return salary*2;}
}
