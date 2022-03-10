package com.example.wallet.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.TransactionDetailsRepository;
import com.example.wallet.Repository.TxnPassbookRepository;
import com.example.wallet.Repository.WalletDetailsRepository;
import com.example.wallet.common.Constants;
import com.example.wallet.common.RuleUtil;
import com.example.wallet.db.TransactionData;
import com.example.wallet.db.dto.TransactionDetailsEntity;
import com.example.wallet.db.dto.WalletDetailsEntity;
import com.example.wallet.db.dto.WalletTxnPassbookEntity;

@Service
public class AccountRebateTxn {

	private static double balance;
	private int withdrawTxnId;
	private int depositTxnId;
	private double totalDrBalance;
	private double totalCrBalance;
	private String txnStatusResp;

	@Autowired TransactionDetailsRepository transactionRepository;
	@Autowired TxnPassbookRepository txnPassbookRepository;
	@Autowired WalletDetailsRepository walletDetailsRepository;

	public String withdraw( TransactionData txnData ) {
		RuleUtil ru = new RuleUtil();
		WalletTxnPassbookEntity walletTxnPassbookEntity = new WalletTxnPassbookEntity();
		WalletDetailsEntity walletDetailsEntity = new WalletDetailsEntity();
		walletDetailsEntity =
				walletDetailsRepository.getBalanceByWalletId( txnData.getDrWalletId() );
		if ( !checkValidRebate( txnData.getTxnId() ) )
			return Constants.ResponseConstants.FAILURE;
		try {
			this.totalDrBalance = balance = totBalance( txnData.getDrWalletId() );
			if ( balance >= txnData.getAmount() ) {
				TransactionDetailsEntity transactionDetailsEntity =
						new TransactionDetailsEntity();
				walletTxnPassbookEntity.setDrWalletId( txnData.getDrWalletId() );
				walletTxnPassbookEntity.setCrWalletId( txnData.getCrWalletId() );
				walletTxnPassbookEntity.setStatus( Constants.ONE );
				walletTxnPassbookEntity.setTxnType( Constants.TxnType.DEBIT_REBATE );
				walletTxnPassbookEntity
						.setTotTxnAmt( txnData.getAmount() );
				walletTxnPassbookEntity.setTotAccAmt( balance + txnData.getAmount() );
				walletTxnPassbookEntity.setTxnId( txnData.getTxnId() );
				withdrawTxnId = txnPassbookRepository.save( walletTxnPassbookEntity ).getId();

				walletDetailsEntity.setTotAccAmt( balance + txnData.getAmount() );
				walletDetailsRepository.save( walletDetailsEntity );
				Thread.sleep( Constants.millis );

				transactionDetailsEntity.setWalletId( withdrawTxnId );
				transactionDetailsEntity
						.setTxnAmt( txnData.getAmount() * Constants.REBATE_VAR );
				transactionDetailsEntity
						.setTxnCharge( ru.calculateCharge( txnData.getAmount() )
								* Constants.REBATE_VAR );
				transactionDetailsEntity
						.setTxnComsn( ru.calculateCommission( txnData.getAmount() )
								* Constants.REBATE_VAR );
				transactionRepository.save( transactionDetailsEntity );
				Thread.sleep( Constants.millis );

				//				failExceptionTest();
				this.txnStatusResp = Constants.ResponseConstants.SUCCESS;
				this.txnStatusResp = deposit( txnData );
				if ( !this.txnStatusResp.equals( Constants.ResponseConstants.SUCCESS ) ) {
					isFailed( walletTxnPassbookEntity, walletDetailsEntity, withdrawTxnId,
							this.totalDrBalance );
				}
				return this.txnStatusResp;
			}
			else {
				return Constants.ResponseConstants.FAILURE;
			}

		}
		catch ( Exception e ) {
			//			e.printStackTrace();
			isFailed( walletTxnPassbookEntity, walletDetailsEntity, withdrawTxnId,
					this.totalDrBalance );
			return Constants.ResponseConstants.FAILURE;
		}

	}

	public String deposit( TransactionData txnData ) {
		if ( !isWalletActive( txnData.getCrWalletId() ) )
			return Constants.ResponseConstants.FAILURE;

		if ( isTxnPending( txnData.getCrWalletId() ) )
			return Constants.ResponseConstants.PENDING;

		RuleUtil ru = new RuleUtil();
		WalletDetailsEntity walletDetailsEntity = new WalletDetailsEntity();
		walletDetailsEntity =
				walletDetailsRepository.getBalanceByWalletId( txnData.getCrWalletId() );
		WalletTxnPassbookEntity walletTxnPassbookEntity = new WalletTxnPassbookEntity();
		try {
			this.totalCrBalance = balance = totBalance( txnData.getCrWalletId() );

			walletTxnPassbookEntity.setDrWalletId( txnData.getCrWalletId() );
			walletTxnPassbookEntity.setCrWalletId( txnData.getDrWalletId() );
			walletTxnPassbookEntity.setStatus( Constants.ONE );
			walletTxnPassbookEntity.setTxnType( Constants.TxnType.CREDIT_REBATE );
			walletTxnPassbookEntity.setTotTxnAmt(
					ru.actualBalance( txnData.getAmount() ) * Constants.REBATE_VAR );
			walletTxnPassbookEntity
					.setTotAccAmt( balance - ru.actualBalance( txnData.getAmount() ) );
			walletDetailsEntity
					.setTotAccAmt( balance - ru.actualBalance( txnData.getAmount() ) );
			walletTxnPassbookEntity.setTxnId( txnData.getTxnId() );

			//			failExceptionTest();

			depositTxnId = txnPassbookRepository.save( walletTxnPassbookEntity ).getId();
			walletDetailsRepository.save( walletDetailsEntity );
			Thread.sleep( Constants.millis );
			return Constants.ResponseConstants.SUCCESS;
		}
		catch ( Exception e ) {
			//			e.printStackTrace();
			isFailed( walletTxnPassbookEntity, walletDetailsEntity, depositTxnId,
					this.totalCrBalance );
			return Constants.ResponseConstants.FAILURE;
		}
	}

	private boolean checkValidRebate( String txnId ) {
		int size = txnPassbookRepository
				.getValidRebate( txnId, Constants.TxnType.CREDIT_REBATE ).size();
		return size <= 0 ? true : false;
	}

	private boolean isTxnPending( int walletId ) {
		return ( walletId == Constants.PENDING_ACC ) ? true : false;
	}

	private boolean isWalletActive( int walletId ) {
		Optional<WalletDetailsEntity> walletDetails =
				walletDetailsRepository.findById( walletId );
		if ( walletDetails.isPresent() )
			return ( walletDetails.get().getWalletStatus() == 0 ) ? false : true;
		return false;
	}

	public double totBalance( int walletId ) {
		Optional<WalletDetailsEntity> walletDetails =
				walletDetailsRepository.findById( walletId );
		if ( walletDetails.isPresent() )
			return walletDetails.get().getTotAccAmt();
		return 0;
	}

	private void isFailed( WalletTxnPassbookEntity walletTxnPassbookEntity,
		WalletDetailsEntity walletDetailsEntity, int txnId, double totalBalance ) {
		walletDetailsEntity.setTotAccAmt( totalBalance );
		walletDetailsRepository.save( walletDetailsEntity );
		walletTxnPassbookEntity.setId( txnId );
		walletTxnPassbookEntity.setStatus( Constants.ZERO );
		walletTxnPassbookEntity.setTotAccAmt( totalBalance );
		txnPassbookRepository.save( walletTxnPassbookEntity );
	}

	//	private void failExceptionTest() {
	//		int[] arr = { 0 };
	//		System.out.println( arr[3] );
	//	}

	public boolean bankBalanceCheck() {
		// code to check balance from bank account
		return true;
	}

}
