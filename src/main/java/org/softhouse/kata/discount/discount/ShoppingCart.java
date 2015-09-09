package org.softhouse.kata.discount.discount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
	List<ShoppingCartItem> items;
	private Customer customer;
	private TimeService timeService;
	private InventoryStatusService inventoryStatus;

	public ShoppingCart(){
		items = new ArrayList<ShoppingCartItem>();
	}

	public double sum()
	{
		double sum = 0; 
		double freight;
		DiscountCalculator calculator;
		
		if(isHolidaySpecial()){
			calculator = new HolidaySpecialCalculator();
		}else{
			calculator = new StandardDiscountCalculator();
			if(isRegularCustomer()){
				calculator = new RegularCustomerDiscountCalculator(calculator);
			}
		}

		for (ShoppingCartItem item:items){
			sum += calculateDiscountedPriceForItem(
					calculator, item);
			
		}
		freight = calculateFreight(sum,items.size());
		return sum + freight;
	}

	private double calculateDiscountedPriceForItem(DiscountCalculator calculator, ShoppingCartItem item) {
		double  discountedPrice;
		double clearancePrice = -1;
		DiscountCalculator clearanceCalculator = new ClearanceCalculator();
		discountedPrice = calculator.calculatePrice(item);
		if(item.getCategory().equals("Discontinued") && inventoryStatus.getInventoryStatus(item.getName())<10){
			clearancePrice = clearanceCalculator.calculatePrice(item);
		}
		discountedPrice= (clearancePrice>0 && clearancePrice<discountedPrice)?clearancePrice:discountedPrice;
		return discountedPrice;
	}

	private boolean isRegularCustomer() {
		return customer!= null && customer.getType()== "Regular";
	}

	public void addToCart(ShoppingCartItem shoppingCartItem) {
		items.add(shoppingCartItem);

	}


	private boolean isHolidaySpecial(){
		Date date = timeService.getDate();
		if(date.getMonth()==11 && date.getDate()>24 ){
			return true;
		}
		return false;
	}



	private double calculateFreight(double sum,int products){
		if(sum<400&&products<5){
			return 50;
		}
		return 0;

	}

	public void setCustmer(Customer customer) {
		this.customer = customer; 

	}

	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;

	}
	
	public void setInventoryStatusService(InventoryStatusService inventoryStatus){
		this.inventoryStatus = inventoryStatus;
	}
}


