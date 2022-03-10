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
@Table( name = "wallet_txn_passbook" )
public class WalletTxnPassbookEntity {

	@Id @SequenceGenerator( name = "seq_txn_passbook", sequenceName = "seq_txn_passbook",
			allocationSize = 1 ) @GeneratedValue( strategy = GenerationType.SEQUENCE,
					generator = "seq_txn_passbook" ) @Column( name = "ID" ) private int id;
	@Column( name = "dr_wallet_id" ) private int drWalletId;
	@Column( name = "cr_wallet_id" ) private int crWalletId;
	// cr = 1 || dr = 0
	@Column( name = "txn_type" ) private int txnType;
	@Column( name = "txn_status" ) private int status;
	@Column( name = "tot_tx_amt" ) private double totTxnAmt;
	@Column( name = "tot_acc_amt" ) private double totAccAmt;
	@Column( name = "created_at", insertable = false,
			updatable = false ) private Date createdAt;
	@Column( name = "txn_id" ) private String txnId;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

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
