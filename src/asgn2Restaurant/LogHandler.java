package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Adam Bona and Samuel Janetzki
 *
 */
public class LogHandler {
	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new LogHandlerException(e);
		}
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				customers.add(createCustomer(line));
			}
			
			reader.close();
		} catch (IOException e) {
			throw new LogHandlerException(e);
		}
		
		return customers;
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException {
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new LogHandlerException(e);
		}
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
//				pizzas.add(createPizza(line));
			}
			
			reader.close();
		} catch (IOException e) {
			throw new LogHandlerException(e);
		}
		
		return pizzas;
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException {
		String[] elements = line.split(",");
		if (elements.length != 9) throw new LogHandlerException("Incompatible log line");
		
//		String name         = elements[2];
//		String mobileNumber = elements[3];
//		String customerCode = elements[4];
//		int locationX       = Integer.parseInt(elements[5]);
//		int locationY       = Integer.parseInt(elements[6]);
		
		LocalTime orderTime    = LocalTime.parse(elements[0]);
		LocalTime deliveryTime = LocalTime.parse(elements[1]);
		String name            = elements[2];
		String mobileNumber    = elements[3];
		String customerCode    = elements[4];
		int locationX          = Integer.parseInt(elements[5]);
		int locationY          = Integer.parseInt(elements[6]);
		String pizzaCode       = elements[7];
		int quantity           = Integer.parseInt(elements[8]);
		
		
		
		
		System.out.println("'"
				+ orderTime + "' '"
				+ deliveryTime + "' '"
				+ name + "' '"
				+ mobileNumber + "' '"
				+ customerCode + "' '"
				+ locationX + "' '"
				+ locationY + "' '"
				+ pizzaCode + "' '"
				+ quantity + "'");
		
//		return CustomerFactory.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
		return null;
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException {
//		String[] elements = line.split("'");
//		if (elements.length != 9) throw new LogHandlerException("Incompatible log line");
		
//		LocalTime orderTime    = LocalTime.parse(elements[0]);
//		LocalTime deliveryTime = LocalTime.parse(elements[1]);
//		String pizzaCode       = elements[7];
//		int quantity           = Integer.parseInt(elements[8]);
		
//		return PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
	}

}
