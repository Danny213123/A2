import java.util.ArrayList;
import java.util.Collections;
/*
 * class Product defines a product for sale by the system.
 *
 * A product belongs to one of the 5 categories below.
 *
 * Some products also have various options (e.g. size, color, format, style, ...). The options can affect
 * the stock count(s). In this generic class Product, product options are not used in get/set/reduce stockCount() methods
 *
 * Some products
 */
public class Product implements Comparable<Product>
{
	public static enum Category {GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES};

	private String name;
	private String id;
	private Category category;
	private double price;
	private int stockCount;
	private int timesPurchased = 0;

	/** Product Construction
	 *
	 * Set id, name, category and stock count.
	 */
	public Product()
	{
		this.name = "Product";
		this.id = "001";
		this.category = Category.GENERAL;
		this.stockCount = 0;
	}

	/** Product Construction
	 *
	 * @param String id
	 */
	public Product(String id)
	{
		this("Product", id, 0, 0, Category.GENERAL);
	}


	/** Product Constructor
	 * @param String name			- Name of item
	 * @param String id				- Id of item
	 * @param double price			- Price of item
	 * @param int stock 			- Stock of item
	 * @param Category category   	- Category of item
	 */
	public Product(String name, String id, double price, int stock, Category category)
	{
		this.name = name;
		this.id = id;
		this.price = price;
		this.stockCount = stock;
		this.category = category;
	}

	/*
	 * This method always returns true in class Product. In subclasses, this method will be overridden
	 * and will check to see if the options specified are valid for this product.
	 */
	public boolean validOptions(String productOptions)
	{
		return true;
	}

	/*
	 * This method will return product category
	 */
	public Category getCategory()
	{
		return category;
	}

	/*
	 * This method will return author, inherited by book
	 */
	public String getAuthor() {return name;}

	/*
	 * This method will set category
	 * @param Category category
	 */
	public void setCategory(Category category)
	{
		this.category = category;
	}

	/*
	 * This method will return String name
	 */
	public String getName()
	{
		return name;
	}

	/*
	 * Return title, inherited in book class
	 */
	public String getTitle(){return null;}

	/*
	 * This method will set name
	 * @param String name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/*
	 * This method will return id
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
	 * This method will return price
	 */
	public double getPrice()
	{
		return price;
	}

	/*
	 * This method will return product option
	 */
	public String getArray(String productOption)
	{
		return productOption;
	}

	/*
	 * This method will set price
	 * @param double price
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}

	/*
	 * This method always return 1 in class Product. In subclasses, this method will be overridden
	 * and will return year published in books subclass
	 */
	public int getYear(){return 1;}

	/*
	 * Increase times purchased
	 */
	public void purchased(){timesPurchased ++;}

	public int getPurchased(){return this.timesPurchased;}
	/*
	 * Return the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public int getStockCount(String productOptions)
	{
		return stockCount;
	}
	/*
	 * Set (or replenish) the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void setStockCount(int stockCount, String productOptions)
	{
		this.stockCount = stockCount;
	}
	/*
	 * Reduce the number of items currently in stock for this product by 1 (called when a product has
	 * been ordered by a customer)
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void reduceStockCount(String productOIptions)
	{
		stockCount--;
	}

	public void print()
	{
		System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", id, category, name, price);
	}

	/*
	 * Two products are equal if they have the same product Id.
	 * This method is inherited from superclass Object and overridden here
	 */
	public boolean equals(Object other) {
		Product otherP = (Product) other;
		return this.id.equals(otherP.id);
	}

	/*
	 * This is a comparator method to sort prices
	 * This method is inherited from superclass Object and overridden here
	 */
	@Override
	public int compareTo(Product other){
		if (this.price > other.getPrice()){
			return 1;
		} else if (this.price < other.getPrice()){
			return -1;
		} else {
			return 0;
		}
	}
	
}
