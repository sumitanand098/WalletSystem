package com.example.wallet.db;

public class UserPassbook {

	private int userId;

	private String startDate;

	private String endDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId( int userId ) {
		this.userId = userId;
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
