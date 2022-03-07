package com.example.wallet.db;

public class TransactionData {

	private int fromUserId;

	private int toUserId;

	private double amount;

	public int getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId( int fromUserId ) {
		this.fromUserId = fromUserId;
	}

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId( int toUserId ) {
		this.toUserId = toUserId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount( double amount ) {
		this.amount = amount;
	}

}
