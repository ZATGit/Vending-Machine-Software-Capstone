package com.techelevator.Items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Inventory {

    private TreeMap<String,Item>inventory;

    public TreeMap<String,Item> getInventory(List<String[]> items) {

        TreeMap<String,Item> inventory = new TreeMap<String,Item>();

        for(String[] line : items) {
            if(line[3].equals("Chip")) {
                double cost = Double.parseDouble(line[2]);
                Chip chip = new Chip(line[3],line[0],line[1],cost,5);
                inventory.put(line[0], chip);
            }
            else if(line[3].equals("Drink")) {
                double cost = Double.parseDouble(line[2]);
                Drink drink = new Drink(line[3],line[0],line[1],cost,5);
                inventory.put(line[0], drink);
            }
            else if(line[3].equals("Candy")) {
                double cost = Double.parseDouble(line[2]);
                Candy candy = new Candy(line[3],line[0],line[1],cost,5);
                inventory.put(line[0], candy);
            }
            else if(line[3].equals("Gum")) {
                double cost = Double.parseDouble(line[2]);
                Gum gum = new Gum(line[3],line[0],line[1],cost,5);
                inventory.put(line[0], gum);
            }

        }
        return inventory;
    }

}
