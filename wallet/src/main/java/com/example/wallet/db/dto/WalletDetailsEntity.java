package com.example.wallet.db.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "wallet_details" )
public class WalletDetailsEntity {

	@Id @SequenceGenerator( name = "seq_wallet_details", sequenceName = "seq_wallet_details",
			allocationSize = 1 ) @GeneratedValue( strategy = GenerationType.SEQUENCE,
					generator = "seq_wallet_details" ) @Column( name = "ID" ) private int id;

	@Column( name = "user_id" ) private int userId;
	@Column( name = "wallet_type" ) private int walletType;
	@Column( name = "wallet_status" ) private int walletStatus;
	@Column( name = "created_at", insertable = false,
			updatable = false ) private Date createdAt;
	@Column( name = "tot_acc_amt" ) private double totAccAmt;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId( int userId ) {
		this.userId = userId;
	}

	public int getWalletType() {
		return walletType;
	}

	public void setWalletType( int walletType ) {
		this.walletType = walletType;
	}

	public int getWalletStatus() {
		return walletStatus;
	}

	public void setWalletStatus( int walletStatus ) {
		this.walletStatus = walletStatus;
	}

	public double getTotAccAmt() {
		return totAccAmt;
	}

	public void setTotAccAmt( double totAccAmt ) {
		this.totAccAmt = totAccAmt;
	}

}
