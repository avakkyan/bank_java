package Entites.BankAccounts;

import Entites.Bank.Bank;
import Entites.Bank.Transaction;
import Entites.Clients.Client;
import Tools.BankException;

public class CreditAccount extends Account {

    private double limit;
    private double commissions;
    private double owesMoney;

    public CreditAccount(double money, double limit, double percent) {
        super(money, limit, percent);
    }
    public double getCommissions()
    {
        return commissions;
    }
    public void applyCommission() {
        if (balance < 0) {
            balance -= commissions;
        }
    }
}
