package com.example.wallet.service;

import java.util.List;

import com.example.wallet.db.PassbookDataDetails;
import com.example.wallet.db.UserPassbook;
import com.example.wallet.db.dto.WalletTxnPassbookEntity;

public interface PassbookDetails {

	List<PassbookDataDetails> passbookDetails( int userId );

	List<WalletTxnPassbookEntity> passbookRangeDetails( UserPassbook userPassbook );

}
