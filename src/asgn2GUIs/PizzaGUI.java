package asgn2GUIs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalTime;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature – as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Adam Bona and Samuel Janetzki
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	private PizzaRestaurant restaurant;
	private JFrame frame;
	private JMenu menuFile;
	private JMenu menuDisplay;
	private JTextArea eventLog;
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		frame = new JFrame(title);
		
		createMenuBar();
		createMainPanel(title);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocation(100, 50);
		frame.pack();
	}
	
	private void createMenuBar() {
		JMenuBar bar = new JMenuBar();
		menuFile     = new JMenu("Files");
		menuDisplay  = new JMenu("Displays");

		createMenuItem(menuFile, "Load", "load");
		createMenuItem(menuFile, "Reset", "reset");
		createMenuItem(menuDisplay, "Information", "info");
		createMenuItem(menuDisplay, "Calculations", "calcs");
		
		bar.add(menuFile);
		bar.add(menuDisplay);
		frame.setJMenuBar(bar);
	}

	private void createMenuItem(JMenu menu, String title, String command) {
		JMenuItem item = new JMenuItem(title);
		item.addActionListener(this);
		item.setActionCommand(command);
		menu.add(item);
	}

	private void createMainPanel(String title) {
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
        main.setBorder(new EmptyBorder(10, 10, 10, 10));
		
        eventLog = new JTextArea();
        
        JScrollPane viewWindow = new JScrollPane(eventLog);
        viewWindow.setPreferredSize(new Dimension(600, 400));
        
        main.add(viewWindow, BorderLayout.NORTH);
		frame.getContentPane().add(main);
	}

	@Override
	public void run() {
		restaurant = new PizzaRestaurant();
		
		menuFile.getMenuComponent(0).setEnabled(true);
		menuFile.getMenuComponent(1).setEnabled(false);
		menuDisplay.getMenuComponent(0).setEnabled(false);
		menuDisplay.getMenuComponent(1).setEnabled(false);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String move = event.getActionCommand();
		
		if (move == "load") {
			loadFile();
		} else if (move == "reset") {
			resetData();
		} else if (move == "info") {
			displayInformation();
		} else if (move == "calcs") {
			displayCalculations();
		}
	}

	private void loadFile() {
		JFileChooser chooser = new JFileChooser();
		File file            = new File(System.getProperty("user.dir") + "/logs");
		
		chooser.setCurrentDirectory(file);
		int response = chooser.showOpenDialog(frame);
		
		if (response == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			
			try {
				// load log file into system
				restaurant.processLog(file.getPath());
				
				menuFile.getMenuComponent(0).setEnabled(false);
				menuFile.getMenuComponent(1).setEnabled(true);
				menuDisplay.getMenuComponent(0).setEnabled(true);
				menuDisplay.getMenuComponent(1).setEnabled(true);
				eventLog.append(file.getName() + " loaded to database\n");
			} catch (CustomerException | PizzaException | LogHandlerException error) {
				// handle failed load
				restaurant.resetDetails();
				JOptionPane.showMessageDialog(frame, error.getMessage());
			}
        }
	}

	private void resetData() {
		restaurant.resetDetails();

		menuFile.getMenuComponent(0).setEnabled(true);
		menuFile.getMenuComponent(1).setEnabled(false);
		menuDisplay.getMenuComponent(0).setEnabled(false);
		menuDisplay.getMenuComponent(1).setEnabled(false);
		eventLog.setText("cleared database\n");
	}
	
	private void displayInformation() {
		int customerSize = restaurant.getNumCustomerOrders();
//		int pizzaSize    = restaurant.getNumPizzaOrders();
		String[][] customerData = new String[customerSize][];
//		String[][] pizzaData    = new String[pizzaSize][];
		
		String[] customerHeaders = {
				"Customer Name",
				"Mobile Number",
				"Customer Type",
				"Coordinates",
				"Distance"
		};
		
//		String[] pizzaHeaders    = {
//				"Pizza Type",
//				"Quantity",
//				"Order Price",
//				"Order Cost",
//				"Order Profit"
//		};
		
		try {
			for (int i = 0; i < customerSize; i++) {
				Customer customer = restaurant.getCustomerByIndex(i);
				customerData[i] = new String[] {
						customer.getName(),
						customer.getMobileNumber(),
						customer.getCustomerType(),
						customer.getLocationX() + ", " + customer.getLocationY(),
						String.format("%.2f", customer.getDeliveryDistance())
				};
			}
		} catch (CustomerException error) {
			JOptionPane.showMessageDialog(frame, error.getMessage());
		}
		
//		try {
//			for (int i = 0; i < pizzaSize; i++) {
//				Pizza pizza = restaurant.getPizzaByIndex(i);
//				pizzaData[i] = new String[] {
//						pizza.getPizzaType(),
//						String.format("%d", pizza.getQuantity()),
//						String.format("%.2f", pizza.getOrderPrice()),
//						String.format("%.2f", pizza.getOrderCost()),
//						String.format("%.2f", pizza.getOrderProfit())
//				};
//			}
//		} catch (PizzaException error) {
//			JOptionPane.showMessageDialog(frame, error.getMessage());
//		}
		
//		DISPLAY TO BE REPLACED WITH
//		JTable(Object[][] rowData, Object[] columnNames)
		
		eventLog.append(String.format("%-25s\t%s\t%s\t%s\t%s\n",
				customerHeaders[0],
				customerHeaders[1],
				customerHeaders[2],
				customerHeaders[3],
				customerHeaders[4]
		));
		
		for (int i = 0; i < customerSize; i++) {
			eventLog.append(String.format("%-25s\t%s\t%s\t%s\t%s\n",
					customerData[i][0],
					customerData[i][1],
					customerData[i][2],
					customerData[i][3],
					customerData[i][4]
			));
		}

//		eventLog.append(String.format("%s\t%s\t%s\t%s\t%s\n",
//				pizzaHeaders[0],
//				pizzaHeaders[1],
//				pizzaHeaders[2],
//				pizzaHeaders[3],
//				pizzaHeaders[4]
//		));

//		for (int i = 0; i < pizzaSize; i++) {
//			eventLog.append(String.format("%s\t%s\t%s\t%s\t%s\n",
//					pizzaData[i][0],
//					pizzaData[i][1],
//					pizzaData[i][2],
//					pizzaData[i][3],
//					pizzaData[i][4]
//			));
//		}
	}
	
	private void displayCalculations() {
		double distance = restaurant.getTotalDeliveryDistance();
//		restaurant.getTotalProfit();
		
		eventLog.append(String.format("Total Delivery Distance: %.2f blocks\n", distance));
	}
}
