package com.example.wallet.db;

public class TransactionData {

	private int drWalletId;
	private int crWalletId;
	private double amount;
	private String txnId;

	public int getDrWalletId() {
		return drWalletId;
	}

	public void setDrWalletId( int drWalletId ) {
		this.drWalletId = drWalletId;
	}

	public int getCrWalletId() {
		return crWalletId;
	}

	public void setCrWalletId( int crWalletId ) {
		this.crWalletId = crWalletId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount( double amount ) {
		this.amount = amount;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId( String txnId ) {
		this.txnId = txnId;
	}
}
