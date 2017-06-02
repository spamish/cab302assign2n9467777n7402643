package asgn2Customers;

import asgn2Exceptions.CustomerException;


/** An abstract class to represent a customer at the Pizza Palace restaurant.
 *  The Customer class is used as a base class of PickUpCustomer, 
 *  DriverDeliveryCustomer and DroneDeliverCustomer. Each of these subclasses overwrites
 *  the abstract method getDeliveryDistance. A description of the class's
 * fields and their constraints is provided in Section 5.2 of the Assignment Specification.  
 * 
 * @author Samuel Janetzki
*/
public abstract class Customer {
	String name, mobileNumber, type;
	int locationX, locationY;
	
	/**
	 *  This class represents a customer of the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.2. 
	 *  A CustomerException is thrown if the any of the constraints listed in Section 5.2 of the Assignment Specification
	 *  are violated. 
	 *  
  	 * <P> PRE: True
  	 * <P> POST: All field values are set
  	 * 
	 * @param name - The Customer's name 
	 * @param mobileNumber - The customer mobile number
	 * @param locationX - The customer x location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param locationY - The customer y location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param type - A human understandable description of this Customer type
	 * @throws CustomerException if supplied parameters are invalid 
	 * 
	 */
	public Customer(String name, String mobileNumber, int locationX, int locationY, String type) throws CustomerException {
		if ((name.length() < 1) || (name.length() > 20)) throw new CustomerException("Invlaid name length");
		if (name.matches("^[ ]*$")) throw new CustomerException("Name string is empty");
		if (!mobileNumber.matches("^0[0-9]{9}$")) throw new CustomerException("Invalid mobile number");
		if ((locationX < -10) || (locationX > 10)) throw new CustomerException("Invalid longtitude" + locationX + " " + locationY);
		if ((locationY < -10) || (locationY > 10)) throw new CustomerException("Invalid latitude" + locationX + " " + locationY);
		
		this.name         = name;
		this.mobileNumber = mobileNumber;
		this.locationX    = locationX;
		this.locationY    = locationY;
		this.type         = type;
	}
	
	/**
	 * Returns the Customer's name.
	 * @return The Customer's name.
	 */
	public final String getName() {
		return this.name;
	}
	
	/**
	 * Returns the Customer's mobile number.
	 * @return The Customer's mobile number.
	 */
	public final String getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * Returns a human understandable description of the Customer's type. 
	 * The valid alternatives are listed in Section 5.2 of the Assignment Specification. 
	 * @return A human understandable description of the Customer's type.
	 */
	public final String getCustomerType() {
		return this.type;
	}
	
	/**
	 * Returns the Customer's X location which is the number of blocks East or West 
	 * that the Customer is located relative to the Pizza Palace restaurant. 
	 * @return The Customer's X location
	 */
	public final int getLocationX() {
		return this.locationX;
	}

	/**
	 * Returns the Customer's Y location which is the number of blocks North or South 
	 * that the Customer is located relative to the Pizza Palace restaurant. 
	 * @return The Customer's Y location
	 */
	public final int getLocationY() {
		return this.locationY;
	}

	/**
	 * An abstract method that returns the distance between the Customer and 
	 * the restaurant depending on the mode of delivery. 
	 * @return The distance between the restaurant and the Customer depending on the mode of delivery.
	 */
	public abstract double getDeliveryDistance();
	
	/**
	 * Compares *this* Customer object with an instance of an *other* Customer object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 *  You do not need to test this method.
	 * 
	 * @return true if *this* Customer object and the *other* Customer object have the same values returned for 	
	 * getName(),getMobileNumber(),getLocationX(),getLocationY(),getCustomerType().
	 */
	@Override
	public boolean equals(Object other) {
		Customer otherCustomer = (Customer) other;

		return ( (this.getName().equals(otherCustomer.getName()))  &&
			(this.getMobileNumber().equals(otherCustomer.getMobileNumber())) && 
			(this.getLocationX() == otherCustomer.getLocationX()) && 
			(this.getLocationY() == otherCustomer.getLocationY()) && 
			(this.getCustomerType().equals(otherCustomer.getCustomerType())) );			
	}

}
