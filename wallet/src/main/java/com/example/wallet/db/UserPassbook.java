package com.example.wallet.db;

public class UserPassbook {

	private int walletId;
	private String startDate;
	private String endDate;

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId( int walletId ) {
		this.walletId = walletId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate( String startDate ) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate( String endDate ) {
		this.endDate = endDate;
	}

}
