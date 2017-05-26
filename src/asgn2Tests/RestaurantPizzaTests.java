package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Samuel Janetzki
 *
 */
public class RestaurantPizzaTests {
	PizzaRestaurant pizzaRestaurant = new PizzaRestaurant();
	
	// test log processing
	
	@Test
	public void ProcessLog() throws PizzaException, CustomerException, LogHandlerException {
		String filename = "";
		pizzaRestaurant.processLog(filename);
	}
	
	// test pizza fetching
	
	@Test
	public void GetPizzaByIndex() throws PizzaException {
		int index = 0;
		pizzaRestaurant.getPizzaByIndex(index);
	}
	
	// test that correct number of pizzas are coming back
	
	@Test
	public void GetNumPizzaOrders() {
		pizzaRestaurant.getNumPizzaOrders();
	}
	
	// test that profiteering is accurate
	
	@Test
	public void GetTotalProfit() {
		pizzaRestaurant.getTotalProfit();
	}
	
	// test that pizzas are put to bed at night
	
	@Test
	public void ResetDetailsCheckEmpty() {
		pizzaRestaurant.resetDetails();
		
		assertEquals("Pizza dataset not empty", 0, pizzaRestaurant.getNumPizzaOrders());
	}
}
