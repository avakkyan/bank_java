package Entites.BankAccounts;

import Entites.Bank.Bank;
import Entites.Bank.Transaction;
import Entites.Clients.Client;
import Tools.BankException;

import java.util.Objects;
import java.util.UUID;

public abstract class Account {
    private UUID id;
    protected Client owner;
    private double percent;
    protected double balance;

    public Account(double money, double limit, double percent) {
        this.id = UUID.randomUUID();
        this.balance = money;
        this.percent = percent;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPercent() {
        return percent;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Client getOwner(){
        return owner;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public Transaction makeTransaction(Client client,
                                       Account bankAccount1,
                                       Account bankAccount2,
                                       double money) throws BankException {
        if (bankAccount1 instanceof DebitAccount && bankAccount1.getBalance() < 0) {
            throw new BankException("Sorry, insufficient funds");
        }

        Transaction transaction = new Transaction(bankAccount1, bankAccount2, money);
        bankAccount1.setBalance(bankAccount1.getBalance() - money);
        bankAccount2.setBalance(bankAccount2.getBalance() + money);
        return transaction;
    }

    public Transaction withdrawCash(Bank bank, Client client, Account bankAccount, double money) throws BankException {
        Transaction transaction = new Transaction(bankAccount, null, money);
        if (bank.getClients().stream().filter(anyClient -> anyClient == client).findFirst() == null) {
            throw new BankException("Sorry, client not found this bank");
        }

        if (client.getListAccounts().stream().filter(anyBankAccount -> anyBankAccount == bankAccount).findFirst() == null) {
            throw new BankException("Sorry, client don't have this bank account");
        }

        bankAccount.setBalance(bankAccount.getBalance() - money);
        return transaction;
    }

    public void topUpCash(Bank bank, Client client, Account bankAccount, double money) throws BankException {
        if (bank.getClients().stream().filter(anyClient -> anyClient == client) == null) {
            throw new BankException("Sorry, client not found this bank");
        }

        if (client.getListAccounts().stream().filter(anyBankAccount -> anyBankAccount == bankAccount) == null) {
            throw new BankException("Sorry, client don't have this bank account");
        }

        bankAccount.setBalance(bankAccount.getBalance() + money);
    }

    public void countPercent(int amountMonth)
    {
        balance += ((balance / 100) * percent) * amountMonth;
    }
}
