public class Inventory {
    private final String productName;
    private int stock;
    public Inventory(String productName, int stock) { this.productName=productName; this.stock=stock; }
    public void withdrawStock(int amount) throws InsufficientStockException {
        if (amount <= 0) throw new IllegalArgumentException("จำนวนเบิกต้องมากกว่า 0");
        if (amount > stock) throw new InsufficientStockException("สินค้า " + productName + " เหลือ " + stock + " ชิ้น แต่ขอเบิก " + amount + " ชิ้น");
        stock -= amount;
        System.out.println("เบิกสินค้า " + amount + " ชิ้นสำเร็จ เหลือ " + stock + " ชิ้น");
    }
}
