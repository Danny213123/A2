public class CartItem{
    private Product item;
    private String productOptions;

    public CartItem(Product product_item, String product_options){
        this.item = product_item;
        this.productOptions = product_options;
    }
    
    public Product getItem(){
        return this.item;
    }
    
    public String getProductOptions(){
        return this.productOptions;
    }
}