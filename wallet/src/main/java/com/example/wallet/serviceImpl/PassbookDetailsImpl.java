package com.example.wallet.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.WalletDetailsRepository;
import com.example.wallet.db.UserPassbook;
import com.example.wallet.db.dto.WalletPassbookEntity;
import com.example.wallet.service.PassbookDetails;

@Service
public class PassbookDetailsImpl implements PassbookDetails {

	@Autowired
	WalletDetailsRepository walletDetailsRepository;

	@Override
	public List<WalletPassbookEntity> passbookDetails( int userId ) {
		return walletDetailsRepository.getPassbookDetailsById( userId );
	}

	@Override
	public List<WalletPassbookEntity> passbookRangeDetails( UserPassbook userPassbook ) {
		return walletDetailsRepository.getPassbookRangeDetailsById( userPassbook.getUserId(), userPassbook.getStartDate(), userPassbook.getEndDate() );
	}

}
