package Entites.Bank;

import Entites.Bank.Bank;
import Entites.Bank.Transaction;
import Entites.BankAccounts.Account;
import Entites.BankAccounts.CreditAccount;
import Entites.BankAccounts.DebitAccount;
import Entites.BankAccounts.DepositAccount;
import Entites.Clients.Client;
import Entites.Clients.ClientBuilder;
import Tools.BankException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CentralBank {
    public final int countMonth = 30;
    private String name;
    private List<Bank> banks;
    private List<Transaction> transactions;

    public CentralBank(String name){
        this.name = name;
        this.banks = new ArrayList<Bank>();
        this.transactions = new ArrayList<Transaction>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Bank addBank(String name, double percentDebit,
                        double percentCredit, double percentDeposit,
                        double limitDebit, double limitCredit, double limitDeposit) {
        Bank bank = new Bank(name, percentDebit, percentCredit, percentDeposit, limitDebit, limitCredit, limitDeposit);
        banks.add(bank);
        return bank;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void cancelTransaction(UUID id) throws BankException {
        Transaction transaction = transactions.stream()
                .filter(transaction1 -> transaction1.getId() == id).findFirst().orElse(null);
        if(transaction == null) {
            throw new BankException("Sorry, no such transaction id or transaction has already been completed");
        }
        transaction.getBankAccount1().setBalance(transaction.getBankAccount1().getBalance() + transaction.getMoney());
        if(transaction.getBankAccount2() != null) {
            transaction.getBankAccount2().setBalance(transaction.getBankAccount2().getBalance() - transaction.getMoney());
        }

        transactions.remove(transaction);
    }

    public Client createClient(String firstName, String lastName) {
        Client client = new Client(firstName, lastName);
        return client;
    }

    public Client addClientAddress(Client client, String address) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.addFirstName(client.getFirstName());
        clientBuilder.addLastName(client.getLastName());
        clientBuilder.addAddress(address);
        clientBuilder.addPassport(client.getPassport());
        Client newClient = clientBuilder.toBuild();
        return newClient;
    }

    public Client addClientPassport(Client client, String passport) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.addFirstName(client.getFirstName());
        clientBuilder.addLastName(client.getLastName());
        clientBuilder.addAddress(client.getAddress());
        clientBuilder.addPassport(passport);
        return clientBuilder.toBuild();
    }

    public void addClientBank(Client client, Bank bank) {
        bank.getClients().add(client);
    }

    public DebitAccount createDebitAccountForClient(Bank bank, Client client, double money) {
        double limit;
        if (!Objects.equals(client.getPassport(), null)) {
            limit = Integer.MAX_VALUE;
        }
        else {
            limit = bank.getLimitDebit();
        }

        DebitAccount debit = new DebitAccount(money, limit, bank.getPercentDebit());
        bank.getBankAccounts().add(debit);
        return debit;
    }

    public DepositAccount createDepositAccountForClient(Bank bank, Client client, double money) {
        double limit;
        if(!Objects.equals(client.getPassport(), null)) {
            limit = Integer.MAX_VALUE;
        }
        else {
            limit = bank.getLimitDeposit();
        }

        DepositAccount deposit = new DepositAccount(money, limit, bank.getPercentDeposit());
        bank.getBankAccounts().add(deposit);
        client.addToAccountList(deposit);
        return deposit;
    }

    public CreditAccount createCreditAccountForClient(Bank bank, Client client, double money) {
        double limit;
        if (!Objects.equals(client.getPassport(), null)) {
            limit = Integer.MAX_VALUE;
        } else {
            limit = bank.getLimitCredit();
        }

        CreditAccount credit = new CreditAccount(money, limit, bank.getPercentCredit());
        bank.getBankAccounts().add(credit);
        client.addToAccountList(credit);
        return credit;
    }

    public List<Bank> allCountPercent(int days) {
        int amountMonth = days / countMonth;
        for (Bank bank : banks) {
            bank.countPercent(amountMonth);
        }

        return banks;
    }

    public void changePercentDebitAccount(Bank bank, double newPercent) {
        bank.setPercentDebit(newPercent);
        for (Account bankAccount : bank.getBankAccounts()) {
            if(bankAccount instanceof DebitAccount) {
                bankAccount.setPercent(newPercent);
            }
        }
    }

    public void changePercentCreditAccount(Bank bank, double newPercent) {
        bank.setPercentCredit(newPercent);
        for (Account bankAccount : bank.getBankAccounts()) {
            if(bankAccount instanceof CreditAccount) {
                bankAccount.setPercent(newPercent);
            }
        }
    }

    public void changePercentDepositAccount(Bank bank, double newPercent) {
        bank.setPercentDeposit(newPercent);
        for (Account bankAccount : bank.getBankAccounts()) {
            if(bankAccount instanceof DepositAccount) {
                bankAccount.setPercent(newPercent);
            }
        }
    }
}