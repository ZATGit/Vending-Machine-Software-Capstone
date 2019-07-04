package com.techelevator.Items;

public class Chip implements Item{

    private String type;
    private String slot;
    private String name;
    private double price;
    private int count;
    private String saying = "Crunch Crunch, Yum!";


    public Chip(String type, String slot, String name, double price, int count) {
        this.type = type;
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getSlot() {
        return slot;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getSaying(){
        return saying;
    }

    @Override
    public void reduceCount() {
        this.count --;
    }

}
