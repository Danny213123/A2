import java.util.Arrays;
import java.util.Scanner;
/* A shoe is a product with additional info (size and colour)

 	A Shoe comes in 5 different sizes (6, 7, 8, 9, 10)

	A Shoe comes with 2 different colours (black and brown)


*/
public class Shoes extends Product
{

    // private variable size and colour of shoe
    private int size;
    private String colour;

    // Stock related information
    int[] black_stock;
    int[] brown_stock;

    /** ProductOrder Constructor
     * @param String name			- Name of item
     * @param String Id				- Id of item
     * @param double price			- Price of item
     * @param int[][] stock 		- Stock of item
     */
    public Shoes(String name, String id, double price, int[][] stock)
    {
        // Make use of the constructor in the super class Product. Initialize additional Shoe instance variables.
        // Set category to SHOES
        super.setId(id);
        super.setPrice(price);
        super.setName(name);
        super.setCategory(Product.Category.SHOES);
        this.black_stock = stock[0];
        this.brown_stock = stock[1];
    }
    
    // Check if colour is valid
    public boolean validOptions(String productOptions)
    {
        // check productOptions for colour (Black && Brown)
        // Check to see if the size inputed is valid (6-9 inclusive)
        // if all of these return a true, then return true, other wise return false

        if (productOptions == null || productOptions.equals(""))
            return false;
        Scanner options = new Scanner(productOptions);
        if (!options.hasNext()) return false;
        String size  = options.next();
        if (!options.hasNext()) return false;
        String color = options.next();
        if (color == null) return false;
        return (size.equalsIgnoreCase("6") || size.equals("7") || size.equals("8") || size.equals("9") || size.equals("10")) &&
                (color.equalsIgnoreCase("Black") || color.equalsIgnoreCase("Brown"));
    }

    /*
     * Print stock count from array with category COLOUR
     */
    public String getArray(String colour)
    {
        if (colour.equalsIgnoreCase("Black")){
            return Arrays.toString(this.black_stock);
        } else {
            return Arrays.toString(this.brown_stock);
        }
    }

    // Override getStockCount() in super class.
    public int getStockCount(String productOptions)
    {
        // Use the productOptions to check for (and return) the number of stock for "Black" shoes, etc
        // Use the variables black_stock and brown_stock at the top.

        // Split product options
        String[] options = productOptions.split(" ");

        int size = Integer.parseInt(options[0]);
        String colour = options[1];

        // If colour is black
        if (colour.equalsIgnoreCase("Black")){

            // Decrease stock count for respective size
            if (size == 6){
                return this.black_stock[0];
            } else if (size == 7){
                return this.black_stock[1];
            } else if (size == 8){
                return this.black_stock[2];
            } else if (size == 8){
                return this.black_stock[3];
            } else {
                return this.black_stock[4];
            }

            // If colour is brown
        } else {

            // Decrease stock count for respective size
            if (size == 6){
                return this.brown_stock[0];
            } else if (size == 7){
                return this.brown_stock[1];
            } else if (size == 8){
                return this.brown_stock[2];
            } else if (size == 8){
                return this.brown_stock[3];
            } else {
                return this.brown_stock[4];
            }
        }
    }

    /*
     * Set stock of shoe with colour to stockCount
     */
    public void setStockCount(int stockCount, String productOptions)
    {
        // Use the productOptions to check for (and set) the number of stock for "Black" shoes, etc
        // Use the variables black_stock and brown_stock at the top.
    }

    /*
     * When a shoe is ordered, the stock of the specific colour of shoe will decrease
     */
    public void reduceStockCount(String productOptions)
    {
        // Use the productOptions to check for (and reduce) the number of stock for "Black" shoes, etc
        // Use the variables black_stock and brown_stock at the top.

        // Split product options
        String[] options = productOptions.split(" ");

        int size = Integer.parseInt(options[0]);
        String colour = options[1];

        // if the colour is black
        if (colour.equalsIgnoreCase("Black")){

            // Decrease stock count for respective size
            if (size == 6){
                this.black_stock[0] --;
            } else if (size == 7){
                this.black_stock[1] --;
            } else if (size == 8){
                this.black_stock[2] --;
            } else if (size == 8){
                this.black_stock[3] --;
            } else {
                this.black_stock[4] --;
            }

            // if the colour is brown
        } else {

            // Decrease stock count for respective size
            if (size == 6){
                this.brown_stock[0] --;
            } else if (size == 7){
                this.brown_stock[1] --;
            } else if (size == 8){
                this.brown_stock[2] --;
            } else if (size == 8){
                this.brown_stock[3] --;
            } else {
                this.brown_stock[4] --;
            }
        }
    }
    /*
     * Print product information in super class and append Shoe specific information size and colour
     */
    public void print(){

        super.print();
        System.out.print("     Colours: Black and Brown       Sizes: (6-10)");
    }
}
