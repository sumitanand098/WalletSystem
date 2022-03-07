package com.example.wallet.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "transaction_details" )
public class TransactionDetailsEntity {

	@Id @SequenceGenerator( name = "seq_txn", sequenceName = "seq_txn",
			allocationSize = 1 ) @GeneratedValue( strategy = GenerationType.SEQUENCE,
					generator = "seq_txn" ) @Column( name = "ID" ) private int id;

	@Column( name = "wallet_id" ) private int walletId;

	@Column( name = "txn_amt" ) private double txnAmt;

	@Column( name = "txn_charge" ) private double txnCharge;

	@Column( name = "txn_comsn" ) private double txnComsn;

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

	public double getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt( double txnAmt ) {
		this.txnAmt = txnAmt;
	}

	public double getTxnCharge() {
		return txnCharge;
	}

	public void setTxnCharge( double txnCharge ) {
		this.txnCharge = txnCharge;
	}

	public double getTxnComsn() {
		return txnComsn;
	}

	public void setTxnComsn( double txnComsn ) {
		this.txnComsn = txnComsn;
	}

}
