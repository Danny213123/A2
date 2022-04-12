import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try {
				String action = scanner.nextLine();

				if (action == null || action.equals("")) {
					System.out.print("\n>");
					continue;
				} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;

				else if (action.equalsIgnoreCase("PRODS"))    // List all products for sale
				{
					amazon.printAllProducts();
				} else if (action.equalsIgnoreCase("BOOKS"))    // List all books for sale
				{
					amazon.printAllBooks();
				} else if (action.equalsIgnoreCase("CUSTS"))    // List all registered customers
				{
					amazon.printCustomers();
				} else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
				{
					amazon.printAllOrders();
				} else if (action.equalsIgnoreCase("SHIPPED"))    // List all orders that have been shipped
				{
					amazon.printAllShippedOrders();
				} else if (action.equalsIgnoreCase("NEWCUST"))    // Create a new registered customer
				{
					String name = "";
					String address = "";

					System.out.print("Name: ");
					if (scanner.hasNextLine())
						name = scanner.nextLine();

					System.out.print("\nAddress: ");
					if (scanner.hasNextLine())
						address = scanner.nextLine();

					amazon.createCustomer(name, address);
				} else if (action.equalsIgnoreCase("SHIP"))    // ship an order to a customer
				{
					String orderNumber = "";

					System.out.print("Order Number: ");
					orderNumber = scanner.nextLine();
					// Get order number from scanner

					amazon.shipOrder(orderNumber);
					// Ship order to customer (see ECommerceSystem for the correct method to use
				} else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
				{
					String customerId = "";

					System.out.print("Customer Id: ");
					// Get customer Id from scanner
					customerId = scanner.nextLine();
					// Print all current orders and all shipped orders for this customer
					amazon.printOrderHistory(customerId);

				} else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
				{
					String productId = "";
					String customerId = "";

					System.out.print("Product Id: ");
					// Get product Id from scanner
					productId = scanner.nextLine();

					System.out.print("\nCustomer Id: ");
					// Get customer Id from scanner
					customerId = scanner.nextLine();

					// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
					// Print Order Number string returned from method in ECommerceSystem
					String order = amazon.orderProduct(productId, customerId, "");
					if (!order.equals(null)){System.out.println(order);}

				} else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
				{
					String productId = "";
					String customerId = "";
					String options = "";

					System.out.print("Product Id: ");
					// get product id
					productId = scanner.nextLine();

					System.out.print("\nCustomer Id: ");
					// get customer id
					customerId = scanner.nextLine();

					System.out.print("\nFormat [Paperback Hardcover EBook]: ");
					// get book forma and store in options string
					options = scanner.nextLine();

					amazon.setType("Book");
					// Order product. Check for error mesage set in ECommerceSystem
					String order = amazon.orderProduct(productId, customerId, options);
					System.out.println(order);
				} else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color
				{
					String productId = "";
					String customerId = "";
					String options = "";

					System.out.print("Product Id: ");
					// get product id
					productId = scanner.nextLine();

					System.out.print("\nCustomer Id: ");
					// get customer id
					customerId = scanner.nextLine();

					System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
					// get shoe size and store in options
					options += scanner.nextLine();

					System.out.print("\nColor: \"Black\" \"Brown\": ");
					// get shoe color and append to options
					options += " " + scanner.nextLine();

					amazon.setType("Shoe");
					//order shoes
					String order = amazon.orderProduct(productId, customerId, options);
					System.out.println(order);
				} else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
				{
					String orderNumber = "";

					System.out.print("Order Number: ");
					orderNumber = scanner.nextLine();
					// get order number from scanner
					amazon.cancelOrder(orderNumber);
				} else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
				{
					amazon.printByPrice();
				} else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
				{
					amazon.printByName();
				} else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
				{
					amazon.sortCustomersByName();
				} else if (action.equalsIgnoreCase("GETSTOCK")) {
					String productId = "";

					System.out.print("Product Number: ");
					productId = scanner.nextLine();

					amazon.getStock(productId);
				} else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) {
					String Author = "";

					System.out.println("Books: " + "\n");
					amazon.printAuthors();

					System.out.print("\nAuthor name: ");
					Author = scanner.nextLine();

					amazon.sortByAuthor(Author);
				} else if (action.equalsIgnoreCase("COMMANDS")) {
					System.out.println("BOOKSBYAUTHOR, GETSTOCK, SORTCUSTS, PRINTBYNAME, PRINTBYPRICE, CANCEL, ORDERSHOES, ORDERBOOK, ORDER, SHIP, NEWCUST, SHIPPED, ORDERS, CUSTS, BOOKS, PRODS, QUIT, ADDTOCART, REMCARTITEM, PRINTCART, ORDERITEMS, STATS, RATE");
				} else if (action.equalsIgnoreCase("ADDTOCART")) {
					String productId = "";
					String customerId = "";
					String productOptions = "";

					System.out.print("Product ID: ");
					productId = scanner.nextLine();

					System.out.print("Customer ID: ");
					customerId = scanner.nextLine();

					System.out.print("Product Option: ");
					productOptions = scanner.nextLine();

					amazon.addToCart(productId, customerId, productOptions);
				} else if (action.equalsIgnoreCase("REMCARTITEM")) {
					String productId = "";
					String customerId = "";

					System.out.print("Customer ID: ");
					customerId = scanner.nextLine();

					System.out.print("Product ID: ");
					productId = scanner.nextLine();

					amazon.removeItemCommand(customerId, productId);

				} else if (action.equalsIgnoreCase("PRINTCART")) {
					String customerId = "";

					System.out.print("Customer ID: ");
					customerId = scanner.nextLine();

					amazon.printCart(customerId);
				} else if (action.equalsIgnoreCase("ORDERITEMS")) {
					String customerId = "";

					System.out.print("Customer ID: ");
					customerId = scanner.nextLine();

					amazon.orderItems(customerId);
				} else if (action.equalsIgnoreCase("RATE")) {
					String customerId = "";
					String productId = "";
					int rating = 0;

					System.out.print("Customer ID: ");
					customerId = scanner.nextLine();

					System.out.print("Product ID: ");
					productId = scanner.nextLine();

					System.out.print("Rating: ");
					rating = scanner.nextInt();

					scanner.nextLine();

					amazon.rate(customerId, productId, rating);
				} else if (action.equalsIgnoreCase("PRINTRATINGS")) {
					String productId = "";

					System.out.print("Product ID: ");
					productId = scanner.nextLine();

					amazon.printRatings(productId);
				} else if (action.equalsIgnoreCase("REMOVERATING")) {
					String customerId = "";
					String productId = "";

					System.out.print("Customer ID: ");
					customerId = scanner.nextLine();

					System.out.print("Product ID: ");
					productId = scanner.nextLine();

					amazon.removeRating(customerId, productId);
				} else if (action.equalsIgnoreCase("SORTBYRATING")){
					String Category = "";
					int rating = 0;

					System.out.print("Category: ");
					Category = scanner.nextLine();

					System.out.print("Only show ratings above: ");
					rating = scanner.nextInt();

					scanner.nextLine();

					amazon.sortByRating(Category, rating);
				} else if (action.equalsIgnoreCase("STATS")){
					amazon.statistics();
				}

				System.out.print("\n>");
			} catch (UnknownCustomerException | UnknownProductException | InvalidOptionException | CategoryNameInvalid | InvalidOrderTypeException | NumberFormatException | InvalidRatingException | OutOfStockException | InvalidCustomerAddress | InvalidCustomerName | InvalidOrderNumber | InvalidAuthorName | InputMismatchException exception){
				System.out.println(exception.toString());

				System.out.print("\n>");
			}
		}
	}
}
