package Revolut;

import java.util.Currency;
import java.util.HashMap;

public class Person {
    private String name;

    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();
    //private HashMap<String, Crypto> cryptoAccounts = new HashMap<String, Account>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it to the userAccounts container
        Currency accdCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accdCurrency, 0);
        userAccounts.put("EUR", euroAccount);
    }

    public void setAccountBalance(double startingBalance, String accName) {
        //Set the starting balance of the person's main account to startingBalance
        userAccounts.get(accName).setBalance(startingBalance);
    }

    public Account getAccount(String accountCur) {
        return userAccounts.get(accountCur);
    }

    public double getAccountBalance(String accCurreny){
        return userAccounts.get(accCurreny).getBalance();
    }
    public HashMap<String, Account> getUserAccounts() {
        return userAccounts;
    }


}
