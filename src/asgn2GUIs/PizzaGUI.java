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
	final static String LOAD  = "Load Log File";
	final static String CLEAR = "Clear Log Data";
	final static String INFO = "Customers and Pizzas";
	final static String CALCS = "Logistics and Finances";  
	private PizzaRestaurant restaurant;
	private String title;
	private JMenuItem load, clear, info, calcs;
	private JLabel message;
	private JPanel cards;
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		this.restaurant = new PizzaRestaurant();
		this.title = title;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		
		switch (command) {
			case LOAD:  loadData();
				break;
			case CLEAR: resetData();
				break;
			case INFO:  displayInformation();
				break;
			case CALCS: displayCalculations();
				break;
		}
	}
	
	@Override
	public void run() {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocation(100, 50);
		
		frame.setJMenuBar(createMenu());
		message = new JLabel("", SwingConstants.CENTER);

		JPanel blank = new JPanel();
		blank.setPreferredSize(new Dimension(600, 400));
		blank.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel load = new JPanel();
		load.setPreferredSize(new Dimension(600, 400));
		load.setBorder(new EmptyBorder(10, 10, 10, 10));
		load.add(message, BorderLayout.CENTER);

		cards = new JPanel(new CardLayout());
		cards.add(CLEAR, blank);
		cards.add(LOAD, load);
		
		frame.getContentPane().add(cards);
		frame.pack();
		frame.setVisible(true);
		
		setLayerVisibility(CLEAR);
		setMenuVisibility(true, false, false, false);
	}

	private JMenuBar createMenu() {
		load = new JMenuItem(LOAD);
		load.setActionCommand(LOAD);
		load.addActionListener(this);

		clear = new JMenuItem(CLEAR);
		clear.setActionCommand(CLEAR);
		clear.addActionListener(this);

		info = new JMenuItem(INFO);
		info.setActionCommand(INFO);
		info.addActionListener(this);

		calcs = new JMenuItem(CALCS);
		calcs.setActionCommand(CALCS);
		calcs.addActionListener(this);
		
		JMenu menuFile = new JMenu("File");
		menuFile.add(load);
		menuFile.add(clear);
		
		JMenu menuDisp = new JMenu("Display Information");
		menuDisp.add(info);
		menuDisp.add(calcs);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuFile);
		menuBar.add(menuDisp);
		
		return menuBar;
	}

	private void loadData() {
		JFileChooser chooser = new JFileChooser();
		File file = new File(System.getProperty("user.dir") + "/logs");
		
		chooser.setCurrentDirectory(file);
		int response = chooser.showOpenDialog(this.getContentPane());
		
		if (response == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			
			try {
				// load log file into system
				restaurant.processLog(file.getPath());
				message.setText("Proccessed log file " + file.getName());
				setLayerVisibility(LOAD);
				setMenuVisibility(false, true, true, true);
			} catch (CustomerException | PizzaException | LogHandlerException error) {
				// handle failed load
				restaurant.resetDetails();
				JOptionPane.showMessageDialog(this.getContentPane(), error.getMessage());
			}
        }
	}

	private void resetData() {
		restaurant.resetDetails();
		setLayerVisibility(CLEAR);
		setMenuVisibility(true, false, false, false);
	}
	
	private void displayInformation() {
//		int customerSize = restaurant.getNumCustomerOrders();
////		int pizzaSize    = restaurant.getNumPizzaOrders();
//		String[][] customerData = new String[customerSize][];
////		String[][] pizzaData    = new String[pizzaSize][];
//		
//		String[] customerHeaders = {
//				"Customer Name",
//				"Mobile Number",
//				"Customer Type",
//				"Coordinates",
//				"Distance"
//		};
//		
////		String[] pizzaHeaders    = {
////				"Pizza Type",
////				"Quantity",
////				"Order Price",
////				"Order Cost",
////				"Order Profit"
////		};
//		
//		try {
//			for (int i = 0; i < customerSize; i++) {
//				Customer customer = restaurant.getCustomerByIndex(i);
//				customerData[i] = new String[] {
//						customer.getName(),
//						customer.getMobileNumber(),
//						customer.getCustomerType(),
//						customer.getLocationX() + ", " + customer.getLocationY(),
//						String.format("%.2f", customer.getDeliveryDistance())
//				};
//			}
//		} catch (CustomerException error) {
//			JOptionPane.showMessageDialog(frame, error.getMessage());
//		}
//		
////		try {
////			for (int i = 0; i < pizzaSize; i++) {
////				Pizza pizza = restaurant.getPizzaByIndex(i);
////				pizzaData[i] = new String[] {
////						pizza.getPizzaType(),
////						String.format("%d", pizza.getQuantity()),
////						String.format("%.2f", pizza.getOrderPrice()),
////						String.format("%.2f", pizza.getOrderCost()),
////						String.format("%.2f", pizza.getOrderProfit())
////				};
////			}
////		} catch (PizzaException error) {
////			JOptionPane.showMessageDialog(frame, error.getMessage());
////		}
//		
////		DISPLAY TO BE REPLACED WITH
////		JTable(Object[][] rowData, Object[] columnNames)
//		
//		eventLog.append(String.format("%-25s\t%s\t%s\t%s\t%s\n",
//				customerHeaders[0],
//				customerHeaders[1],
//				customerHeaders[2],
//				customerHeaders[3],
//				customerHeaders[4]
//		));
//		
//		for (int i = 0; i < customerSize; i++) {
//			eventLog.append(String.format("%-25s\t%s\t%s\t%s\t%s\n",
//					customerData[i][0],
//					customerData[i][1],
//					customerData[i][2],
//					customerData[i][3],
//					customerData[i][4]
//			));
//		}
//
////		eventLog.append(String.format("%s\t%s\t%s\t%s\t%s\n",
////				pizzaHeaders[0],
////				pizzaHeaders[1],
////				pizzaHeaders[2],
////				pizzaHeaders[3],
////				pizzaHeaders[4]
////		));
//
////		for (int i = 0; i < pizzaSize; i++) {
////			eventLog.append(String.format("%s\t%s\t%s\t%s\t%s\n",
////					pizzaData[i][0],
////					pizzaData[i][1],
////					pizzaData[i][2],
////					pizzaData[i][3],
////					pizzaData[i][4]
////			));
////		}
	}
	
	private void displayCalculations() {
//		double distance = restaurant.getTotalDeliveryDistance();
////		restaurant.getTotalProfit();
//		
//		eventLog.append(String.format("Total Delivery Distance: %.2f blocks\n", distance));
	}

	private void setLayerVisibility(String name) {
		CardLayout layout = (CardLayout) cards.getLayout();
		layout.show(cards, name);
	}
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param loadArg - true if user can load a file
	 * @param clearArg - true if user can reset the system
	 * @param infoArg - true if user can display information
	 * @param calcsArg - true if user can display calculations
	 */
	private void setMenuVisibility(boolean loadArg, boolean clearArg, boolean infoArg, boolean calcsArg) {
		load.setEnabled(loadArg);
		clear.setEnabled(clearArg);
		info.setEnabled(infoArg);
		calcs.setEnabled(calcsArg);
	}
}
