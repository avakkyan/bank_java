package Entites.BankAccounts;

import Entites.Bank.Bank;
import Entites.Bank.Transaction;
import Entites.Clients.Client;
import Tools.BankException;

import java.util.HashMap;
import java.util.Map;

public class DepositAccount extends  Account{
    private static final Map<Double, Double> interestRates = new HashMap<>();

    static {
        interestRates.put(50000.0, 3.0);
        interestRates.put(100000.0, 3.5);
        interestRates.put(Double.MAX_VALUE, 4.0);
    }
        public DepositAccount(double money, double limit, double percent) {
            super(money, limit, percent);
        }

    @Override
        public Transaction makeTransaction(Client client, Account bankAccount1, Account bankAccount2, double money) throws BankException {
            throw new BankException("Sorry, transaction are not available for deposit accounts");
        }

        @Override
        public Transaction withdrawCash(Bank bank, Client client, Account bankAccount, double money) throws BankException {
            throw new BankException("Sorry, you cannot withdraw money from the deposit account");
        }

    public double getInterestRate() {
        double interestOnBalance = 0.0;
        for (Map.Entry<Double, Double> entry : interestRates.entrySet()) {
            if (balance <= entry.getKey()) {
                interestOnBalance = entry.getValue();
                break;
            }
        }
        return interestOnBalance;
    }
    public void calculateInterest() {
        double interestOnBalance = 0.0;
        for (Map.Entry<Double, Double> entry : interestRates.entrySet()) {
            if (balance <= entry.getKey()) {
                interestOnBalance = entry.getValue();
                break;
            }
        }
        balance += balance * interestOnBalance / 100.0;
    }
}
