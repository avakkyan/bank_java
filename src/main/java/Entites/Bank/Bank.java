package Entites.Bank;

import Entites.BankAccounts.Account;
import Entites.BankAccounts.CreditAccount;
import Entites.BankAccounts.DebitAccount;
import Entites.BankAccounts.DepositAccount;
import Entites.Clients.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String name;
    private UUID id;
    private List<Account> accounts;
    private double percentDebit;
    private double percentCredit;
    private double percentDeposit;
    private double limitDebit;
    private double limitCredit;
    private double limitDeposit;
    private List<Client> clients;

    public Bank(String name, double percentDebit, double percentCredit, double percentDeposit,
            double limitDebit, double limitCredit, double limitDeposit) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.percentDebit = percentDebit;
        this.percentCredit = percentCredit;
        this.percentDeposit = percentDeposit;
        this.limitDebit = limitDebit;
        this.limitCredit = limitCredit;
        this.limitDeposit = limitDeposit;
        this.accounts = new ArrayList<Account>();
        this.clients = new ArrayList<Client>();
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {  return name;    }

    public double getPercentDebit() {
        return percentDebit;
    }

    public void setPercentDebit(double percentDebit) {
        this.percentDebit = percentDebit;
    }

    public double getPercentCredit() {
        return percentCredit;
    }

    public void setPercentCredit(double percentCredit) {
        this.percentCredit = percentCredit;
    }

    public double getPercentDeposit() {
        return percentDeposit;
    }

    public void setPercentDeposit(double percentDeposit) {
        this.percentDeposit = percentDeposit;
    }

    public double getLimitDebit() {
        return limitDebit;
    }

    public void setLimitDebit(double limitDebit) {
        this.limitDebit = limitDebit;
    }

    public double getLimitCredit() {
        return limitCredit;
    }

    public void setLimitCredit(double limitCredit) {
        this.limitCredit = limitCredit;
    }

    public double getLimitDeposit() {
        return limitDeposit;
    }

    public void setLimitDeposit(double limitDeposit) {
        this.limitDeposit = limitDeposit;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Account> getBankAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
    public void countPercent(int amountMonth) {
        for (Account bankAccount : accounts) {
            bankAccount.countPercent(amountMonth);
        }
    }
}
