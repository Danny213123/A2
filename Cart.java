import java.util.ArrayList;

public class Cart {
    ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    
    public Cart (){}
    
    public void addItem(Product item, String productOptions){
        cartItems.add(new CartItem(item, productOptions));
    }
    
    public void removeItem(CartItem item){
        cartItems.remove(item);
    }
    
    public ArrayList<CartItem> getItems(){
        return cartItems;
    }
    
    public void printCart(){
        for(CartItem x:cartItems){
            x.getItem().print();
        }
    }
}