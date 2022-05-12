/* Cart item Class

    Cart item has a reference to product class and keeps product options
*/

public class CartItem{
    private Product item;
    private String productOptions;

    /**
     * Cart item constructor
     * @param product_item
     * @param product_options
     */
    public CartItem(Product product_item, String product_options){
        this.item = product_item;
        this.productOptions = product_options;
    }

    // Get cart item
    public Product getItem(){
        return this.item;
    }

    // Get product option
    public String getProductOptions(){
        return this.productOptions;
    }
}