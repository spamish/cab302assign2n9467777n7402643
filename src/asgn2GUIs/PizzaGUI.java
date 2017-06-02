package asgn2GUIs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;


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
	private JPanel main;
	private JPanel info;
	private JPanel calc;
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.setLocation(100, 50);
		frame.setSize(600, 600);
		
		createMenuBar();
		createMainPanel();
		createInformationPanel();
		createCalculationsPanel();
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

	private void createMainPanel() {
		JLabel label = new JLabel("Hello Java Swing World");
		frame.getContentPane().add(label);
	}

	private void createInformationPanel() {
		
	}

	private void createCalculationsPanel() {
		
	}
	
	private void createMenuItem(JMenu menu, String title, String command) {
		JMenuItem item = new JMenuItem(title);
		item.addActionListener(this);
		item.setActionCommand(command);
		menu.add(item);
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
				
//				print loading confirmation to main window
			} catch (CustomerException | PizzaException | LogHandlerException error) {
				// handle failed load
				restaurant.resetDetails();
				JOptionPane.showMessageDialog(frame, error.getMessage());
			}
        }
	}

	private void resetData() {
		// clear system data
		menuFile.getMenuComponent(0).setEnabled(true);
		menuFile.getMenuComponent(1).setEnabled(false);
		menuDisplay.getMenuComponent(0).setEnabled(false);
		menuDisplay.getMenuComponent(1).setEnabled(false);
		
		restaurant.resetDetails();
	}
	
	private void displayInformation() {
		int index;
		
//		index = restaurant.getNumCustomerOrders();
//		restaurant.getCustomerByIndex(index);
//		index = restaurant.getNumPizzaOrders();
//		restaurant.getPizzaByIndex(index);
		
	}
	
	private void displayCalculations() {
		double distance = restaurant.getTotalDeliveryDistance();
//		restaurant.getTotalProfit();
		
		JOptionPane.showMessageDialog(frame, "Display delivery and finacial information " + distance);
	}
}
