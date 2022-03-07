package com.example.wallet.service;

import java.util.List;

import com.example.wallet.db.UserPassbook;
import com.example.wallet.db.dto.WalletPassbookEntity;

public interface PassbookDetails {

	List<WalletPassbookEntity> passbookDetails( int userId );

	List<WalletPassbookEntity> passbookRangeDetails( UserPassbook userPassbook );

}
