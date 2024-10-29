package com.test.okipotest.response;

import com.test.okipotest.model.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class TransactionResponse {
    private String transactionHash;

    private int blockNumber;

    private long timeStamp;

    private String fromAddress;

    private String toAddress;

    private long gasUsed;

    private double value;

    public TransactionResponse() {
    }

    public TransactionResponse(Transaction transaction) {
        this.transactionHash = transaction.getTransactionHash();
        this.blockNumber = transaction.getBlockNumber();
        this.timeStamp = transaction.getTimeStamp();
        this.fromAddress = transaction.getFromAddress();
        this.toAddress = transaction.getTo();
        this.gasUsed = transaction.getGasUsed();
        this.value = transaction.getValue();
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public long getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(long gasUsed) {
        this.gasUsed = gasUsed;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "transactionHash='" + transactionHash + '\'' +
                ", blockNumber=" + blockNumber +
                ", timeStamp=" + timeStamp +
                ", fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", gasUsed=" + gasUsed +
                ", value=" + value +
                '}';
    }
}
