package org.softhouse.kata.discount.discount;

public class StandardDiscountCalculator implements DiscountCalculator {

	public double calculatePrice(ShoppingCartItem item) {
		double factor = 1;
		if(item.getCount() > 5){
			factor = 0.9; 
		}
		return item.getCount()*item.getPrice()*factor;
		
	}

}
