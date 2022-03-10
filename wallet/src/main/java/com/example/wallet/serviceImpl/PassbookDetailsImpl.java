package com.example.wallet.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.TxnPassbookRepository;
import com.example.wallet.common.Constants;
import com.example.wallet.db.PassbookDataDetails;
import com.example.wallet.db.UserPassbook;
import com.example.wallet.db.dto.WalletTxnPassbookEntity;
import com.example.wallet.service.PassbookDetails;

@Service
public class PassbookDetailsImpl implements PassbookDetails {

	@Autowired TxnPassbookRepository txnPassbookRepository;

	@Override
	public List<PassbookDataDetails> passbookDetails( int walletId ) {
		List<WalletTxnPassbookEntity> wtpe =
				txnPassbookRepository.getPassbookDetailsById( walletId );
		List<PassbookDataDetails> passbookDataDetailsList =
				new ArrayList<PassbookDataDetails>();
		for ( WalletTxnPassbookEntity wtp : wtpe ) {
			passbookDataDetailsList.add( getpassbookDataDetails( wtp, walletId ) );
		}

		return passbookDataDetailsList;
	}

	private PassbookDataDetails getpassbookDataDetails( WalletTxnPassbookEntity wtp,
		int walletId ) {
		PassbookDataDetails passbookDataDetails = new PassbookDataDetails();
		passbookDataDetails.setId( wtp.getId() );
		passbookDataDetails.setTotAccAmt( wtp.getTotAccAmt() );
		passbookDataDetails.setTotTxnAmt( wtp.getTotTxnAmt() );
		passbookDataDetails.setStatus( wtp.getStatus() );
		passbookDataDetails.setWalletId( walletId );
		passbookDataDetails.setTxnId( wtp.getTxnId() );
		passbookDataDetails.setCreatedAt( wtp.getCreatedAt() );
		passbookDataDetails.setTxnType( wtp.getTxnType() );

		return passbookDataDetails;
	}

	@Override
	public List<WalletTxnPassbookEntity> passbookRangeDetails( UserPassbook userPassbook ) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat( Constants.WALLETDATEPATTERN )
					.parse( userPassbook.getStartDate() );
			endDate = new SimpleDateFormat( Constants.WALLETDATEPATTERN )
					.parse( userPassbook.getEndDate() );
			return txnPassbookRepository.getPassbookRangeDetailsById(
					userPassbook.getWalletId(),
					startDate, endDate );
		}
		catch ( ParseException e ) {
			e.printStackTrace();
		}

		return null;
	}

}
