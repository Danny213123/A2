import java.util.ArrayList;

/* Cart Class

    Class objects start with an array list for cart items
*/

public class Cart {
    ArrayList<CartItem> cartItems = new ArrayList<CartItem>();

    // Empty constructor
    public Cart (){}

    // Add Item
    public void addItem(Product item, String productOptions){
        cartItems.add(new CartItem(item, productOptions));
    }

    // Remove item from cart
    public void removeItem(CartItem item){
        cartItems.remove(item);
    }

    // Get item from cart
    public ArrayList<CartItem> getItems(){
        return cartItems;
    }

    // Print cart items
    public void printCart(){
        for(CartItem x:cartItems){
            x.getItem().print();
        }
    }
}