package com.techelevator.moneytest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.techelevator.money.MoneyCalculator;

import org.junit.Assert;

public class MoneyCalculatorTest {

    private MoneyCalculator calc;

    @Before
    public void setup() {
       this.calc= new MoneyCalculator();
    }


    @Test
    public void feed_money_balance_should_update(){
        double balance = 0;
        balance = calc.feedMoney(5.00,balance);
        Assert.assertEquals("expected balance to be 5.00 after feeding 5.00",5.00,balance,2);

        balance = calc.feedMoney(5.00,balance);
        Assert.assertEquals("expected feeding 5.00 to balance of 5.00 should return 10.00",10.00,balance,2);
    }

    @Test
    public void charge_item_cost_from_balance(){
        double balance = 0;
        balance = calc.feedMoney(5.00,balance);
        balance = calc.chargeMoney(2.50,balance);
        Assert.assertEquals("Expected balance to be 2.50 after charging 2.50 from 5.00", 2.50, balance,2);

        balance = calc.chargeMoney(5.00,balance);
        Assert.assertEquals("When cost>balance return balance",2.50,balance,2);
    }

    @Test
    public void balance_of_1_25_returns_5_quarters(){
        double balance = Math.floor(1.25 * 100) /100;
        String returnedValue = calc.getChange(balance);
        String expectedValue = "Your change is \n" + "Quarters: " + 5  + " Dimes: " + 0 + " Nickels: " + 0 + "\n";

        Assert.assertEquals("Expected to get back 5 quarters 0 dimes and 0 nickels",expectedValue , returnedValue);
    }

    @Test
    public void balance_of_1_05_to_return_4_quarters_and_1_nickel(){
        double balance = 1.05;
        String returnedValue = calc.getChange(balance);
        String expectedValue = "Your change is \n" + "Quarters: " + 4  + " Dimes: " + 0 + " Nickels: " + 1 + "\n";

        Assert.assertEquals("expected a balance of 1.05 to return 4 quarters and 1 nickel", expectedValue,returnedValue);
    }

}