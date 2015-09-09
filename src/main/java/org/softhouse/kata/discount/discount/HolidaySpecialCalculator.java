package org.softhouse.kata.discount.discount;

public class HolidaySpecialCalculator implements DiscountCalculator {

	public double calculatePrice(ShoppingCartItem item) {
		
		return item.getCount()*item.getPrice()*0.5;
	}

}
