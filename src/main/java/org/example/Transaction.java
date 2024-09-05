package org.example;

import java.util.Date;
public class Transaction {
    private Date date;
    private String description;
    private double amount;
    private String category;
    private String transactionType;

    public Transaction(Date date,String description,double amount,String category,String transactionType){
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.transactionType = transactionType;
    }

    //Getters and Setters

    public Date getDate(){
        return date;
    }

    public void setDate(){
        this.date = date;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(){
        this.description = description;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(){
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(){
        this.category = category;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(){
        this.transactionType = transactionType;
    }

}
