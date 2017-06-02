package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.VegetarianPizza;
import asgn2Restaurant.LogHandler;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Samuel Janetzki
* 
*/
public class LogHandlerPizzaTests {
	LocalTime orderTime;
	LocalTime deliveryTime;
	Pizza margherita;
	Pizza vegetarian;
	Pizza meatlovers;
	Pizza pizza;
	ArrayList<Pizza> list;

	@Before
	public void ReferencePizzas() throws PizzaException {
		orderTime    = LocalTime.parse("21:00:00");
		deliveryTime = LocalTime.parse("21:35:00");
		margherita   = new MargheritaPizza(1,  orderTime, deliveryTime);
		vegetarian   = new VegetarianPizza(2,  orderTime, deliveryTime);
		meatlovers   = new MeatLoversPizza(3,  orderTime, deliveryTime);
	}
	
	// test creating pizzas

	@Test
	public void CreatePizzaMargherita() throws PizzaException {
		String line = "21:00:00,21:35:00,April O'Neal,0987654321,DNC,3,4,PZM,1";

		try {
			pizza = LogHandler.createPizza(line);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", pizza, margherita);
	}

	@Test
	public void CreatePizzaVegetarian() throws PizzaException {
		String line = "21:00:00,21:35:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		
		try {
			pizza = LogHandler.createPizza(line);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", pizza, margherita);
	}

	@Test
	public void CreatePizzaMeatLovers() throws PizzaException {
		String line = "21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3";
		
		try {
			pizza = LogHandler.createPizza(line);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", pizza, margherita);
	}
}
