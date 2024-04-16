package Entites.Clients;

import Entites.BankAccounts.Account;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String firstName;
    private String lastName;
    private String address;
    private String passport;
    private List<Account> accounts;
    public Client(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        address = null;
        passport = null;
        accounts = new ArrayList<Account>();
    }
    public ClientBuilder toBuilder(ClientBuilder clientBuilder){
        clientBuilder.addFirstName(firstName);
        clientBuilder.addLastName(lastName);
        clientBuilder.addAddress(address);
        clientBuilder.addPassport(passport);
        return clientBuilder;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(){
        this.lastName = lastName;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(){
        this.address = address;
    }

    public String getPassport(){
        return passport;
    }
    public void setPassport(){
        this.passport = passport;
    }
    public List<Account> addToAccountList(Account bankAccount){
        accounts.add(bankAccount);
        return accounts;
    }
    public List<Account> getListAccounts() {
        return accounts;
    }

    public void setListAccounts(List<Account> listAccounts) {
        this.accounts = listAccounts;
    }
}
