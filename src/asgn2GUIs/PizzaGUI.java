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
		frame.setJMenuBar(createMenuBar());
		frame.setVisible(true);

		menuFile.getMenuComponent(0).setEnabled(true);
		menuFile.getMenuComponent(1).setEnabled(false);
		menuDisplay.getMenuComponent(0).setEnabled(false);
		menuDisplay.getMenuComponent(1).setEnabled(false);
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		menuFile     = new JMenu("Files");
		menuDisplay  = new JMenu("Displays");

		JMenuItem itemLoad = new JMenuItem("Load");
		itemLoad.addActionListener(this);
		itemLoad.setActionCommand("load");
		menuFile.add(itemLoad);
		
		JMenuItem itemReset = new JMenuItem("Reset");
		itemReset.addActionListener(this);
		itemReset.setActionCommand("reset");
		menuFile.add(itemReset);
		
		JMenuItem itemInformation = new JMenuItem("Information");
		itemInformation.addActionListener(this);
		itemInformation.setActionCommand("info");
		menuDisplay.add(itemInformation);
		
		JMenuItem itemCalculations = new JMenuItem("Calculations");
		itemCalculations.addActionListener(this);
		itemCalculations.setActionCommand("calc");
		menuDisplay.add(itemCalculations);
		
		bar.add(menuFile);
		bar.add(menuDisplay);
		return bar;
	}

	@Override
	public void run() {
		restaurant = new PizzaRestaurant();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String move = event.getActionCommand();
		
		if (move == "load") {
			JFileChooser chooser = new JFileChooser();
			File file            = new File(System.getProperty("user.dir") + "/logs");
			
			chooser.setCurrentDirectory(file);
			int response = chooser.showOpenDialog(frame);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				
				try {
					restaurant.processLog(file.getPath());
					
					menuFile.getMenuComponent(0).setEnabled(false);
					menuFile.getMenuComponent(1).setEnabled(true);
					menuDisplay.getMenuComponent(0).setEnabled(true);
					menuDisplay.getMenuComponent(1).setEnabled(true);
					
//					print loading confirmation to main window
				} catch (CustomerException | PizzaException | LogHandlerException error) {
					JOptionPane.showMessageDialog(frame, error.getMessage());
				}
	        }
		} else if (move == "reset") {
			menuFile.getMenuComponent(0).setEnabled(true);
			menuFile.getMenuComponent(1).setEnabled(false);
			menuDisplay.getMenuComponent(0).setEnabled(false);
			menuDisplay.getMenuComponent(1).setEnabled(false);
			
			restaurant.resetDetails();
		} else if (move == "info") {
//			int index;
			
			JOptionPane.showMessageDialog(frame, "Display customer and pizza information");
			
//			restaurant.getNumPizzaOrders();
//			restaurant.getPizzaByIndex(index)
//			restaurant.getNumCustomerOrders();
//			restaurant.getCustomerByIndex(index);
		} else if (move == "calc") {
			JOptionPane.showMessageDialog(frame, "Display delivery and finacial information");
			
//			restaurant.getTotalDeliveryDistance();
//			restaurant.getTotalProfit();
		}
	}
}
