public class Inventory {
    private Product[] products;
    public Inventory(Product[] products){this.products=products;}
    public Product[] getProducts(){return products;}
    public Product findProduct(String code)throws ProductNotFoundException{for(Product p:products)if(p.getCode().equalsIgnoreCase(code))return p;throw new ProductNotFoundException("Product "+code+" not found.");}
}
