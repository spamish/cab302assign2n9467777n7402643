package asgn2Tests;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.CustomerFactory;
import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;


/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Adam Bona
 *
 */
public class CustomerFactoryTests {
	String name;
	String mobileNumber;
	int locationX;
	int locationY;
	Customer customer;
	
	//Setup
	
	@Before
	public void ReferenceCustomer() throws CustomerException {
		name = "Adam";
		mobileNumber = "0465734365";
		locationX = 3;
		locationY = 2;
	}
	
	//Tests
	
	@Test
	public void getCustomer() throws CustomerException {
		String temp = "DVC";
		
		try {
			customer = CustomerFactory.getCustomer(temp, name, mobileNumber, locationX, locationY);
		} catch (CustomerException e) {
			e.printStackTrace();
		}

	}		

}
