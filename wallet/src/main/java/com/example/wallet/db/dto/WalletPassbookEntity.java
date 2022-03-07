package com.example.wallet.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "wallet_passbook" )
public class WalletPassbookEntity {

	@Id @SequenceGenerator( name = "seq_wallet", sequenceName = "seq_wallet",
			allocationSize = 1 ) @GeneratedValue( strategy = GenerationType.SEQUENCE,
					generator = "seq_wallet" ) @Column( name = "ID" ) private int id;

	@Column( name = "user_id" ) private int userId;

	@Column( name = "second_user_id" ) private int secUserId;

	// cr = 1 || dr = 0
	@Column( name = "txn_type" ) private int txnType;

	@Column( name = "status" ) private boolean status;

	@Column( name = "tot_tx_amt" ) private double totTxnAmt;

	@Column( name = "tot_acc_amt" ) private double totAccAmt;

	@Column( name = "created_at" ) private String createdAt;

	@Column( name = "txn_id" ) private String txnId;

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

	public int getSecUserId() {
		return secUserId;
	}

	public void setSecUserId( int secUserId ) {
		this.secUserId = secUserId;
	}

	public int getTxnType() {
		return txnType;
	}

	public void setTxnType( int txnType ) {
		this.txnType = txnType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus( boolean status ) {
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt( String createdAt ) {
		this.createdAt = createdAt;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId( String txnId ) {
		this.txnId = txnId;
	}

}
