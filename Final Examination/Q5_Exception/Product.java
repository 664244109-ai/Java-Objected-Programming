public class Product {
    private String code,name;private int stock;
    public Product(String code,String name,int stock){this.code=code;this.name=name;this.stock=stock;}
    public String getCode(){return code;}public String getName(){return name;}public int getStock(){return stock;}
    public void withdraw(int qty)throws InsufficientStockException{if(qty<=0)throw new IllegalArgumentException("Quantity must be greater than 0.");if(qty>stock)throw new InsufficientStockException("Insufficient stock for "+name+". Requested "+qty+", available "+stock+".");stock-=qty;}
}
