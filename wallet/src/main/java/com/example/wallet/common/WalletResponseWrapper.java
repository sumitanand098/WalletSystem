package com.example.wallet.common;

public class WalletResponseWrapper {

	public WalletResponse createResponse( String status, Object content ) {
		WalletResponse walletResp = new WalletResponse();
		walletResp.setStatus( status );
		walletResp.setContent( content );
		return walletResp;
	}
}
