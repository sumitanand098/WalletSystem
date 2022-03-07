package com.example.wallet.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.TransactionRepository;
import com.example.wallet.Repository.WalletDetailsRepository;
import com.example.wallet.common.Constants;
import com.example.wallet.common.RuleUtil;
import com.example.wallet.db.TransactionData;
import com.example.wallet.db.dto.TransactionDetailsEntity;
import com.example.wallet.db.dto.WalletPassbookEntity;

@Service
public class Account {

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern( Constants.WalletDatePattern );

	@Autowired WalletTotalBalanceImpl walletTotalBalance;

	@Autowired TransactionRepository transactionRepository;

	@Autowired WalletDetailsRepository walletDetailsRepository;

	private static double balance;

	private int walletId;

	public double totBalance( int userId ) {
		return walletDetailsRepository.getTotalAmountById( userId ).get( 0 );
	}

	public String withdraw( TransactionData txnData ) {
		RuleUtil ru = new RuleUtil();
		WalletPassbookEntity walletPassbookEntity = new WalletPassbookEntity();
		try {
			balance = totBalance( txnData.getFromUserId() );
			if ( balance >= txnData.getAmount() ) {
				TransactionDetailsEntity transactionDetailsEntity =
						new TransactionDetailsEntity();
				walletPassbookEntity.setUserId( txnData.getFromUserId() );
				walletPassbookEntity.setSecUserId( txnData.getToUserId() );
				walletPassbookEntity.setTxnType( Constants.Zero );
				walletPassbookEntity.setStatus( true );
				LocalDateTime now = LocalDateTime.now();
				walletPassbookEntity.setCreatedAt( dtf.format( now ) );
				walletPassbookEntity.setTotTxnAmt( ru.actualBalance( txnData.getAmount() ) );
				walletPassbookEntity.setTotAccAmt( balance - txnData.getAmount() );
				walletId = walletDetailsRepository.save( walletPassbookEntity ).getId();
				Thread.sleep( 1000 );

				transactionDetailsEntity.setWalletId( walletId );
				transactionDetailsEntity.setTxnAmt( txnData.getAmount() );
				transactionDetailsEntity
						.setTxnCharge( ru.calculateCharge( txnData.getAmount() ) );
				transactionDetailsEntity
						.setTxnComsn( ru.calculateCommission( txnData.getAmount() ) );
				transactionRepository.save( transactionDetailsEntity );
				Thread.sleep( 1000 );
				//				failExceptionTest();
				deposit( txnData );
				return Constants.ResponseConstants.SUCCESS;
			}
			else {
				return Constants.ResponseConstants.FAILURE;
			}
		}
		catch ( Exception e ) {
			e.printStackTrace();
			walletPassbookEntity.setId( walletId );
			walletPassbookEntity.setStatus( false );
			walletPassbookEntity.setTotAccAmt( balance );
			walletDetailsRepository.save( walletPassbookEntity );
			return Constants.ResponseConstants.FAILURE;
		}
	}

	//	private void failExceptionTest() {
	//		int[] arr = { 0 };
	//		System.out.println( arr[3] );
	//	}

	public String deposit( TransactionData txnData ) {
		RuleUtil ru = new RuleUtil();
		try {
			balance = totBalance( txnData.getToUserId() );
			WalletPassbookEntity walletPassbookEntity = new WalletPassbookEntity();
			walletPassbookEntity.setUserId( txnData.getToUserId() );
			walletPassbookEntity.setSecUserId( txnData.getFromUserId() );
			walletPassbookEntity.setTxnType( Constants.One );
			walletPassbookEntity.setStatus( true );
			LocalDateTime now = LocalDateTime.now();
			walletPassbookEntity.setCreatedAt( dtf.format( now ) );
			walletPassbookEntity.setTotTxnAmt( ru.actualBalance( txnData.getAmount() ) );
			walletPassbookEntity
					.setTotAccAmt( balance + ru.actualBalance( txnData.getAmount() ) );
			walletDetailsRepository.save( walletPassbookEntity );
			Thread.sleep( 1000 );
			return Constants.ResponseConstants.SUCCESS;
		}
		catch ( Exception e ) {
			e.printStackTrace();
			return Constants.ResponseConstants.FAILURE;
		}
	}

	public boolean bankBalanceCheck() {
		// code to check balance from bank account
		return true;
	}

}