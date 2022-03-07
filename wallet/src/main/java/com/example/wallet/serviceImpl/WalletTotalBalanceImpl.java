package com.example.wallet.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.WalletDetailsRepository;
import com.example.wallet.service.WalletTotalBalance;

@Service
public class WalletTotalBalanceImpl implements WalletTotalBalance {

	@Autowired WalletDetailsRepository walletDetailsRepository;

	@Override
	public double totalBalance( int userId ) {
		return walletDetailsRepository.getTotalAmountById( userId ).get( 0 );
	}

}
