package com.test.okipotest.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @Column(unique = true, nullable = false)
    private String transactionHash;
    @Column(nullable = false)
    private int blockNumber;
    @Column(nullable = false)
    private long timeStamp;
    @Column(nullable = false)
    private String fromAddress;
    @Column(nullable = false)
    private String toAddress;
    @Column(nullable = false)
    private long gasUsed;
    @Column(nullable = false)
    private double value;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    public Transaction() {
    }

    public Transaction(Result result, Address address) {
        this.transactionHash = result.getHash();
        this.blockNumber = Integer.parseInt(result.getBlockNumber());
        this.timeStamp = Long.parseLong(result.getTimeStamp());
        this.fromAddress = result.getFrom();
        this.toAddress = result.getTo();
        this.gasUsed = Long.parseLong(result.getGasUsed());
        this.value = Double.parseDouble(result.getValue());
        this.address = address;
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

    public String getTo() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionHash='" + transactionHash + '\'' +
                ", blockNumber=" + blockNumber +
                ", timeStamp=" + timeStamp +
                ", fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", gasUsed=" + gasUsed +
                ", value=" + value +
                ", address=" + address +
                '}';
    }
}
