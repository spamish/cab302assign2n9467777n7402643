package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Restaurant.LogHandler;
/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Adam Bona
 * 
 *
 */
public class CustomerTests {


	
	
	@Before 
	public void ReferenceCustomer() throws CustomerException {
	
				
	}
	
	
	
	@Test
	public void DriverDeliveryCustomer() throws CustomerException {
		
	}
	
	@Test
	public void DroneDeliveryCustomer() throws CustomerException {
		
	}
	
	@Test
	public void PickUpCustomer() throws CustomerException {
		
	}
}
