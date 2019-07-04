package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.techelevator.FileIO.FileReader;
import com.techelevator.FileIO.LogWriter;
import com.techelevator.Items.Inventory;
import com.techelevator.Items.Item;
import com.techelevator.money.MoneyCalculator;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		// variable  and object creation
		double balance = 0;
		double pastBalance = 0;
		Inventory inventory = new Inventory();
		MoneyCalculator calc = new MoneyCalculator();
		FileReader invReader = new FileReader();
		File file = new File("vendingmachine.csv");
		List<String[]> invList = new ArrayList<String[]>(); // maybe not needed?
		LogWriter logWriter = new LogWriter();
		try {
			invList = invReader.getItems(file);
		} catch (FileNotFoundException e) {
			menu.printMsgToUser("Inventory file not found");
			e.printStackTrace();
		}
		TreeMap<String,Item> invenoryItems = inventory.getInventory(invList);
		List<Item> cart = new ArrayList<Item>();

		// infinite loop to run the program until the process is killed
		while(true) {
			
			//get user input to see if they want a list of available items or continue onto the next menu
			String firstChoice = menu.firstChoice();
			if(firstChoice.equals("1")){
				// print a list of available invintory items
				printProducts(invList,invenoryItems);
			}
			else if(firstChoice.equals("2")){
				// display second menu and get user input
				String secondChoice = menu.secondChoice(balance);
				switch (secondChoice) {
					case "1":
						
						// see how much money user wants to add
						double insertedMoney = menu.getMoney();
						pastBalance = balance;
						
						//add money to balance
						balance = calc.feedMoney(insertedMoney, balance);
						
						//write to log that user added money
						try {
							logWriter.WriteToLog("FEED MONEY:", pastBalance, balance);
						} catch (IOException e) {
							menu.printMsgToUser("Error trying to write to log.txt");
						}

						break;

					case "2":
	
						String itemChoice = menu.getItemChoice(); // ask the user what item they would like to buy and get response
						Item chosenItem = invenoryItems.get(itemChoice);						
						chosenItem.reduceCount(); // reduce the total number of that item available

						cart.add(chosenItem);	//add wanted item to a shopping cart
						pastBalance = balance;
						
						//check to see if the user has enough money to purchase the selected item
						if (chosenItem.getPrice() > balance) { 
							menu.printMsgToUser("balance is too low to afford " + chosenItem.getName());
						}
						// check that item is still in stock
						else if(chosenItem.getCount() <= 0){
							menu.printMsgToUser("Sorry that item is sold out \n");
						}
						// charge the user for the selected item and reduce its total stock available
						else {
							balance = calc.chargeMoney(chosenItem.getPrice(), balance);
							String itemName = chosenItem.getName();
							String itemSlot = chosenItem.getSlot();
							
							// write to log that user purchased an item
							try {
								logWriter.WriteToLog(itemName + " " + itemSlot, pastBalance, balance);
							} catch (IOException e) {
								menu.printMsgToUser("Error trying to write to log.txt");
							}
						}
						break;

					case "3":
						// print change
						menu.printMsgToUser(calc.getChange(balance));
						// print the item specific sayings
						for (Item item : cart) {
							menu.printMsgToUser(item.getSaying() + "\n");
						}
						// write to log that transaction was finished
						balance = 0;
						try {
							logWriter.WriteToLog("GIVE CHANGE: ", pastBalance, balance);
						} catch (IOException e) {
							menu.printMsgToUser("Error trying to write to log.txt");
						}
						break;

				}
			}

		}
	}

	public void printProducts(List<String[]>invList, TreeMap<String,Item> inventory) {
		for (String[] lines :invList) {
			for(int i = 0; i < lines.length -1; i++) {
				String line = lines[i];
				String key = lines[0];
				Item chosenItem = (Item) inventory.get(key);
				int count = chosenItem.getCount();
				menu.printMsgToUser(line + " " + count);
			}
			menu.printMsgToUser("\n");
		}
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
