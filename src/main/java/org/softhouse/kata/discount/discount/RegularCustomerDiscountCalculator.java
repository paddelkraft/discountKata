package org.softhouse.kata.discount.discount;

public class RegularCustomerDiscountCalculator implements DiscountCalculator {
	DiscountCalculator calculator;
	
	
	public RegularCustomerDiscountCalculator(DiscountCalculator calculator) {
		this.calculator = calculator;
	}


	public double calculatePrice(ShoppingCartItem item) {
		
		return calculator.calculatePrice(item)*0.9;
	}

}
