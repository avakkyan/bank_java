package Entites.Bank;

import Entites.BankAccounts.Account;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private Account bankAccount1;
    private Account bankAccount2;
    private double money;

    public Transaction(Account bankaccount1, Account bankAccount2, double money){
        this.id = UUID.randomUUID();
        this.bankAccount1 = bankaccount1;
        this.bankAccount2 = bankAccount2;
        this.money = money;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getBankAccount1() {
        return bankAccount1;
    }

    public void setBankAccount1(Account bankAccount1) {
        this.bankAccount1 = bankAccount1;
    }

    public Account getBankAccount2() {
        return bankAccount2;
    }

    public void setBankAccount2(Account bankAccount2) {
        this.bankAccount2 = bankAccount2;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}