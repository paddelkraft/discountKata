package org.softhouse.kata.discount.discount;

public class ClearanceCalculator implements DiscountCalculator {

	public double calculatePrice(ShoppingCartItem item) {
		return item.getCount()*item.getPrice()*0.6;
	}

}
