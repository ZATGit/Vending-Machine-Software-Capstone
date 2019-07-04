package com.techelevator.money;

public class MoneyCalculator {

    public double feedMoney(double insertedMoney, double balance) {
        balance += insertedMoney;
        return balance;
    }

    public double chargeMoney(double moneyToCharge, double balance){
        if(moneyToCharge > balance){
            return balance;
        }
        balance -= moneyToCharge;
        return Math.floor(balance *100) /100;
    }

    public String getChange(double balance){
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;

        while(balance > 0){
            if(balance >= 0.25){
                balance -= 0.25;
                quarterCount ++;
            }
            else if (balance >= 0.10){
                balance -= 0.10;
                dimeCount++;
            }
            else if(balance >= 0.05){
                balance -= 0.05;
                nickelCount ++;
            }
            else if(balance <= 0.05){
                break;
            }
        }
        String yourChange = "Your change is \n" + "Quarters: " + quarterCount + " Dimes: " + dimeCount + " Nickels: " + nickelCount + "\n";
        return yourChange;
    }
}
