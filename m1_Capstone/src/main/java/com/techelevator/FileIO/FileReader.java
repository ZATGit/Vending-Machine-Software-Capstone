package com.techelevator.FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

	public List<String[]> getItems(File itemListFile) throws FileNotFoundException {

		List<String[]> itemList = new ArrayList<String[]>();
		// scanner for file
		try(Scanner fileScanner = new Scanner(itemListFile)){

			while(fileScanner.hasNextLine()) {

				String data = fileScanner.nextLine();
				// split the csv file by the delimiters
				String[] splitData = data.split("\\|");

				//add the splited line to a list
				itemList.add(splitData);
			}

		}
		return itemList;
	}

}
