package org.example;

import java.util.ArrayList;
import java.util.List;
public class User {
    private String username;
    private String email;
    private String password;
    private List<Transaction> transactions;

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.transactions = new ArrayList<>();
    }
    //getters and setters

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(){
        this.password = password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
