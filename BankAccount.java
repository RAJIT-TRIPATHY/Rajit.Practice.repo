package bank;

import java.util.Date;

public abstract class BankAccount {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected String password;
    protected Date lastTransactionDate;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance, String password) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.password = password;
        this.lastTransactionDate = new Date();
    }

    public abstract void withdraw(double amount, String password) throws Exception;

    public void deposit(double amount) {
        balance += amount;
        lastTransactionDate = new Date();
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public Date getLastTransactionDate() {
        return lastTransactionDate;
    }
}

