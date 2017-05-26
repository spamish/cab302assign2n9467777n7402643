package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Samuel Janetzki
 *
 */
public class PizzaTests {
	
	// test quantities

	@Test(expected = PizzaException.class)
	public void PizzaQuantityNone() throws PizzaException {
		int quantity           = 0;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void PizzaQuantityOneTen() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals("Quantity should be 1", 1, pizza.getQuantity());
	}

	@Test
	public void PizzaQuantityTen() throws PizzaException {
		int quantity           = 10;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals("Quantity should be 10", 10, pizza.getQuantity());
	}

	@Test(expected = PizzaException.class)
	public void PizzaQuantityEleven() throws PizzaException {
		int quantity           = 11;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	// test order times

	@Test(expected = PizzaException.class)
	public void PizzaNoOrderTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = null;
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test(expected = PizzaException.class)
	public void PizzaOrderBeforeOpen() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("18:59:59");
		LocalTime deliveryTime = LocalTime.parse("19:30:00");
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void PizzaEarlyOrderTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("19:00:00");
		LocalTime deliveryTime = LocalTime.parse("19:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should order at opening time", pizza);
	}

	@Test
	public void PizzaLateOrderTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("23:00:00");
		LocalTime deliveryTime = LocalTime.parse("23:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should order at closing time", pizza);
	}

	@Test(expected = PizzaException.class)
	public void PizzaOrderAfterClose() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("23:00:01");
		LocalTime deliveryTime = LocalTime.parse("23:30:00");
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	// test delivery times

	@Test(expected = PizzaException.class)
	public void PizzaNoDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = null;
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test(expected = PizzaException.class)
	public void PizzaPrematureDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:09:59");
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void PizzaEarlyDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:10:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should deliver just after cooking", pizza);
	}

	@Test
	public void PizzaLateDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("21:00:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should deliver just before throwing out", pizza);
	}

	@Test(expected = PizzaException.class)
	public void PizzaOverdueDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("21:00:01");
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void PizzaClosedDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("22:30:00");
		LocalTime deliveryTime = LocalTime.parse("23:00:01");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should deliver just after closing time", pizza);
	}

	@Test
	public void PizzaLatestDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("23:00:00");
		LocalTime deliveryTime = LocalTime.parse("24:00:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should deliver at latest possible time", pizza);
	}

	// test cost of individual pizza toppings

	@Test
	public void PizzaCostPerPizzaMargherita() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MargheritaPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(1.5, pizza.getCostPerPizza(), 0.001);
	}

	@Test
	public void PizzaCostPerPizzaVegetarian() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new VegetarianPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(5.5, pizza.getCostPerPizza(), 0.001);
	}

	@Test
	public void PizzaCostPerPizzaMeatLovers() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(5.0, pizza.getCostPerPizza(), 0.001);
	}

    // test sale price of individual pizza
    
	@Test
	public void PizzaPricePerPizzaMargherita() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MargheritaPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(8.0, pizza.getPricePerPizza(), 0.0001);
	}
    
	@Test
	public void PizzaPricePerPizzaVegetarian() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new VegetarianPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(10.0, pizza.getPricePerPizza(), 0.0001);
	}
    
	@Test
	public void PizzaPricePerPizzaMeatLovers() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(12.0, pizza.getPricePerPizza(), 0.0001);
	}
    
    // test cost of all pizzas

	@Test
	public void PizzaOrderCostSingle() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(5.0, pizza.getOrderCost(), 0.0001);
	}

	@Test
	public void PizzaOrderCostTen() throws PizzaException {
		int quantity           = 10;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(50.0, pizza.getOrderCost(), 0.0001);
	}

    // test sale price of all pizzas
    
	@Test
	public void PizzaOrderPriceSingle() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(12.0, pizza.getOrderPrice(), 0.0001);
	}

	@Test
	public void PizzaOrderPriceTen() throws PizzaException {
		int quantity           = 10;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(120.0, pizza.getOrderPrice(), 0.0001);
	}

    // test profit from all pizzas
    
	@Test
	public void PizzaOrderProfitSingle() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(7.0, pizza.getOrderProfit(), 0.0001);
	}

	@Test
	public void PizzaOrderProfitTen() throws PizzaException {
		int quantity           = 10;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(70.0, pizza.getOrderProfit(), 0.0001);
	}

	// test pizza toppings

	@Test
	public void PizzaToppingsIncludedMargherita() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MargheritaPizza(quantity, orderTime, deliveryTime);
		
		assertTrue("Should contain tomato", pizza.containsTopping(PizzaTopping.TOMATO));
		assertTrue("Should contain cheese", pizza.containsTopping(PizzaTopping.CHEESE));
	}

	@Test
	public void PizzaToppingsIncludedVegetarian() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new VegetarianPizza(quantity, orderTime, deliveryTime);
		
		assertTrue("Should contain tomato",   pizza.containsTopping(PizzaTopping.TOMATO));
		assertTrue("Should contain cheese",   pizza.containsTopping(PizzaTopping.CHEESE));
		assertTrue("Should contain eggplant", pizza.containsTopping(PizzaTopping.EGGPLANT));
		assertTrue("Should contain mushroom", pizza.containsTopping(PizzaTopping.MUSHROOM));
		assertTrue("Should contain capsicum", pizza.containsTopping(PizzaTopping.CAPSICUM));
	}

	@Test
	public void PizzaToppingsIncludedMeatLovers() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertTrue("Should contain tomato",    pizza.containsTopping(PizzaTopping.TOMATO));
		assertTrue("Should contain cheese",    pizza.containsTopping(PizzaTopping.CHEESE));
		assertTrue("Should contain bacon",     pizza.containsTopping(PizzaTopping.BACON));
		assertTrue("Should contain pepperoni", pizza.containsTopping(PizzaTopping.PEPPERONI));
		assertTrue("Should contain salami",    pizza.containsTopping(PizzaTopping.SALAMI));
	}

	@Test
	public void PizzaToppingsExcludedMargherita() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MargheritaPizza(quantity, orderTime, deliveryTime);
		
		assertFalse("Shouldn't contain bacon",     pizza.containsTopping(PizzaTopping.BACON));
		assertFalse("Shouldn't contain pepperoni", pizza.containsTopping(PizzaTopping.PEPPERONI));
		assertFalse("Shouldn't contain salami",    pizza.containsTopping(PizzaTopping.SALAMI));
		assertFalse("Shouldn't contain eggplant",  pizza.containsTopping(PizzaTopping.EGGPLANT));
		assertFalse("Shouldn't contain mushroom",  pizza.containsTopping(PizzaTopping.MUSHROOM));
		assertFalse("Shouldn't contain capsicum",  pizza.containsTopping(PizzaTopping.CAPSICUM));
	}

	@Test
	public void PizzaToppingsExcludedVegetarian() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new VegetarianPizza(quantity, orderTime, deliveryTime);
		
		assertFalse("Shouldn't contain bacon",     pizza.containsTopping(PizzaTopping.BACON));
		assertFalse("Shouldn't contain pepperoni", pizza.containsTopping(PizzaTopping.PEPPERONI));
		assertFalse("Shouldn't contain salami",    pizza.containsTopping(PizzaTopping.SALAMI));
	}

	@Test
	public void PizzaToppingsExcludedMeatLovers() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertFalse("Shouldn't contain eggplant", pizza.containsTopping(PizzaTopping.EGGPLANT));
		assertFalse("Shouldn't contain mushroom", pizza.containsTopping(PizzaTopping.MUSHROOM));
		assertFalse("Shouldn't contain capsicum", pizza.containsTopping(PizzaTopping.CAPSICUM));
	}

	// test pizza types

	@Test
	public void PizzaTypeMargherita() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MargheritaPizza(quantity, orderTime, deliveryTime);
		
		assertEquals("Name should be \"Margherita\"", "Margherita", pizza.getPizzaType());
	}

	@Test
	public void PizzaTypeVegetarian() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new VegetarianPizza(quantity, orderTime, deliveryTime);
		
		assertEquals("Name should be \"Vegetarian\"", "Vegetarian", pizza.getPizzaType());
	}

	@Test
	public void PizzaTypeMeatLovers() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals("Name should be \"Meat Lovers\"", "Meat Lovers", pizza.getPizzaType());
	}
}