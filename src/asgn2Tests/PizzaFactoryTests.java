package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Pizzas.VegetarianPizza;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Samuel Janetzki
 * 
 */
public class PizzaFactoryTests {
	LocalTime orderTime    = LocalTime.parse("20:00:00");
	LocalTime deliveryTime = LocalTime.parse("20:30:00");
	Pizza single_margherita;
	Pizza single_vegetarian;
	Pizza single_meatlovers;
	Pizza ten_margherita;
	Pizza ten_vegetarian;
	Pizza ten_meatlovers;
	Pizza single_pizza;
	Pizza ten_pizza;
	
	@Before
	public void ReferencePizzas() throws PizzaException {
		single_margherita = new MargheritaPizza(1,  orderTime, deliveryTime);
		single_vegetarian = new VegetarianPizza(1,  orderTime, deliveryTime);
		single_meatlovers = new MeatLoversPizza(1,  orderTime, deliveryTime);
		ten_margherita    = new MargheritaPizza(10, orderTime, deliveryTime);
		ten_vegetarian    = new VegetarianPizza(10, orderTime, deliveryTime);
		ten_meatlovers    = new MeatLoversPizza(10, orderTime, deliveryTime);
	}

	@Test
	public void GetPizzaSingleMargherita() throws PizzaException {
		String pizzaCode = "PZM";
		
		single_pizza = PizzaFactory.getPizza(pizzaCode, 1, orderTime, deliveryTime);
		
		assertTrue("Should return single margherita", single_pizza.equals(single_margherita));
	}
	
	@Test
	public void GetPizzaSingleVegetarian() throws PizzaException {
		String pizzaCode = "PZV";
		
		single_pizza = PizzaFactory.getPizza(pizzaCode, 1, orderTime, deliveryTime);
		
		assertTrue("Should return single vegetarian", single_pizza.equals(single_vegetarian));
	}
	
	@Test
	public void GetPizzaSingleMeatLovers() throws PizzaException {
		String pizzaCode = "PZL";
		
		single_pizza = PizzaFactory.getPizza(pizzaCode, 1, orderTime, deliveryTime);
		
		assertTrue("Should return single meat lovers", single_pizza.equals(single_meatlovers));
	}

	@Test
	public void GetPizzaTenMargherita() throws PizzaException {
		String pizzaCode = "PZM";
		
		ten_pizza = PizzaFactory.getPizza(pizzaCode, 10, orderTime, deliveryTime);
		
		assertTrue("Should return ten margherita", ten_pizza.equals(ten_margherita));
	}
	
	@Test
	public void GetPizzaTenVegetarian() throws PizzaException {
		String pizzaCode = "PZV";
		
		ten_pizza = PizzaFactory.getPizza(pizzaCode, 10, orderTime, deliveryTime);
		
		assertTrue("Should return ten vegetarian", ten_pizza.equals(ten_vegetarian));
	}
	
	@Test
	public void GetPizzaTenMeatLovers() throws PizzaException {
		String pizzaCode = "PZL";
		
		ten_pizza = PizzaFactory.getPizza(pizzaCode, 10, orderTime, deliveryTime);
		
		assertTrue("Should return ten meat lovers", ten_pizza.equals(ten_meatlovers));
	}
	
	@Test(expected = PizzaException.class)
	public void GetPizzaInvalidCode() throws PizzaException {
		String pizzaCode = "PZS";
		
		single_pizza = PizzaFactory.getPizza(pizzaCode, 1, orderTime, deliveryTime);
	}
}
