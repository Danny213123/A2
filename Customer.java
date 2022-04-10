import java.util.ArrayList;
/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address.
 *  A unique id is generated when when a new customer is created.
 *
 *  Implement the Comparable interface and compare two customers based on name
 */
public class Customer
{
	private String id;
	private String name;
	private String shippingAddress;
	private Cart shoppingCart;

	/** Customer Constructor
	 * 
	 * @param String id
	 */
	public Customer(String id)
	{
		this.id = id;
		this.name = "";
		this.shippingAddress = "";
		this.shoppingCart = new Cart();
	}

	/** Customer Constructor
	 *
	 * @param String id
	 * @param String name
	 * @param String address
	 */
	public Customer(String id, String name, String address)
	{
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		this.shoppingCart = new Cart();
	}

	/*
	 * This method will return customer id
	 */
	public String getId()
	{
		return id;
	}

	/*
	 * This method will set id
	 * @param String id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/*
	 * This method will return customer name
	 */
	public String getName()
	{
		return name;
	}

	/*
	 * This method will set customer name
	 * @param String name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/*
	 * This method will return shipped address
	 */
	public String getShippingAddress()
	{
		return shippingAddress;
	}

	/*
	 * This method will set shipping address
	 * @param String shippingAddress
	 */
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}

	/*
	 * print method
	 */
	public void print()
	{
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}

	public void addItem(Product product, String productOptions){
		shoppingCart.addItem(product, productOptions);
	}

	public ArrayList<CartItem> getCart(){
		return shoppingCart.getItems();
	}

	public void printCart(){
		shoppingCart.printCart();
	}

	public void removeItem(CartItem item){
		shoppingCart.removeItem(item);
	}

	/*
	 * Method to check if 2 customers are equal (check id)
	 */
	public boolean equals(Object other)
	{
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}

}
