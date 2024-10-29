package com.test.okipotest.response;

import java.util.List;

public class TransactionResponseBonus {
    private double balance;
    private List<TransactionResponse> transactionResponseList;


    public TransactionResponseBonus(){
    }

    public TransactionResponseBonus(double balance, List<TransactionResponse> transactionResponseList) {
        this.balance = balance;
        this.transactionResponseList = transactionResponseList;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionResponse> getTransactionResponseList() {
        return transactionResponseList;
    }

    public void setTransactionResponseList(List<TransactionResponse> transactionResponseList) {
        this.transactionResponseList = transactionResponseList;
    }

    @Override
    public String toString() {
        return "TransactionResponseBonus{" +
                "balance=" + balance +
                ", transactionResponseList=" + transactionResponseList +
                '}';
    }
}


