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
	
	// test that an order contains at least 1 pizza
	
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

	// test that an order contains at most 10 pizzas
	
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
	
	// test that pizza cannot be ordered before 7pm

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

	// test that a pizza cannot be ordered after 11pm
	
	@Test
	public void PizzaLateOrderTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("22:59:59");
		LocalTime deliveryTime = LocalTime.parse("23:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should order at closing time", pizza);
	}

	@Test(expected = PizzaException.class)
	public void PizzaOrderAfterClose() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("23:00:00");
		LocalTime deliveryTime = LocalTime.parse("23:30:00");
		
		new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	// test that a pizza is cooked

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
	
	// test that a pizza wont be a biohazard

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

	// test that a pizza can be delivered after 11pm
	
	@Test
	public void PizzaClosedDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("22:30:00");
		LocalTime deliveryTime = LocalTime.parse("23:00:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should deliver just after closing time", pizza);
	}

	@Test
	public void PizzaLatestDeliveryTime() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("22:59:59");
		LocalTime deliveryTime = LocalTime.parse("23:59:59");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertNotNull("Should deliver at latest possible time", pizza);
	}

	// test the individual cost
	
	@Test
	public void PizzaCostPerPizzaSingleMeatLovers() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(5.0, pizza.getCostPerPizza(), 0.001);
	}

	@Test
	public void PizzaCostPerPizzaMultipleMeatLovers() throws PizzaException {
		int quantity           = 10;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(5.0, pizza.getCostPerPizza(), 0.001);
	}

	// test the individual sale price

	@Test
	public void PizzaPricePerPizzaSingle() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(12.0, pizza.getPricePerPizza(), 0.0001);
	}

	@Test
	public void PizzaPricePerPizzaTen() throws PizzaException {
		int quantity           = 10;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals(12.0, pizza.getPricePerPizza(), 0.0001);
	}
    
    // test the order cost

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

    // test the order sale price
    
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

    // test the order profit
    
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

	// test margherita toppings

	@Test
	public void MargheritaPizzaToppingsIncluded() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MargheritaPizza(quantity, orderTime, deliveryTime);
		
		assertTrue("Should contain tomato", pizza.containsTopping(PizzaTopping.TOMATO));
		assertTrue("Should contain cheese", pizza.containsTopping(PizzaTopping.CHEESE));
	}

	@Test
	public void MargheritaPizzaToppingsExcluded() throws PizzaException {
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

	// test vegetarian toppings
	
	@Test
	public void VegetarianPizzaToppingsIncluded() throws PizzaException {
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
	public void VegetarianPizzaToppingsExcluded() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new VegetarianPizza(quantity, orderTime, deliveryTime);
		
		assertFalse("Shouldn't contain bacon",     pizza.containsTopping(PizzaTopping.BACON));
		assertFalse("Shouldn't contain pepperoni", pizza.containsTopping(PizzaTopping.PEPPERONI));
		assertFalse("Shouldn't contain salami",    pizza.containsTopping(PizzaTopping.SALAMI));
	}
	
	// test meat lovers toppings

	@Test
	public void MeatLoversPizzaToppingsIncluded() throws PizzaException {
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
	public void MeatLoversPizzaToppingsExcluded() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertFalse("Shouldn't contain eggplant", pizza.containsTopping(PizzaTopping.EGGPLANT));
		assertFalse("Shouldn't contain mushroom", pizza.containsTopping(PizzaTopping.MUSHROOM));
		assertFalse("Shouldn't contain capsicum", pizza.containsTopping(PizzaTopping.CAPSICUM));
	}

	// test that pizza names are assigned correctly

	@Test
	public void PizzaFactoryTypeMargherita() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MargheritaPizza(quantity, orderTime, deliveryTime);
		
		assertEquals("Name should be \"Margherita\"", "Margherita", pizza.getPizzaType());
	}

	@Test
	public void PizzaFactoryTypeVegetarian() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new VegetarianPizza(quantity, orderTime, deliveryTime);
		
		assertEquals("Name should be \"Vegetarian\"", "Vegetarian", pizza.getPizzaType());
	}

	@Test
	public void PizzaFactoryTypeMeatLovers() throws PizzaException {
		int quantity           = 1;
		LocalTime orderTime    = LocalTime.parse("20:00:00");
		LocalTime deliveryTime = LocalTime.parse("20:30:00");
		
		Pizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		
		assertEquals("Name should be \"Meat Lovers\"", "Meat Lovers", pizza.getPizzaType());
	}
}