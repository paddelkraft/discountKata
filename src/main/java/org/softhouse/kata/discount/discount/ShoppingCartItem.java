package org.softhouse.kata.discount.discount;

/**
 * Created with IntelliJ IDEA.
 * User: cno
 * Date: 2014-05-06
 * Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
public class ShoppingCartItem {
    private String name;
    private String category;
    private double price;
	private int count;
	

    public ShoppingCartItem(String name, String category,int count, double price)
    {
        this.name = name;
        this.category = category;
        this.count = count;
        this.price = price;
    }

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	public double getPrice() {
		
		return price;
	}
	
	public String getName(){
		return name;
	}

	public Object getCategory() {
		
		return category;
	}

	
}