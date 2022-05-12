import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;
import java.util.StringTokenizer;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    private Map<String, Product> products = new TreeMap<String, Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();

    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;

    private String orderType = "";

    // Random number generator
    Random random = new Random();

    public ECommerceSystem()
    {
        // NOTE: do not modify or add to these objects!! - the TAs will use for testing
        // If you do the class Shoes bonus, you may add shoe products

        // Create some products. Notice how generateProductId() method is used
        readFile();

        // Create some customers. Notice how generateCustomerId() method is used
        customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
        customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
        customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
        customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }

    /*
     * This method will increment order number
     */
    private String generateOrderNumber()
    {
        return "" + orderNumber++;
    }

    /*
     * This method will increment customer id
     */
    private String generateCustomerId()
    {
        return "" + customerId++;
    }

    /*
     * This method will increment product id
     */
    private String generateProductId() {
        return "" + productId++;
    }

    /*
     * Reads file, creates product classes, puts it in product map
     */
    private void readFile (){
        try {
            Scanner scanner = new Scanner(new File("products.txt")).useDelimiter("\n");

            // Check to see if there is a next line avaliable in the file
            while (scanner.hasNext()) {

                String line = (scanner.nextLine());
                String productId = generateProductId();

                // Check to see if line is equal to books
                if (line.equals("BOOKS")){

                    try {

                        // 2nd line is name
                        String name = scanner.nextLine();

                        // 3rd line is price
                        double price = Double.parseDouble(scanner.nextLine());

                        // 4th line takes line and splits it into stock
                        String[] stock = scanner.nextLine().split(" ");

                        // takes the last line, splits it into product options, etc
                        String[] last_options = scanner.nextLine().split(":");
                        
                        products.put(productId, new Book (name, productId, price, Integer.parseInt(stock[0]), Integer.parseInt(stock[1]), last_options[0], last_options[1], Integer.parseInt(last_options[2])));

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                        System.out.println(exception.toString());
                        System.exit(1);
                    }

                } else {

                    // Category is not BOOK
                    try {

                        // 2nd line is name
                        String name = scanner.nextLine();

                        // 3rd line is price
                        double price = Double.parseDouble(scanner.nextLine());

                        // 4th line is stock
                        int stock = Integer.parseInt(scanner.nextLine());

                        scanner.nextLine();

                        if (line.equals("COMPUTERS")) {

                            products.put(productId, new Product(name, productId, price, stock, Product.Category.COMPUTERS));

                        } else if (line.equals("FURNITURE")) {

                            products.put(productId, new Product(name, productId, price, stock, Product.Category.FURNITURE));

                        } else if (line.equals("CLOTHING")) {

                            products.put(productId, new Product(name, productId, price, stock, Product.Category.CLOTHING));

                        } else if (line.equals("GENERAL")) {

                            products.put(productId, new Product(name, productId, price, stock, Product.Category.GENERAL));

                        } else {

                            throw new CategoryNameInvalid(line);

                        }

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {

                        System.out.println(exception.toString());
                        System.exit(1);

                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    /*
     * This method will set order type
     * @param String type;
     */
    public void setType(String Type) { orderType = Type;}

    /*
     * This method will generate shoe stock
     */
    private int[][] generateShoeStock()
    {
        Random random = new Random();

        int[][] shoe_stock = new int[5][5];

        // Loop through the possible number of colours
        for (int x = 0; x < shoe_stock.length; x++){

            // loop through the possible sizes
            for (int y = x; y < shoe_stock[x].length; y++){

                // Generate random shoe stock
                shoe_stock[x][y] = random.nextInt((99 - 1) + 1) + 1;
            }
        }

        return shoe_stock;
    }

    /*
     * This method will print all products
     */
    public void printAllProducts()
    {
        for (int x = 700; x < productId; x++){
            products.get(""+x).print();
        }
    }

    /*
     * This method will print all products that are books
     */
    public void printAllBooks()
    {
        for (int x = 700; x < productId; x++){
            if (products.get(""+x).getClass() == Book.class){
                products.get(""+x).print();
            }

        }
    }

    /*
     * This method will print all current orders
     */
    public void printAllOrders()
    {
        for (ProductOrder order :orders){
            order.print();
        }
    }

    /*
     * This method will print all shipped orders
     */
    public void printAllShippedOrders()
    {
        for (ProductOrder order :shippedOrders){
            order.print();
        }
    }

    /*
     * This method will print all customers
     */
    public void printCustomers()
    {
        for (Customer customer : customers){
            customer.print();
        }
    }

    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     * @param String customerId
     */
    public void printOrderHistory(String customerId)
    {
        boolean found = false;

        // Check to see if customer is in customer arraylist
        for (Customer customer : customers){

            // if customer id equals customer id entered, return true
            if (customer.getId().equals(customerId)){

                found = true;
                break;

            }
        }

        // if Customer not found, return false
        if (!found){

            throw new UnknownCustomerException(customerId);

        } else {
            // Print current orders of this customer
            System.out.println("Current Orders of Customer " + customerId);

            for (ProductOrder product: orders){

                if (product.getCustomer().getId().equals(customerId)){

                    product.print();

                }

            }
            // Print shipped orders of this customer
            System.out.println("\nShipped Orders of Customer " + customerId);

            for (ProductOrder product: shippedOrders){

                if (product.getCustomer().getId().equals(customerId)){

                    product.print();

                }

            }

        }

    }

    /*
     * Order product method
     * @param String productId
     * @param String customerId
     * @param String productOptions
     */
    public String orderProduct(String productId, String customerId, String productOptions)
    {

        int index = customers.indexOf(new Customer(customerId));
        if (index == -1)
        {
            throw new UnknownCustomerException(Integer.parseInt(customerId));
        }
        Customer customer = customers.get(index);

        // Get product
        if (Integer.parseInt(productId) >= this.productId|| Integer.parseInt(productId) <= 699){
            throw new UnknownProductException(Integer.parseInt(productId));
        }

        Product product = products.get(productId);

        // Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
        try {
            if (!productOptions.equals("")) {
                if (product.getClass() == Book.class) {
                    if (!product.validOptions(productOptions)) {
                        throw new InvalidOptionException("Product Book ProductID " + product.getId() + " Invalid Options: " + productOptions);
                    }
                } else if (product.getClass() == Shoes.class) {
                    if (!product.validOptions(productOptions)) {
                        throw new InvalidOptionException("Product Shoe ProductID " + product.getId() + " Invalid Options: " + productOptions);
                    }
                } else {
                    throw new InvalidOrderTypeException("Order is not a book/shoe and has invalid product option " + productOptions);
                }
            } else {
                if (product.getClass() == Book.class || product.getClass() == Shoes.class) {
                    throw new InvalidOrderTypeException("Use book/shoe command to order book/shoes");
                }
            }
            // Is it in stock?

            if ((product.getClass() == Book.class) || (product.getClass() == Shoes.class) || productOptions.equals("")) {
                if (product.getStockCount(productOptions) == 0) {
                    throw new OutOfStockException("Product " + product.getName() + " ProductId " + productId + " Out of Stock");
                }
                // Create a ProductOrder
                ProductOrder order = new ProductOrder(generateOrderNumber(), product, customer, productOptions);
                product.reduceStockCount(productOptions);

                // Add to orders and return
                product.purchased();
                orders.add(order);

                return "Order #" + order.getOrderNumber();
            } else {
                throw new InvalidOptionException("Product " + productId + " is a " + "book/shoe.");
            }
        } catch (InvalidOptionException | OutOfStockException | InvalidOrderTypeException exception) {
            System.out.println(exception.toString());
            return null;
        }
    }

    /*
     * Create a new Customer object and add it to the list of customers
     * @param String name
     * @param String address
     */
    public void createCustomer(String name, String address)
    {

        // check to see if customer name is valid
        if (name.equals("") || name.equals(null)){

            throw new InvalidCustomerName(name);

        // Check to see if address is valid
        } else if (address.equals("") || address.equals(null)) {

            throw new InvalidCustomerAddress(address);

        } else {

            // Create a Customer object and add to array list
            customers.add(new Customer(generateCustomerId(),name, address));

        }
    }

    /*
     * Ship product with given order nu,ber
     * @param String orderNumber
     */
    public ProductOrder shipOrder(String orderNumber)
    {
        int index = orders.indexOf(new ProductOrder(orderNumber,null,null,""));
        if (index == -1)
        {
            throw new InvalidOrderNumber(orderNumber);
        }
        ProductOrder order = orders.get(index);
        
        orders.remove(index);
        shippedOrders.add(order);
        return order;
    }

    /*
     * Cancel a specific order based on order number
     * @param String orderNumber
     */
    public void cancelOrder(String orderNumber)
    {
        int location = 0;

        boolean found = false;

        // Loop through orders list
        for (int x =0; x < orders.size(); x++){

            // If order found, found = true
            if (orders.get(x).getOrderNumber().equals(orderNumber)){

                location = x;

                found = true;

                break;

            }

        }

        // If found, remove order from orderlist
        if (found){

            ProductOrder removed = orders.get(location);

            orders.remove(location);

        // Error Message (Order number not found)
        } else {

            throw new InvalidOrderNumber(orderNumber);

        }

    }

    /*
     * Print stock of product
     * @param String productId
     */
    public void getStock(String t_productId)
    {
        boolean p_in_system = false;

        Product product_object = null;

        // Check to see if product is in products list
        for (int x = 700; x < Integer.parseInt(t_productId); x++){

            if (products.get(""+x).getId().equals(t_productId)){

                p_in_system = true;

                product_object = products.get(""+x);

            }

        }

        // Error Message (Product not found)
        if (p_in_system == false){

            throw new UnknownProductException(t_productId);

        } else {

            // Check class of product, then print
            if (product_object.getClass() == Book.class) {

                System.out.println("Paperback: " + product_object.getStockCount("Paperback") + "Hardcover: " + product_object.getStockCount("Hardcover"));

            } else if (product_object.getClass() == Shoes.class) {

                System.out.println("Black: " + product_object.getArray("Black") + "Brown: " + product_object.getArray("Brown"));

            } else {

                System.out.println(product_object.getStockCount(""));

            }
        }
        
    }

    /*
     * Print authors who have books
     */
    public void printAuthors(){

        ArrayList<String> Authors = new ArrayList<String>();

        // Loop through products, find book class objects
        for (int x = 700; x < this.productId; x++){

            if (products.get(""+x).getClass() == Book.class){

                // check if author has already been added
                for (String y : Authors){

                    if (products.get(""+x).getAuthor().equals(y)){

                        break;

                    }

                }

                // Add to authors list
                Authors.add(products.get(""+x).getAuthor());
            }

        }
        
        for (String x : Authors){System.out.println(x);}
    }

    /*
     * Print books by author in increasing order
     * @param String Name
     */
    public void sortByAuthor(String Name) {

        ArrayList<Product> booksByAuthor = new ArrayList<Product>();

        // Check to see if book in products list was made by author given
        for (int x = 700; x < this.productId; x++){

            if (products.get(""+x).getClass() == Book.class) {

                if (products.get(""+x).getAuthor().equals(Name)){

                    booksByAuthor.add(products.get(""+x));

                }

            }

        }

        // if total books made is greater than 1
        if (booksByAuthor.size() >= 1){

            // Sort books by author by published year
            Collections.sort(booksByAuthor, new Comparator<Product>(){

                @Override
                public int compare(Product A, Product B){

                    if (A.getYear() > B.getYear()){

                        return -1;

                    } else if (A.getYear() < B.getYear()){

                        return 1;

                    } else {

                        return 0;

                    }
                    
                }

            });

            // print
            for (Product x : booksByAuthor){x.print();}

        // Error (Author is not found)
        } else {

            throw new InvalidAuthorName(Name);

        }

    }

    /*
     * Adds item to customer cart
     * @param String productId
     * @param String customerId
     * @param String productOptions
     */
    public void addToCart(String productId, String customerId, String productOptions){

        // Check if customer exists
        int index = customers.indexOf(new Customer(customerId));
        if (index == -1){
            throw new UnknownProductException(Integer.parseInt(productId));
        }
        Customer customer = customers.get(index);

        // Checks to see if product exists
        if (Integer.parseInt(productId) > this.productId || Integer.parseInt(productId) < 700){
            throw new UnknownProductException(Integer.parseInt(productId));
        }
        Product product = products.get(productId);

        customer.addItem(product, productOptions);
    }

    /*
     * Prints customer cart
     * @param String customerId
     */
    public void printCart(String customerId){

        // Check to see if customer exists
        int index = customers.indexOf(new Customer(customerId));
        if (index == -1){
            throw new UnknownCustomerException(Integer.parseInt(customerId));
        }
        Customer customer = customers.get(index);

        customer.printCart();
    }

    /*
     * Order items from customer cart
     * @param String customerId
     */
    public void orderItems(String customerId){

        // Checks to see if customer exists
        int index = customers.indexOf(new Customer(customerId));
        if (index == -1){
            throw new UnknownCustomerException(Integer.parseInt(customerId));
        }
        Customer customer = customers.get(index);
        
        ArrayList<CartItem> shoppingCart = customer.getCart();
        ArrayList<CartItem> orderedCart = new ArrayList<CartItem>();

        // orders the product, adds ordered items into ordered cart
        for (CartItem items: shoppingCart){
            orderProduct(items.getItem().getId(),customerId,items.getProductOptions());
            orderedCart.add(items);
        }

        // Removes items from customer cart
        for (CartItem ordered: orderedCart){
            customer.removeItem(ordered);
        }
    }

    /*
     * Removes item from customer cart
     * @param String productId
     * @param String customerId
     */
    public void removeItemCommand(String customerId, String productId){

        // Checks to see if customer exists
        int index = customers.indexOf(new Customer(customerId));
        if (index == -1){
            throw new UnknownCustomerException(Integer.parseInt(customerId));
        }
        Customer customer = customers.get(index);

        ArrayList<CartItem> shoppingCart = customer.getCart();

        int length = shoppingCart.size();

        // Removes product if equals product id
        for (CartItem items : shoppingCart){
            if (items.getItem().getId().equals(productId)){
                customer.removeItem(items);
                break;
            }
        }

        // If length stays the same, product doesn't exist in cart
        if (length == customer.getCart().size()) {
            throw new UnknownProductException("Product id " + productId + " not in shopping cart.");
        }
    }

    /*
     * Checks order statistics of products
     */
    public void statistics(){

        ArrayList<Product> tempProducts = new ArrayList<Product>();

        // Adds products to array list tempProducts
        for (int x = 700; x < this.productId; x++){
            tempProducts.add(products.get(""+x));
        }

        // Sort arraylist
        Collections.sort(tempProducts, new Comparator<Product>(){

            @Override
            public int compare(Product A, Product B){
                if (A.getPurchased() > B.getPurchased())
                    return -1;
                if (A.getPurchased() < B.getPurchased())
                    return 1;
                return 0;
            }

        });

        for (Product x : tempProducts) {
            System.out.printf("\nId: %-5s Name: %-20s Purchased: %7.1s", x.getId(), x.getName(), x.getPurchased());
        }
    }

    /*
     * Print list of products of category with higher than given rating
     * @param String Category
     * @param int Rating
     */
    public void sortByRating(String Category, int rating){
        ArrayList<Product> tempProducts = new ArrayList<Product>();
        ArrayList<Product> categoryProds = new ArrayList<Product>();

        // Move products from map to array list tempProducts
        for (int x = 700; x < this.productId; x++){
            tempProducts.add(products.get(""+x));
        }

        if (rating < 1 || rating > 5){
            throw new InvalidRatingException(rating);
        }

        // Create a new arraylist for products with the same category
        for (Product prods : tempProducts){
            if (prods.getCategory().equalsIgnoreCase(Category)){
                if (prods.returnRating() > rating) {
                    categoryProds.add(prods);
                }
            }
        }
        
        if (categoryProds.size() == 0){
            throw new NoProductWithRatingException(Category, rating);
        }

        // Sort arraylist by ratings
        Collections.sort(categoryProds, new Comparator<Product>(){

            @Override
            public int compare(Product A, Product B){
                if (A.returnRating() > B.returnRating())
                    return -1;
                if (A.returnRating() < B.returnRating())
                    return 1;
                return 0;
            }

        });

        for (Product prods : categoryProds){prods.print();}
    }

    public void rate(String customerId, String productId, int rating){

        // Check to see if customer exists
        int index = customers.indexOf(new Customer(customerId));
        if (index == -1)
        {
            throw new UnknownCustomerException(Integer.parseInt(customerId));
        }
        Customer customer = customers.get(index);

        // Get product
        if (Integer.parseInt(productId) >= this.productId || Integer.parseInt(productId) <= 699){
            throw new UnknownProductException(Integer.parseInt(productId));
        }

        Product product = products.get(productId);

        // Initialize product ratings arraylist
        product.initialize();

        if (rating < 1 || rating > 5){
            throw new InvalidRatingException(rating);
        }

        // Check if customer has already put a rating on a product
        if (product.checkRating(customer)){
            System.out.println ("Customer " + customerId + " has already voted, current rating of: " + rating + " has replaced the last rating of " + product.checkPastRating(customer));
            product.decreaseRating(product.checkPastRating(customer));
            product.removeRating(product.checkPastRating(customer), customer);
            product.rate(rating, customer);
            product.addRating(rating);

        } else {
            product.addRating(rating);
            product.rate(rating, customer);

            System.out.println("Rating added.");
        }
    }

    public void removeRating (String customerId, String productId){

        // Check to see if customer exists
        int index = customers.indexOf(new Customer(customerId));
        if (index == -1)
        {
            throw new UnknownCustomerException(Integer.parseInt(customerId));
        }
        Customer customer = customers.get(index);

        // Get product
        if (Integer.parseInt(productId) >= this.productId || Integer.parseInt(productId) <= 699){
            throw new UnknownProductException(Integer.parseInt(productId));
        }

        Product product = products.get(productId);
        
        product.decreaseRating(product.checkPastRating(customer));
        product.removeRating(product.checkPastRating(customer), customer);

        System.out.println("Rating removed.");
    }

    public void printRatings(String productId){

        // Get product
        if (Integer.parseInt(productId) >= this.productId || Integer.parseInt(productId) <= 699){
            throw new UnknownProductException(Integer.parseInt(productId));
        }

        Product product = products.get(productId);

        product.printRatings();
    }
    
    /*
     * Sort products by price
     */
    public void printByPrice()
    {
        ArrayList<Product> tempProducts = new ArrayList<Product>();

        for (int x = 700; x < this.productId; x++){
            tempProducts.add(products.get(""+x));
        }

        Collections.sort(tempProducts, new Comparator<Product>(){

            @Override
            public int compare(Product A, Product B){
                if (A.getPrice() > B.getPrice())
                    return 1;
                if (A.getPrice() < B.getPrice())
                    return -1;
                return 0;
            }

        });

        for (Product x: tempProducts){
            x.print();
        }
    }


    /*
     * Sort products by name
     */
    public void printByName()
    {
        ArrayList<Product> tempProducts = new ArrayList<Product>();

        for (int x = 700; x < this.productId; x++){
            tempProducts.add(products.get(""+x));
        }

        // Sort products
        Collections.sort(tempProducts, new Comparator<Product>(){

            @Override
            public int compare(Product A, Product B){
                return A.getName().compareTo(B.getName());
            }

        });

        for (Product x: tempProducts){
            x.print();
        }
    }


    /*
     * Sort customers by name
     */
    public void sortCustomersByName()
    {
        // Sort customers
        Collections.sort(customers, new Comparator<Customer>(){

            @Override
            public int compare(Customer A, Customer B){
                return A.getName().compareTo(B.getName());
            }

        });
    }
}

// Customer doesn't exist exception
class UnknownCustomerException extends  RuntimeException {
    public UnknownCustomerException(int id){
        super ("Customer " + id + " not found.");
    }

    public UnknownCustomerException (String message){
        super(message);
    }
}

// Product doesn't exist exception
class UnknownProductException extends  RuntimeException {
    public UnknownProductException(int id){
        super ("Product " + id + " not found.");
    }

    public UnknownProductException (String message){
        super(message);
    }
}

// Invalid product option exception
class InvalidOptionException extends  RuntimeException {
    public InvalidOptionException(int id){
        super ("Product " + id + " not found.");
    }

    public InvalidOptionException (String message){
        super(message);
    }
}

// Invalid order type exception
class InvalidOrderTypeException extends RuntimeException {
    public InvalidOrderTypeException(int id, String Type){
        super ("Product " + id + " is not a " + Type);
    }

    public InvalidOrderTypeException (String message){
        super(message);
    }
}

// Out of stock exception
class OutOfStockException extends RuntimeException {
    public OutOfStockException (String message){
        super (message);
    }
}

// Invalid customer address exception
class InvalidCustomerAddress extends RuntimeException {
    public InvalidCustomerAddress (String address){
        super ("Invalid customer address: " + address + " .");
    }
}

// Invalid customer name exception
class InvalidCustomerName extends RuntimeException {
    public InvalidCustomerName (String name){
        super ("Invalid customer name: " + name + " .");
    }
}

// Invalid order number exception
class InvalidOrderNumber extends RuntimeException {
    public InvalidOrderNumber (String number){
        super ("Order number: " + number + " Not Found.");
    }
}

// Invalid author name exception
class InvalidAuthorName extends RuntimeException {
    public InvalidAuthorName (String name){
        super ("Author " + name + " not found.");
    }
}

// Category name invalid exception
class CategoryNameInvalid extends RuntimeException {
    public CategoryNameInvalid (String category){
        super("Invalid Category Name: " + category);
    }
}

// Invalid rating exception
class InvalidRatingException extends RuntimeException {
    public InvalidRatingException (int rating) { super("Invalid product rating: " + rating);}
}

// No product with over rating exception
class NoProductWithRatingException extends RuntimeException {
    public NoProductWithRatingException (String product, int rating) { super ("There are no products of category " + product + " with rating over " + rating + " stars.");}
}