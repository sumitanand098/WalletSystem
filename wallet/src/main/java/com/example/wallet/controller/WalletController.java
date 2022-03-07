package com.example.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wallet.common.Constants;
import com.example.wallet.common.WalletResponse;
import com.example.wallet.common.WalletResponseWrapper;
import com.example.wallet.db.TransactionData;
import com.example.wallet.db.UserPassbook;
import com.example.wallet.service.PassbookDetails;
import com.example.wallet.service.WalletTotalBalance;
import com.example.wallet.serviceImpl.Account;

@RestController
@RequestMapping( value = "/wallet" )
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class WalletController {

	@Autowired WalletTotalBalance walletTotalBalance;

	@Autowired Account account;

	@Autowired PassbookDetails passbookDetails;

	// fetch total amount
	@PostMapping( path = "/totalAmount/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<WalletResponse> getTotalAmountDetails( @PathVariable int id ) {
		WalletResponseWrapper walletResponseWrapper = new WalletResponseWrapper();
		WalletResponse walletResponse = walletResponseWrapper.createResponse(
				Constants.ResponseConstants.SUCCESS, walletTotalBalance.totalBalance( id ) );
		return new ResponseEntity<WalletResponse>( walletResponse, HttpStatus.OK );
	}

	// fetch pass book details
	@PostMapping( path = "/passbook/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<WalletResponse> getPassbookDetails( @PathVariable int id ) {
		//		List<WalletPassbookEntity> listWP = passbookDetails.passbookDetails( userDet.getUserId() );
		WalletResponseWrapper walletResponseWrapper = new WalletResponseWrapper();
		WalletResponse walletResponse = walletResponseWrapper.createResponse(
				Constants.ResponseConstants.SUCCESS, passbookDetails.passbookDetails( id ) );
		return new ResponseEntity<WalletResponse>( walletResponse, HttpStatus.OK );
	}

	// fetch pass book details
	@PostMapping( path = "/passbook",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<WalletResponse>
			getPassbookRangeDetails( @RequestBody UserPassbook userPassbook ) {
		//		List<WalletPassbookEntity> listWP = passbookDetails.passbookDetails( userDet.getUserId() );
		WalletResponseWrapper walletResponseWrapper = new WalletResponseWrapper();
		WalletResponse walletResponse =
				walletResponseWrapper.createResponse( Constants.ResponseConstants.SUCCESS,
						passbookDetails.passbookRangeDetails( userPassbook ) );
		return new ResponseEntity<WalletResponse>( walletResponse, HttpStatus.OK );
	}

	// transfer amount will include charges and commission from UI
	// FromUser to ToUser transaction
	@PostMapping( path = "/walletTransaction",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<WalletResponse>
			debitTransactionDetails( @RequestBody TransactionData transactionData ) {
		WalletResponseWrapper walletResponseWrapper = new WalletResponseWrapper();
		WalletResponse walletResponse = walletResponseWrapper.createResponse(
				Constants.ResponseConstants.SUCCESS, account.withdraw( transactionData ) );
		return new ResponseEntity<WalletResponse>( walletResponse, HttpStatus.OK );
	}

	// transfer amount will include charges and commission from UI
	// from bank transaction (FromUser = ToUser)
	@PostMapping( path = "/bankTransaction",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<WalletResponse>
			creditTransactionDetails( @RequestBody TransactionData transactionData ) {
		WalletResponseWrapper wrw = new WalletResponseWrapper();
		WalletResponse walletResponse;
		if ( account.bankBalanceCheck() )
			walletResponse = wrw.createResponse( Constants.ResponseConstants.SUCCESS,
					account.deposit( transactionData ) );
		else
			walletResponse = wrw.createResponse( Constants.ResponseConstants.SUCCESS,
					Constants.ResponseConstants.FAILURE );
		return new ResponseEntity<WalletResponse>( walletResponse, HttpStatus.OK );
	}

}
