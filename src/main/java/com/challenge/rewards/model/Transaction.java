package com.challenge.rewards.model;

public class Transaction {
	private String customerId;
	private int dollarsSpent;
	private String transactionDate;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getDollarsSpent() {
		return dollarsSpent;
	}
	public void setDollarsSpent(int dollarsSpent) {
		this.dollarsSpent = dollarsSpent;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
}
