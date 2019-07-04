package com.techelevator.Items;

public interface Item {

    String getType();

    String getSlot();

    String getName();

    double getPrice();

    int getCount();

    String getSaying();

    void reduceCount();

}
