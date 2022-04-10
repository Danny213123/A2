
/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product
{
    private String author;
    private String title;
    private int yearPublished;

    // Stock related information NOTE: inherited stockCount variable is used for EBooks
    int paperbackStock;
    int hardcoverStock;

    /** Book Constructor
     * @param String name			- Name of item
     * @param String Id				- Id of item
     * @param double price			- Price of item
     * @param int paperbackStock 	- Stock of paperback
     * @param int hardcoverStock    - Stock of hardcover
     * @param String title          - Title of book
     * @param int year              - Year published
     */
    public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author, int year)
    {
        // Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
        // Set category to BOOKS
        super.setId(id);
        super.setPrice(price);
        super.setName("Book");
        super.setCategory(Product.Category.BOOKS);
        this.paperbackStock = paperbackStock;
        this.hardcoverStock = hardcoverStock;
        this.title = title;
        this.author = author;
        this.yearPublished = year;
    }

    /* Inherits super class method
     * This method will return book author
     */
    public String getAuthor(){
        return this.author;
    }
    
    /*
     * Return book title
     */
    public String getTitle(){
        return this.title;
    }

    /* i=Inherits super class method
     * This method will return year published
     */
    public int getYear(){
        return this.yearPublished;
    }

    /*
     * This method will check to see if product option is valid
     * @param String productOptions
     */
    public boolean validOptions(String productOptions)
    {
        // check productOptions for "Paperback" or "Hardcover" or "EBook"
        // if it is one of these, return true, else return false
        if (productOptions.equals("Paperback") || productOptions.equals("Hardcover") || productOptions.equals("EBook")){
            return true;
        } else{
            return false;
        }
    }

    /* Inherits super class method
     * This method will return stock count of given product option
     * @param String productOptions
     */
    public int getStockCount(String productOptions)
    {
        // Use the productOptions to check for (and return) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top. 
        // For "EBook", use the inherited stockCount variable.
        if (productOptions.equals("Paperback")){
            return paperbackStock;
        } else if (productOptions.equals("Hardcover")){
            return hardcoverStock;
        } else {
            return super.getStockCount(productOptions);
        }
    }

    /* Inherits super class method
     * This method will set stock count
     * @param int stockCount
     * @param String productOptions
     */
    public void setStockCount(int stockCount, String productOptions)
    {
        // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top. 
        // For "EBook", set the inherited stockCount variable.
        if (productOptions.equals("Paperback")){
            this.paperbackStock = stockCount;
        } else if (productOptions.equals("Paperback")) {
            this.hardcoverStock = stockCount;
        } else {
            super.setStockCount(stockCount, productOptions);
        }
    }

    /*
     * When a book is ordered, reduce the stock count for the specific stock type
     */
    public void reduceStockCount(String productOptions)
    {
        // Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top. 
        // For "EBook", set the inherited stockCount variable.
        if (productOptions.equals("Paperback")){
            this.paperbackStock--;
        } else if (productOptions.equals("Paperback")) {
            this.hardcoverStock--;
        } else {
            super.setStockCount(1, productOptions);
        }
    }
    
    /*
     * Print product information in super class and append Book specific information title and author
     */
    public void print()
    {
        // Replace the line below.
        // Make use of the super class print() method and append the title and author info. See the video
        super.print();
        System.out.printf("     Book title: %-35s Author: %-20s Year Published: %-20d", title, author, yearPublished);
    }
}
