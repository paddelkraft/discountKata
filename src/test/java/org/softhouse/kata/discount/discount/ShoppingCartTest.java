package org.softhouse.kata.discount.discount;



import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class ShoppingCartTest  {

	ShoppingCart cart;
	@Before
	public void setup(){
		cart = new ShoppingCart();
		cart.setTimeService(new OrdinaryDayMock());
	}
	@Test
	public void shouldPayFreight(){
		
		cart.addToCart(new ShoppingCartItem("My Test product","testCategory",1,300));
		assertEquals(350,cart.sum(),0.01);
	}
	
	@Test
	public void shouldGetFreeFreightWhenSumExceedes400(){
		cart.addToCart(new ShoppingCartItem("My Test product","testCategory",2,300));
		assertEquals(600,cart.sum(),0.01);
	}
	
	@Test
	public void shouldGetFreeFreightWhenMoreThan5Products(){
		cart.addToCart(new ShoppingCartItem("My Test product 1","testCategory",1,20));
		cart.addToCart(new ShoppingCartItem("My Test product 2","testCategory",1,20));
		cart.addToCart(new ShoppingCartItem("My Test product 3","testCategory",1,20));
		cart.addToCart(new ShoppingCartItem("My Test product 4","testCategory",1,20));
		cart.addToCart(new ShoppingCartItem("My Test product 5 ","testCategory",1,20));
		cart.addToCart(new ShoppingCartItem("My Test product 6","testCategory",1,20));
		assertEquals(120,cart.sum(),0.01);
	}
	
	@Test
	public void shouldGetVolumeRateWhenMoreThan5ItemsOfSameKind(){
		cart.addToCart(new ShoppingCartItem("My Test product","testCategory",6,100));
		assertEquals(540,cart.sum(),0.01);
	}
	
	@Test
	public void regularsShouldGetDiscount(){
		cart.addToCart(new ShoppingCartItem("My Test product","testCategory",1,300));
		cart.setCustmer(new Customer("Regular"));
		assertEquals(320,cart.sum(),0.01);
	}
	
	@Test
	public void regularsShouldNotPayFreightWhenPriceBeforeDiscountIsMoreThan400(){
		cart.addToCart(new ShoppingCartItem("My Test product","testCategory",2,300));
		cart.setCustmer(new Customer("Regular"));
		assertEquals(540,cart.sum(),0.01);
	}
	
	@Test
	public void shouldGetHolidaySpecial(){
		cart.addToCart(new ShoppingCartItem("My Test product","testCategory",9,100));
		cart.setTimeService(new HolidaySaleMock());
		cart.setCustmer(new Customer("Regular"));
		assertEquals(450,cart.sum(),0.01);
	}
	
	@Test
	public void shouldGetClearenceSpecial(){
		cart.addToCart(new ShoppingCartItem("12345","Discontinued",1,1000));
		cart.setCustmer(new Customer("Regular"));
		cart.setInventoryStatusService( new InventoryStatusMock());
		assertEquals(600,cart.sum(),0.01);
	}
	
	@Test
	public void holidaySpesialShouldOverideClearenceSpecial(){
		cart.addToCart(new ShoppingCartItem("12345","Discontinued",1,1000));
		cart.setCustmer(new Customer("Regular"));
		cart.setTimeService(new HolidaySaleMock());
		cart.setInventoryStatusService( new InventoryStatusMock());
		assertEquals(500,cart.sum(),0.01);
	}
	
	
	class HolidaySaleMock implements TimeService{

		public Date getDate() {
			
			return new Date(2015,11,26);
		}
		
	}
	
	class OrdinaryDayMock implements TimeService{

		public Date getDate() {
			
			return new Date(2015,01,26);
		}
		
	}
	
	class InventoryStatusMock implements InventoryStatusService{

		public int getInventoryStatus(String productId) {
			return 9;
		}
		
	}
}