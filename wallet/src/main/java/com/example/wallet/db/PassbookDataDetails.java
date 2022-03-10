package com.example.wallet.db;

import java.util.Date;

public class PassbookDataDetails {

	private int id;
	private int walletId;
	private int txnType;
	private int status;
	private double totTxnAmt;
	private double totAccAmt;
	private Date createdAt;
	private String txnId;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId( int walletId ) {
		this.walletId = walletId;
	}

	public int getTxnType() {
		return txnType;
	}

	public void setTxnType( int txnType ) {
		this.txnType = txnType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus( int status ) {
		this.status = status;
	}

	public double getTotTxnAmt() {
		return totTxnAmt;
	}

	public void setTotTxnAmt( double totTxnAmt ) {
		this.totTxnAmt = totTxnAmt;
	}

	public double getTotAccAmt() {
		return totAccAmt;
	}

	public void setTotAccAmt( double totAccAmt ) {
		this.totAccAmt = totAccAmt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt( Date createdAt ) {
		this.createdAt = createdAt;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId( String txnId ) {
		this.txnId = txnId;
	}

}
