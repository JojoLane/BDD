package Revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;


    public Account(Currency currency, double balance) {
        this.balance = balance;
        this.accCurrency = currency;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public void addFunds(double topUpAmount, boolean isRequestSuccessful) {
        if (isRequestSuccessful) {
            this.balance += topUpAmount;
        }
    }



    public double getBalance() {
        return this.balance;
    }

    public boolean exchangeCurrency(Person person, String account, String currency, double exchangeRate, double amount){
        if(person != null && person.getAccount(account) != null){
            if(getBalance() >= amount){
                if(person.getAccount(currency) == null){
                    Currency exchangeCurrency = Currency.getInstance(currency);
                    person.getUserAccounts().put(currency, new Account(exchangeCurrency, 0));
                }
                double exchangeAmount = exchangeRate * amount;
                person.getAccount(currency).addFunds(exchangeAmount,true);
                person.getAccount(account).addFunds(-amount, true);
            }
        }
        return false;
    }

    public boolean sendFunds(Person person, String accName, double amount){
        if(person != null && getBalance() >= amount){
            person.getAccount(accName).addFunds(amount, true );
            addFunds(-amount,true);
            return true;
        }
        return false;
    }

}


