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
	ArrayList<Pizza> reference;

	@Before
	public void ReferencePizzas() throws PizzaException {
		orderTime    = LocalTime.parse("20:00:00");
		deliveryTime = LocalTime.parse("20:30:00");
		margherita   = new MargheritaPizza(1,  orderTime, deliveryTime);
		vegetarian   = new VegetarianPizza(1,  orderTime, deliveryTime);
		meatlovers   = new MeatLoversPizza(1,  orderTime, deliveryTime);
		pizza        = null;
		
		list.clear();
		reference.add(margherita);
		reference.add(vegetarian);
		reference.add(meatlovers);
	}
	
	// test creating pizzas

	@Test
	public void CreatePizzaMargherita() throws PizzaException {
		String line = "";
		
		try {
			pizza = LogHandler.createPizza(line);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", pizza, margherita);
	}

	@Test
	public void CreatePizzaVegetarian() throws PizzaException {
		String line = "";
		
		try {
			pizza = LogHandler.createPizza(line);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", pizza, margherita);
	}

	@Test
	public void CreatePizzaMeatLovers() throws PizzaException {
		String line = "";
		
		try {
			pizza = LogHandler.createPizza(line);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", pizza, margherita);
	}

	@Test
	public void CreatePizzaInvalidCode() throws PizzaException {
		String line = "";
		
		try {
			pizza = LogHandler.createPizza(line);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", pizza, margherita);
	}

	@Test
	public void CreatePizza() throws PizzaException {
		String line = "";
		
		try {
			pizza = LogHandler.createPizza(line);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", pizza, margherita);
	}
	
	// test creating pizza dataset
	
	@Test
	public void PopulatePizzaDataset() throws PizzaException {
		String filename = "";
		
		try {
			list = LogHandler.populatePizzaDataset(filename);
		} catch (LogHandlerException e) {
			e.printStackTrace();
		}
		
		assertEquals("", list, reference);
	}
	
}
